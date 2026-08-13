package com.app.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.app.dao.JobDAO;
import com.app.model.Job;

@WebServlet("/job")
public class JobServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //  Save Job
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String company = request.getParameter("company");
        String role = request.getParameter("role");
        String link = request.getParameter("link");

        Job job = new Job();
        job.setCompany(company);
        job.setRole(role);
        job.setLink(link);

        JobDAO dao = new JobDAO();
        dao.saveJob(job);

        response.sendRedirect("job"); 
    }
    
    
    
    
    
    //  View Jobs
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
        JobDAO dao = new JobDAO();
        List<Job> list = dao.getAllJobs();
        request.setAttribute("jobs", list);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/job.jsp");
        rd.forward(request, response);
    }
}