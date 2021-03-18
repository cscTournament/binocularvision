package by.gourianova.binocularvision.controller.command.impl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userPhotoServlet")
public class UserPhotoServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/logo_large.jpg");
        view.forward(request, response);
    }
}
