package com.app.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.app.dao.ExperienceDAO;
import com.app.model.Experience;

@WebServlet("/viewExperience")
public class ViewExperienceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String company = request.getParameter("company");
        String candidate = request.getParameter("candidate");
        String round = request.getParameter("round");

        ExperienceDAO dao = new ExperienceDAO();
        List<Experience> list = dao.getFilteredExperiences(company, candidate, round);

        request.setAttribute("list", list);

        RequestDispatcher rd = request.getRequestDispatcher("jsp/view.jsp");
        rd.forward(request, response);
    }
} 