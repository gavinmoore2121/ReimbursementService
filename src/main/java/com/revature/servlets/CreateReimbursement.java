package com.revature.servlets;

import com.revature.core.ERSService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateReimbursement extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Parse input
        BufferedReader reader = req.getReader();
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");

        String username = reader.readLine();
        out.println(username);
        double amountRequested = Double.parseDouble(reader.readLine());
        String requestReason = reader.readLine();
        out.println(requestReason);
        String date = reader.readLine();
        out.println(date);

        // Send to ERSService to create and save request.
        ERSService.getERSService().createReimbursement(username, amountRequested, requestReason, date);
        out.println("submitted");
    }
}
