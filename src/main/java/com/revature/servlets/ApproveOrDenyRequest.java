package com.revature.servlets;

import com.revature.core.ERSService;
import com.revature.entity.definitions.Manager;
import com.revature.entity.definitions.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ApproveOrDenyRequest extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Parse input
        BufferedReader reader = req.getReader();
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");

        String status = reader.readLine();
        int reimID = Integer.parseInt(reader.readLine());
        String managerUsername = reader.readLine();
        String date = reader.readLine();

        Reimbursement reim = ERSService.getERSService().getReimbursement(reimID);
        reim.setApprovalStatus(status);
        reim.setReviewedBy((Manager) ERSService.getERSService().getEmployee(managerUsername));
        reim.setDateReviewed(date);
        ERSService.getERSService().updateReimbursement(reim);
        out.println("Updated.");
    }
}
