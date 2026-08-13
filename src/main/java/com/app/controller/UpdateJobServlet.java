package com.app.controller;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.hibernate.Session;

import com.app.model.Job;
import com.app.util.HibernateUtil;

@WebServlet("/updateJob")
public class UpdateJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Job job = session.get(Job.class, id);

        job.setCompany(request.getParameter("company"));
        job.setRole(request.getParameter("role"));
        job.setLink(request.getParameter("link"));

        session.update(job);

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("job");
    }
}