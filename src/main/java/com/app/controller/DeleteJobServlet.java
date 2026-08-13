package com.app.controller;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.hibernate.Session;

import com.app.model.Job;
import com.app.util.HibernateUtil;

@WebServlet("/deleteJob")
public class DeleteJobServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Job job = session.get(Job.class, id);
        if (job != null) {
            session.delete(job);
        }

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("job");
    }
}