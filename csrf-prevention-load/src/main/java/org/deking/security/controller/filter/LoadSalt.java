package org.deking.security.controller.filter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;

public final class LoadSalt implements Filter {
	private boolean crossContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		crossContext = Optional.ofNullable(filterConfig.getInitParameter("crossContext")).map(Boolean::valueOf)
				.orElseGet(() -> false);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Assume its HTTP
		HttpServletRequest httpReq = (HttpServletRequest) request;
		// Check the user session for the salt cache, if none is present we create one
		@SuppressWarnings("unchecked")
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) (crossContext
				? httpReq.getServletContext().getAttribute("csrfPreventionSaltCache")
				: httpReq.getSession().getAttribute("csrfPreventionSaltCache"));

		if (csrfPreventionSaltCache == null) {
			csrfPreventionSaltCache = CacheBuilder.newBuilder().maximumSize(5000).expireAfterWrite(20, TimeUnit.MINUTES)
					.build();
			if (crossContext) {
				httpReq.getServletContext().setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);
			} else {
				httpReq.getSession().setAttribute("csrfPreventionSaltCache", csrfPreventionSaltCache);
			}
		}
		// Generate the salt and store it in the users cache
		String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
		csrfPreventionSaltCache.put(salt, Boolean.TRUE);
		// Add the salt to the current request so it can be used
		// by the page rendered in this request
		httpReq.setAttribute("csrfPreventionSalt", salt);
		chain.doFilter(request, response);
	}
}