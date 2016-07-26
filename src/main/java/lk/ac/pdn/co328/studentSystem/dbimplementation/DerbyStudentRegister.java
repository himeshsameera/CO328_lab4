/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ac.pdn.co328.studentSystem.dbimplementation;

import java.lang.*;
import java.sql.*;
import java.util.ArrayList;
import lk.ac.pdn.co328.studentSystem.Student;
import lk.ac.pdn.co328.studentSystem.StudentRegister;

/**
 *
 * @author himesh
 */
public class DerbyStudentRegister extends StudentRegister {

    Connection connection = null;
    public DerbyStudentRegister()
    {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        }
        catch(java.lang.ClassNotFoundException e) {
        System.err.print("ClassNotFoundException:");
        System.err.println(e.getMessage());
        }
        try{
            String dbURL1 = "jdbc:derby:\\db\\studentDB.db;create=true";
            connection = DriverManager.getConnection(dbURL1);
            if (connection != null)
            {
                String SQL_CreateTable = "CREATE TABLE Register(id INT , fname VARCHAR(24), lname VARCHAR(24))";
                System.out.println ( "Creating table addresses..." );
                try 
                {
                    Statement stmnt = connection.createStatement();
                    stmnt.execute( SQL_CreateTable );
                    stmnt.close();
                    System.out.println("Table created");
                } catch ( SQLException e )
                {
                    System.out.println(e);
                }
               System.out.println("Connected to database");
            }
            else
            {
                throw new SQLException("Connection Failed");
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void addStudent(Student st) throws Exception {
        if (connection != null)
        {
            String SQL_AddStudent = "INSERT INTO Register VALUES (" + st.getId() + ",'" + st.getFirstName() +"','" + st.getLastName() + "')";
            System.out.println ( "Adding the student..." + SQL_AddStudent);

            Statement stmnt = connection.createStatement();
            stmnt.execute(SQL_AddStudent);
            stmnt.close();
            System.out.println("Student Added");

        }
        else
        {
            throw new Exception("Database Connection Error");
        }
    }

    @Override
    public void removeStudent(int regNo) throws Exception  {
        if (connection != null)
        {
            String SQL_DelStudent = "DELETE FROM Register WHERE id = " + regNo;
            System.out.println ( "Deleting the student..." + SQL_DelStudent);

            Statement stmnt = connection.createStatement();
            stmnt.execute(SQL_DelStudent);
            stmnt.close();
            System.out.println("Student Deleted");
        }
        else
        {
            throw new Exception("Database Connection Error");
        }
    }

    @Override
    public Student findStudent(int regNo) throws Exception  {
        if (connection != null)
        {
            String SQL_FindStudent = "SELECT * FROM Register WHERE id = " + regNo;
            System.out.println ( "Finding the student..." + SQL_FindStudent);

            Statement stmnt = connection.createStatement();
            ResultSet students = stmnt.executeQuery(SQL_FindStudent);
            System.out.println("Student Found");
            Student st = null;
            if (students.next()) {
                st = new Student(students.getInt(1), students.getString(2), students.getString(3));
            }
            System.out.println("Parsing OK");
            stmnt.close();
            return st;
        }
        else
        {
            throw new Exception("Database Connection Error");
        }
    }

    @Override
    public void reset() throws Exception  {
        if (connection != null)
        {
            String SQL_DelStudent = "DELETE FROM Register";
            System.out.println ( "Deleting all students..." + SQL_DelStudent);

            Statement stmnt = connection.createStatement();
            stmnt.execute(SQL_DelStudent);
            stmnt.close();
            System.out.println("All Students Deleted");

        }
        else
        {
            throw new Exception("Database Connection Error");
        }
    }

    @Override
    public ArrayList<Student> findStudentsByName(String name) throws Exception {
        ArrayList<Student> students = new ArrayList<Student>();
        if (connection != null){

            String SQL_FindStudent = "SELECT * FROM Register WHERE fname = " + name;
            Statement stmnt = connection.createStatement();
            ResultSet studentSet = stmnt.executeQuery(SQL_FindStudent);
            while(studentSet.next())
            {
                students.add(new Student(studentSet.getInt(1), studentSet.getString(2), studentSet.getString(3)));
            }
            stmnt.close();

            SQL_FindStudent = "SELECT * FROM Register WHERE lname = " + name;
            stmnt = connection.createStatement();
            studentSet = stmnt.executeQuery(SQL_FindStudent);
            System.out.println("Students Found");
            while(studentSet.next())
            {
                students.add(new Student(studentSet.getInt(1), studentSet.getString(2), studentSet.getString(3)));
            }
            stmnt.close();
        }
        else
        {
            throw new Exception("Database Connection Error");
        }
        return students;
    }

    @Override
    public ArrayList<Integer> getAllRegistrationNumbers()throws Exception  {
        ArrayList<Integer> students = new ArrayList<Integer>();
        if (connection != null){

            String SQL_FindStudent = "SELECT id FROM Register";
            Statement stmnt = connection.createStatement();
            ResultSet studentSet = stmnt.executeQuery(SQL_FindStudent);
            while(studentSet.next())
            {
                students.add(studentSet.getInt(1));
            }
            stmnt.close();
        }
        else
        {
            throw new Exception("Database Connection Error");
        }
        return students;
    }
    
}
