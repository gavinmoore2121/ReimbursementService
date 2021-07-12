package com.revature.servlets;

import com.revature.core.ERSService;
import com.revature.entity.definitions.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ViewEmployees extends HttpServlet {

    private static final long serialVersionUID = -3368351874490410263L;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder empJSON = new StringBuilder();
        empJSON.append("{ \"employees\" : [ ");
        resp.setContentType("text/JSON");
        String empUsername = reader.readLine();
        // If no input, return list of all employees.
        if (empUsername == null) {
             List<Employee> empList = ERSService.getERSService().getAllEmployees();
             for (Employee emp: empList) {
                 empJSON.append(emp.toJson());
                 empJSON.append(", ");
             }
             // Replace comma on last entry with end bracket.
             empJSON.delete(empJSON.length()-2, empJSON.length()-1);
             empJSON.append("] } ");
             resp.getWriter().append(empJSON);
        }
        // If Employee ID is given, return the employee.
        else {
            empJSON.append(ERSService.getERSService().getEmployee(Integer.parseInt(empUsername)).toJson());
            resp.getWriter().println(empJSON);
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder empJSON = new StringBuilder();
        empJSON.append("{ \"employees\" : [ ");
        resp.setContentType("text/JSON");
        String empUsername = reader.readLine();
        // If no input, return list of all employees.
        if (empUsername == null) {
            List<Employee> empList = ERSService.getERSService().getAllEmployees();
            for (Employee emp: empList) {
                empJSON.append(emp.toJson());
                empJSON.append(", ");
            }
            // Replace comma on last entry with end bracket.
            empJSON.delete(empJSON.length()-2, empJSON.length()-1);
        }
        // If Employee username is given, return the employee.
        else {
            empJSON.append(ERSService.getERSService().getEmployee(empUsername).toJson());
        }
        // Add end brackets to JSON and respond.
        empJSON.append("] } ");
        resp.getWriter().append(empJSON);
    }
}