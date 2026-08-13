package com.app.controller;

import java.io.IOException;

import com.app.dao.UserDAO;
import com.app.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        UserDAO dao = new UserDAO();

        boolean result = dao.registerUser(user);

        if (result) {

            response.sendRedirect("index.jsp");

        } else {

            request.setAttribute("error", "Registration Failed!");

            RequestDispatcher rd =
                    request.getRequestDispatcher("register.jsp");

            rd.forward(request, response);
        }
    }
}