package com.app.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.hibernate.Session;

import com.app.model.Experience;
import com.app.util.HibernateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        Session session = HibernateUtil.getSessionFactory().openSession();

        Experience exp = session.get(Experience.class, id);

        session.close();

        if (exp == null || exp.getUploadedFile() == null) {
            res.getWriter().println("File not found.");
            return;
        }

        String uploadPath = "C:\\InterviewHubUploads";

        File file = new File(uploadPath, exp.getUploadedFile());

        if (!file.exists()) {
            res.getWriter().println("File does not exist.");
            return;
        }

        res.setContentType("application/octet-stream");

        res.setHeader(
                "Content-Disposition",
                "attachment; filename=\"" + exp.getOriginalFileName() + "\"");

        FileInputStream fis = new FileInputStream(file);

        OutputStream os = res.getOutputStream();

        byte[] buffer = new byte[4096];

        int bytesRead;

        while ((bytesRead = fis.read(buffer)) != -1) {

            os.write(buffer, 0, bytesRead);

        }

        fis.close();
        os.flush();
    }
}