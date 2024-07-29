package db;

import entity.Group;
import entity.Student;
import services.StringService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

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
}
