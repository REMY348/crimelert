/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alert.controller;

import com.alert.model.ReportBean;
import com.alert.model.ReportDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raimi
 */
public class ViewReportServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
     Object userIdAttribute = request.getSession().getAttribute("cid");

     response.getWriter().println(userIdAttribute);
        if (userIdAttribute != null) {
            // Assuming "userId" is a valid integer, you may need to cast it accordingly
            int userId = Integer.parseInt(userIdAttribute.toString());

            // Use ReportDAO to retrieve all reports for the user
            List<ReportBean> reports = ReportDAO.getAllReportsUser(userId);
//            response.getWriter().println(reports);
            if (!reports.isEmpty()) {
                // Set the list of reports as an attribute in the request
                request.setAttribute("reports", reports);

                // Forward the request to the JSP page for displaying the reports
                request.getRequestDispatcher("/viewReport.jsp").forward(request, response);
            } else {
                // Handle the case where no reports are found for the user
                response.getWriter().println("No reports found for the user.");
            }
        } else {
            // Handle the case where the "userId" session attribute is not present
            response.getWriter().println("Invalid or missing session attribute (userId).");
        }
    }
}
    

