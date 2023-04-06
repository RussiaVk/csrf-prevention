package org.deking.security.controller.filter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.function.Predicate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;

public final class ValidateSalt implements Filter, Predicate<String> {

	private static final Logger LOGGER;
	static {
		LOGGER = LoggerFactory.getLogger(ValidateSalt.class);
	}
	private String verifySaltEndpoint;
	private static HttpClient HTTPCLIENT;

	public void init(FilterConfig filterConfig) throws ServletException {
		verifySaltEndpoint = Optional.ofNullable(filterConfig.getInitParameter("verify-endpoint"))
				.orElseGet(() -> null);
		if (verifySaltEndpoint != null) {
			HTTPCLIENT = HttpClient.newHttpClient();
		}
	}

	@Override
	public boolean test(String salt) {
		try {
			URI uri = URI.create(verifySaltEndpoint + "?salt=" + salt);
			String resp = HTTPCLIENT
					.send(HttpRequest.newBuilder().uri(uri).GET().build(), HttpResponse.BodyHandlers.ofString()).body();
			return Boolean.valueOf(resp);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Assume its HTTP
		HttpServletRequest httpReq = (HttpServletRequest) request;
		// Get the salt sent with the request
		String salt = (String) httpReq.getParameter("csrfPreventionSalt");
		// Validate that the salt is in the cache
		boolean r;
		if (verifySaltEndpoint != null) {
			r = test(salt);
		} else {
			Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) httpReq.getSession()
					.getAttribute("csrfPreventionSaltCache");
			r = csrfPreventionSaltCache != null && salt != null
					&& ((Cache<String, Boolean>) csrfPreventionSaltCache).getIfPresent(salt) != null;
		}
		if (r) {
			// If the salt is in the cache, we move on
			chain.doFilter(request, response);
		} else {
			HttpServletResponse httpResponse = ((HttpServletResponse) response);
			httpResponse.sendError(401, "csrf validate fail!");
		}
	}
}