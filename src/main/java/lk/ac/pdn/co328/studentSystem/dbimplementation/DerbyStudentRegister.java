/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ac.pdn.co328.studentSystem.dbimplementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import lk.ac.pdn.co328.studentSystem.Student;
import lk.ac.pdn.co328.studentSystem.StudentRegister;

/**
 *
 * @author himesh
 */
public class DerbyStudentRegister extends StudentRegister {

    Connection connection = null;
    public DerbyStudentRegister() throws SQLException
    {
            String dbURL1 = "jdbc:derby:codejava/studentDB;create=true";
            connection = DriverManager.getConnection(dbURL1);
            if (connection != null)
            {
                String SQL_CreateTable = "CREATE TABLE Students(id INT , name VARCHAR(24))";
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
    
    @Override
    public void addStudent(Student st) throws Exception {
        if (connection != null)
        {
            String SQL_AddStudent = "INSERT INTO Students VALUES (" + st.getId() + ",'" + st.getFirstName() + "')";
            System.out.println ( "Adding the student..." + SQL_AddStudent);

            Statement stmnt = connection.createStatement();
            stmnt.execute(SQL_AddStudent );
            stmnt.close();
            System.out.println("Student Added");

        }
        else
        {
            throw new Exception("Database Connection Error");
        }
    }

    @Override
    public void removeStudent(int regNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Student findStudent(int regNo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Student> findStudentsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getAllRegistrationNumbers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
