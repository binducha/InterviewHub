package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import com.app.dao.ExperienceDAO;
import com.app.model.Experience;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/shareExperience")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 15 * 1024 * 1024
)
public class ShareExperienceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Experience exp = new Experience();

        exp.setCompany(req.getParameter("company"));
        exp.setCandidateName(req.getParameter("candidateName"));
        exp.setRound(req.getParameter("round"));
        exp.setExperienceText(req.getParameter("experienceText"));
        exp.setGuidance(req.getParameter("guidance"));
        exp.setMaterialLink(req.getParameter("materialLink"));

       
        // FILE UPLOAD
   

        Part filePart = req.getPart("materialFile");

        if (filePart != null && filePart.getSize() > 0) {

            String originalFileName =
                    Paths.get(filePart.getSubmittedFileName())
                            .getFileName()
                            .toString();

           
            // FILE TYPE VALIDATION
           

            String lowerFileName = originalFileName.toLowerCase();

            if (!(lowerFileName.endsWith(".pdf")
                    || lowerFileName.endsWith(".doc")
                    || lowerFileName.endsWith(".docx")
                    || lowerFileName.endsWith(".ppt")
                    || lowerFileName.endsWith(".pptx"))) {

                req.setAttribute("error",
                        "Only PDF, DOC, DOCX, PPT and PPTX files are allowed.");

                RequestDispatcher rd =
                        req.getRequestDispatcher("/jsp/share.jsp");

                rd.forward(req, res);
                return;
            }

           
            // FILE SIZE VALIDATION
            

            if (filePart.getSize() > 10 * 1024 * 1024) {

                req.setAttribute("error",
                        "Maximum allowed file size is 10 MB.");

                RequestDispatcher rd =
                        req.getRequestDispatcher("/jsp/share.jsp");

                rd.forward(req, res);
                return;
            }

           
            // SAVE FILE
             
 
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

        ExperienceDAO dao = new ExperienceDAO();
        dao.saveExperience(exp);

        res.sendRedirect("viewExperience");
    }
}