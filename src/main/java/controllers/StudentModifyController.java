package controllers;

import db.DBManager;
import entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.DateService;

import java.io.IOException;

@WebServlet(name = "StudentModifyController", urlPatterns = "/student_modify")
public class StudentModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("idForModify");
        Student student = DBManager.getStudentById(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("WEB-INF/jsp/student_modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String groupName = req.getParameter("group");
        String date = req.getParameter("date");
        String id = req.getParameter("id");

        if (surname.equals("") || name.equals("") || groupName.equals("") || date.equals("")) {
            req.setAttribute("error", 1);
            req.getRequestDispatcher("WEB-INF/jsp/student_modify.jsp").forward(req, resp);
            return;
        }
        String dateForDb = DateService.convertDateForDb(date);
        int groupId = DBManager.getGroupId(groupName);
        DBManager.modifyStudent(id, surname, name, groupId, dateForDb);
        resp.sendRedirect("/students");
    }
}
