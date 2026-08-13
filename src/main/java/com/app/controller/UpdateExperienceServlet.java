package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import org.hibernate.Session;

import com.app.model.Experience;
import com.app.util.HibernateUtil;

@WebServlet("/updateExperience")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 15 * 1024 * 1024
)
public class UpdateExperienceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Experience exp = session.get(Experience.class, id);

        exp.setCompany(request.getParameter("company"));
        exp.setCandidateName(request.getParameter("candidateName"));
        exp.setRound(request.getParameter("round"));
        exp.setExperienceText(request.getParameter("experienceText"));
        exp.setGuidance(request.getParameter("guidance"));
        exp.setMaterialLink(request.getParameter("materialLink"));

 
        // FILE UPDATE
      
        Part filePart = request.getPart("materialFile");

        if (filePart != null && filePart.getSize() > 0) {

            String originalFileName =
                    Paths.get(filePart.getSubmittedFileName())
                         .getFileName()
                         .toString();

            String lowerFileName = originalFileName.toLowerCase();

            // File Type Validation
            if (!(lowerFileName.endsWith(".pdf")
                    || lowerFileName.endsWith(".doc")
                    || lowerFileName.endsWith(".docx")
                    || lowerFileName.endsWith(".ppt")
                    || lowerFileName.endsWith(".pptx"))) {

                request.setAttribute("error",
                        "Only PDF, DOC, DOCX, PPT and PPTX files are allowed.");

                request.setAttribute("exp", exp);

                RequestDispatcher rd =
                        request.getRequestDispatcher("jsp/editExperience.jsp");

                rd.forward(request, response);
                return;
            }

            // File Size Validation
            if (filePart.getSize() > 10 * 1024 * 1024) {

                request.setAttribute("error",
                        "Maximum allowed file size is 10 MB.");

                request.setAttribute("exp", exp);

                RequestDispatcher rd =
                        request.getRequestDispatcher("jsp/editExperience.jsp");

                rd.forward(request, response);
                return;
            }

            // Delete Old File
            if (exp.getUploadedFile() != null &&
                !exp.getUploadedFile().trim().isEmpty()) {

                File oldFile =
                        new File("C:\\InterviewHubUploads",
                                exp.getUploadedFile());

                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }

            // Save New File
            String uniqueFileName =
                    UUID.randomUUID().toString()
                    + "_"
                    + originalFileName;

            String uploadPath = "C:\\InterviewHubUploads";

            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            filePart.write(uploadPath + File.separator + uniqueFileName);

            exp.setUploadedFile(uniqueFileName);
            exp.setOriginalFileName(originalFileName);
        }

        session.update(exp);

        session.getTransaction().commit();
        session.close();

        response.sendRedirect("viewExperience");
    }
}