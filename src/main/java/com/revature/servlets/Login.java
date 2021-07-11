package com.revature.servlets;

import com.revature.core.ERSService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {
	
	private static final long serialVersionUID = -3368351874490410263L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Read user input
		BufferedReader reader = req.getReader();
		String username = reader.readLine();
		String password = reader.readLine();

		// Check output, validate.
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		out.println(ERSService.getERSService().verifyLogin(username, password));
	}
}