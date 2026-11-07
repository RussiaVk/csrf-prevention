package org.deking.security.controller.filter;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.function.BiPredicate;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.function.FailablePredicate;
import org.deking.security.CSRFEndpoint;
import org.deking.security.CSRFReq;
import org.deking.security.CSRFResp;
import org.deking.security.constant.Constants;

import com.google.common.cache.Cache;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jaxrs.FormEncoder;
import feign.jaxrs2.JAXRS2Contract;

public final class ValidateSaltFilter implements Filter, FailablePredicate<CSRFReq, IOException> {

	private Optional<String> endpoint;
	@Inject
	private BiPredicate<Cache<String, Boolean>, String> csrfValidator;

	public void init(FilterConfig filterConfig) throws ServletException {
		endpoint = Optional.ofNullable(filterConfig.getInitParameter("endpoint"));
	}

	@Override
	public boolean test(CSRFReq req) throws IOException {
		CSRFEndpoint CsrfEndpoint = Feign.builder().contract(new JAXRS2Contract()).encoder(new FormEncoder())
				.decoder(new JacksonDecoder()).target(CSRFEndpoint.class, endpoint.get());

		CSRFResp resp = CsrfEndpoint.verifyCSRF(req);
		return resp.data();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Assume its HTTP
		HttpServletRequest httpReq = (HttpServletRequest) request;
		// Get the salt sent with the request
		String salt = (String) httpReq.getParameter(Constants.CSRF_PARAMETER);
		// Validate that the salt is in the cache
		boolean result;
		if (endpoint.isPresent()) {
			CSRFReq req = new CSRFReq(new URL(request.getServerName()), salt);
			result = test(req);
		} else {
			Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) ((HttpServletRequest) request)
					.getSession().getAttribute(Constants.CSRF_ATTRIBUTE_NAME);
			result = csrfValidator.test(csrfPreventionSaltCache, salt);
		}
		if (result) {
			// If the salt is in the cache, we move on
			chain.doFilter(request, response);
		} else {
			HttpServletResponse httpResponse = ((HttpServletResponse) response);
			httpResponse.sendError(401, "csrf validate fail!");
		}
	}
}