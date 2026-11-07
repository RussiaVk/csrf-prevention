package org.deking.security.controller.api;

import java.net.URL;
import java.util.Set;
import java.util.function.BiPredicate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.deking.security.CSRFEndpoint;
import org.deking.security.CSRFReq;
import org.deking.security.CSRFResp;
import org.deking.security.constant.Constants;
import org.deking.util.JSONResponseFactory;

import com.google.common.cache.Cache;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@SuppressWarnings("serial")
@RequestScoped
@Path("/verify/csrf")
public class CsrfValidatorServlet extends HttpServlet implements CSRFEndpoint {
	@Inject
	private BiPredicate<Cache<String, Boolean>, String> csrfValidator;
	@Context
	private HttpServletRequest request;
	@Inject
	private Jsonb jsonb;
	@Inject
	private Validator validator;

	private boolean checkSameContext(URL host) {
		return request.getServerName().equals(host.getHost());
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(summary = "verify csrf ", tags = { "csrf" }, parameters = {
			@Parameter(in = ParameterIn.QUERY, name = "phoneNumber", description = "phoneNumber", schema = @Schema(implementation = String.class)),
			@Parameter(in = ParameterIn.QUERY, name = "verificationCode", description = "verificationCode", schema = @Schema(implementation = String.class)) })
	@ApiResponse(responseCode = "200", description = "verify csrf success", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)))
	@ApiResponse(responseCode = "500", description = "verify csrf error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class)))
	public CSRFResp verifyCSRF(@BeanParam CSRFReq dto) throws JsonbException {
		String resp;
		Set<ConstraintViolation<Object>> set = validator.validate(dto);
		if (!set.isEmpty()) {
			resp = set.iterator().next().getMessage();
		} else {
			Cache<String, Boolean> csrfPreventionSaltCache;
			boolean sameContext = checkSameContext(dto.host());
			if (sameContext) {
				csrfPreventionSaltCache = (Cache<String, Boolean>) request.getSession()
						.getAttribute(Constants.CSRF_ATTRIBUTE_NAME);
			} else {
				csrfPreventionSaltCache = (Cache<String, Boolean>) getServletContext()
						.getAttribute(Constants.CSRF_ATTRIBUTE_NAME);
			}
			boolean result = csrfValidator.test(csrfPreventionSaltCache, dto.salt());
			if (result) {
				resp = JSONResponseFactory.createSuccess("verify csrf failed!", result);
			} else {
				resp = JSONResponseFactory.createFailure("verify csrf failed!", result);
			}
		}

		return jsonb.fromJson(resp, CSRFResp.class); 

	}
}