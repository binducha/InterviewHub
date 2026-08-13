package com.app.controller;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.hibernate.Session;

import com.app.model.Job;
import com.app.util.HibernateUtil;

@WebServlet("/editJob")
public class EditJobServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        Job job = session.get(Job.class, id);
        session.close();

        request.setAttribute("job", job);

        RequestDispatcher rd = request.getRequestDispatcher("jsp/editJob.jsp");
        rd.forward(request, response);
    }
}