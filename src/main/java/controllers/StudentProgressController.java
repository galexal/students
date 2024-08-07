package controllers;

import db.DBManager;
import entity.Discipline;
import entity.Grade;
import entity.Student;
import entity.Term;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentProgressController", urlPatterns = "/student_progress")
public class StudentProgressController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("idForProgress");
        String termId = req.getParameter("termId");

        Student student = DBManager.getStudentById(studentId);

        List<Term> terms = DBManager.getAllActiveTerms();

        Term selectedTerm = new Term();
        if (termId != null && !termId.isEmpty()) {
            for (Term term : terms) {
                if (termId.equals(String.valueOf(term.getId()))) {
                    selectedTerm = term;
                    break;
                }
            }
        } else {
            selectedTerm = terms.get(0);
        }

        List<Grade> grades = DBManager.getGradesByStudentAndTermId(studentId, String.valueOf(selectedTerm.getId()));
        if (grades.isEmpty()) {
            List<Discipline> disciplines = DBManager.getDisciplinesByTermId(String.valueOf(selectedTerm.getId()));
            for (Discipline discipline : disciplines) {
                Grade grade = new Grade();
                grade.setDiscipline(discipline);
                grade.setValue(-1);
                grades.add(grade);
            }
        }

        req.setAttribute("avgGrade", getAverageRate(grades));
        req.setAttribute("student", student);
        req.setAttribute("terms", terms);
        req.setAttribute("selectedTerm", selectedTerm);
        req.setAttribute("grades", grades);
        req.getRequestDispatcher("WEB-INF/jsp/student_progress.jsp").forward(req, resp);
    }

    private double getAverageRate(List<Grade> grades) {
        int sum = 0;
        for (Grade grade : grades) {
            sum += grade.getValue();
        }
        double result = sum / (double) grades.size();
        if (result == -1) {
            return 0;
        }
        return result;
    }
}
