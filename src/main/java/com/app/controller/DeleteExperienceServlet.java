package com.app.controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.app.model.Experience;
import com.app.util.HibernateUtil;

@WebServlet("/deleteExperience")
public class DeleteExperienceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Experience exp = session.get(Experience.class, id);

        if (exp != null) {

            // Delete uploaded file if it exists
            if (exp.getUploadedFile() != null &&
                !exp.getUploadedFile().trim().isEmpty()) {

                File file = new File(
                        "C:\\InterviewHubUploads",
                        exp.getUploadedFile());

                if (file.exists()) {
                    file.delete();
                }
            }

            // Delete database record
            session.delete(exp);
        }

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("viewExperience");
    }
}