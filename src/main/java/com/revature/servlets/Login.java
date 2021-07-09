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
		BufferedReader reader = req.getReader();
		
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		String username = reader.readLine();
		String password = reader.readLine();
		out.println(username + " " + password);

		switch(ERSService.getERSService().verifyLogin(username, password)) {
			case 1:
				out.println("localhost:8080/EmployeeReimbursementService/EmployeeHome"); // Save cookie of user in JavaScript
			case 2:
				out.println("localhost:8080/EmployeeReimbursementService/ManagerHome");
			case 3:
				out.println("localhost:8080/EmployeeReimbursementService/LoginInvalidUsername");
			case 4:
				out.println("localhost:8080/EmployeeReimbursementService/LoginInvalidPassword");
		}
	}
}