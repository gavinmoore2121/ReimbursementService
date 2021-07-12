package com.revature.servlets;

import com.revature.core.ERSService;
import com.revature.entity.definitions.Employee;
import com.revature.entity.definitions.Reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ViewEmployeeReimbursements extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder reimJSON = new StringBuilder();
        reimJSON.append("{ \"reimbursements\" : [ ");
        resp.setContentType("text/JSON");
        String empUsername = reader.readLine();
        Employee e = ERSService.getERSService().getEmployee(empUsername);
        // If no input, return list of all reimbursements
        List<Reimbursement> reimList = ERSService.getERSService().getAllEmpReimbursements(e);
        for (Reimbursement reim: reimList) {
            reimJSON.append(reim.toJson());
            reimJSON.append(", ");
        }
        // Replace comma on last entry with end bracket.
        reimJSON.delete(reimJSON.length()-2, reimJSON.length()-1);
        reimJSON.append("] } ");
        resp.getWriter().append(reimJSON);
        // If ReimbursementID is given, return the corresponding reimbursement.
    }
}
