package org.deking.security.controller.filter;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.deking.security.ISaltProvider;
import org.deking.security.constant.Constants;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@RequestScoped
public final class LoadSaltFilter implements Filter {
	private static boolean crossContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		crossContext = Optional.ofNullable(filterConfig.getInitParameter("crossContext")).map(Boolean::valueOf)
				.orElseGet(() -> Boolean.FALSE);
	}

	@Inject
	private ISaltProvider provider;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Assume its HTTP
		HttpServletRequest httpReq = (HttpServletRequest) request;
		// Check the user session for the salt cache, if none is present we create one
		@SuppressWarnings("unchecked")
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) (crossContext
				? httpReq.getServletContext().getAttribute(Constants.CSRF_ATTRIBUTE_NAME)
				: httpReq.getSession().getAttribute(Constants.CSRF_ATTRIBUTE_NAME));

		if (csrfPreventionSaltCache == null) {
			csrfPreventionSaltCache = CacheBuilder.newBuilder().maximumSize(5000).expireAfterWrite(20, TimeUnit.MINUTES)
					.build();
			if (crossContext) {
				httpReq.getServletContext().setAttribute(Constants.CSRF_ATTRIBUTE_NAME, csrfPreventionSaltCache);
			} else {
				httpReq.getSession().setAttribute(Constants.CSRF_ATTRIBUTE_NAME, csrfPreventionSaltCache);
			}
		}
		// Generate the salt and store it in the users cache
		String salt = provider.getSalt();
		csrfPreventionSaltCache.put(salt, Boolean.TRUE);
		// Add the salt to the current request so it can be used
		// by the page rendered in this request
		httpReq.setAttribute(Constants.CSRF_PARAMETER, salt);
		chain.doFilter(request, response);
	}
}