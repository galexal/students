package controllers;

import db.DBManager;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Role> allRoles = DBManager.getAllRoles();
        req.setAttribute("roles", allRoles);
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String roleId = req.getParameter("roleId");

        if (DBManager.isAuthorised(login, password, roleId)) {
            req.getSession().setAttribute("isAuthorised", "true");
            req.getSession().setAttribute("role", roleId);
            req.getSession().setAttribute("login", login);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("error", 1);
            List<Role> roles = DBManager.getAllRoles();
            req.setAttribute("roles", roles);
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req,resp);
        }
    }
}
