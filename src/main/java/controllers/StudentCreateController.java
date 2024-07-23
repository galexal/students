package controllers;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.DateService;

import java.io.IOException;

@WebServlet(name = "StudentCreateController", urlPatterns = "/student_create")
public class StudentCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/student_create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String groupName = req.getParameter("group");
        String date = DateService.convertDateForDb(req.getParameter("date"));

        if (surname.equals("") || name.equals("") || groupName.equals("") || date.equals("")) {
            req.setAttribute("error", 1);
            req.getRequestDispatcher("WEB-INF/jsp/student_create.jsp").forward(req, resp);
            return;
        }
        int groupId = DBManager.getGroupId(groupName);
        DBManager.createStudent(surname, name, groupId, date);
        resp.sendRedirect("/students");
    }
}
