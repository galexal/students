package db;

import entity.*;
import services.StringService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    public static final String TERM_NAME = "term";
    public static final String GRADE_VALUE = "value";
    public static final String DISCIPLINE_ID = "id_discipline";
    public static final String DISCIPLINE_NAME = "discipline";
    private static Statement statement;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String DATE = "date";
    private static final String GROUP = "group";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students" +
                    "?user=root&password=P@$5w0rd");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAllActiveStudents() {
        List<Student> students = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT s.id, surname, name, date, g.group " +
                    "FROM students.student AS s\n" +
                    "JOIN groupp AS g ON s.id_group=g.id\n" +
                    "WHERE status='1';");
            while (result.next()) {
                Student student = new Student();
                student.setId(result.getInt(ID));
                student.setName(result.getString(NAME));
                student.setSurname(result.getString(SURNAME));
                student.setDate(result.getDate(DATE));

                Group group = new Group();
                group.setName(result.getString(GROUP));
                student.setGroup(group);

                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Term> getAllActiveTerms() {
        List<Term> terms = new ArrayList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT id, term FROM students.term WHERE status='1';");
            while (result.next()) {
                Term term = new Term();
                term.setId(result.getInt(ID));
                term.setName(result.getString(TERM_NAME));
                terms.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms;
    }

    public static int getGroupId(String groupName) {
        try {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM students.groupp AS g " +
                    "WHERE g.group='%s';", groupName));

            while (resultSet.next()) {
                return resultSet.getInt(ID);
            }

            statement.execute(String.format("INSERT INTO groupp (`group`) VALUES ('%s');", groupName));
            resultSet = statement.executeQuery(String.format("SELECT id FROM students.groupp AS g " +
                    "WHERE g.group='%s';", groupName));

            while (resultSet.next()) {
                return resultSet.getInt(ID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Ошибка создания группы");
    }

    public static void createStudent(String surname, String name, int groupId, String date) {
        try {
            statement.execute(String.format(
                    "INSERT INTO student (`surname`,`name`,`id_group`,`date`) VALUES ('%s', '%s', '%d', '%s');",
                    surname, name, groupId, date));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteStudents(String[] ids) {
        try {
            statement.execute(String.format("UPDATE `students`.`student` SET `status` = '0' WHERE `id` IN (%s);",
                    StringService.convertIdsIntoString(ids)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Student getStudentById(String id) {
        Student student = new Student();
        try {
            ResultSet result = statement.executeQuery(String.format("SELECT s.id, surname, name, date, g.group " +
                    "FROM students.student AS s JOIN groupp AS g ON s.id_group=g.id WHERE s.id='%s';", id));
            while (result.next()) {
                student.setId(result.getInt(ID));
                student.setName(result.getString(NAME));
                student.setSurname(result.getString(SURNAME));
                student.setDate(result.getDate(DATE));

                Group group = new Group();
                group.setName(result.getString(GROUP));
                student.setGroup(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public static void modifyStudent(String id, String surname, String name, int groupId, String date) {
        try {
            statement.execute(String.format(
                    "UPDATE `students`.`student` SET `surname` = '%s', `name` = '%s', `id_group` = '%d', " +
                            "`date` = '%s' WHERE (`id` = '%s');",
                    surname, name, groupId, date, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Grade> getGradesByStudentAndTermId(String studentId, String termId) {
        List<Grade> grades = new ArrayList<>();
        try {
ResultSet resultSet = statement.executeQuery(String.format("SELECT id_discipline, discipline, value FROM students.grade as g " +
        "JOIN term_discipline as td on g.id_term_discipline = td.id " +
        "JOIN discipline as d on td.id_discipline = d.id " +
        "WHERE g.id_student = '%s' AND td.id_term = '%s';", studentId, termId));
            while (resultSet.next()) {
                Grade grade = new Grade();
                grade.setValue(resultSet.getInt(GRADE_VALUE));
                Discipline discipline = new Discipline();
                discipline.setId(resultSet.getInt(DISCIPLINE_ID));
                discipline.setName(resultSet.getString(DISCIPLINE_NAME));
                grade.setDiscipline(discipline);
                grades.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grades;
    }
}
