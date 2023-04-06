package org.deking.security.controller.servlet;

import java.io.IOException;
import java.util.function.BiPredicate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.cache.Cache;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/csrf/verify")
public final class CsrfValidator extends HttpServlet implements BiPredicate<ServletContext, String> {
	@Override
	public boolean test(ServletContext servletContext, String salt) {
		@SuppressWarnings("unchecked")
		Cache<String, Boolean> csrfPreventionSaltCache = (Cache<String, Boolean>) servletContext
				.getAttribute("csrfPreventionSaltCache");
		return csrfPreventionSaltCache != null && salt != null
				&& ((Cache<String, Boolean>) csrfPreventionSaltCache).getIfPresent(salt) != null;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print(test(req.getServletContext(), req.getParameter("salt")));
	}
}