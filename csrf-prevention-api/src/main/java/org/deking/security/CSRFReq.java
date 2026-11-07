package org.deking.security;

import java.net.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;

public record CSRFReq(
		@NotNull @HeaderParam("host") URL host,
		@NotEmpty @FormParam("salt") String salt) {
}