package com.app.controller;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.hibernate.Session;
import com.app.model.Experience;
import com.app.util.HibernateUtil;

@WebServlet("/editExperience")
public class EditExperienceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        Experience exp = session.get(Experience.class, id);
        session.close();

        request.setAttribute("exp", exp);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/editExperience.jsp");
        rd.forward(request, response);
    }
}