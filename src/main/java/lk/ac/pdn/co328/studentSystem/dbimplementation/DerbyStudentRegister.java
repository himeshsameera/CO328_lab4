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
            String dbURL1 = "jdbc:derby:\\codejava\\studentDB.db;create=true";
            connection = DriverManager.getConnection(dbURL1);
            if (connection != null)
            {
                //creating the table
                String SQL_CreateTable = "CREATE TABLE Register(id INT , fname VARCHAR(24), lname VARCHAR(24))";

                try
                {
                    Statement cmd = connection.createStatement();
                    cmd.execute( SQL_CreateTable );
                    cmd.close();

                }
                catch ( SQLException e )
                {
                    System.out.println(e);
                }
                System.out.println("DATABASE CONNECTION IS SUCCESSFUL");
            }
            else
            {
                throw new SQLException("DATABASE CONNECTION IS NOT SUCCESSFUL");
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
            Student temp=null;
            Statement cmd;

            //since PUT AND POST operation using same addstudent() check again
            temp=findStudent(st.getId());

            if(temp!=null){

                String sqladd = "UPDATE  Register SET fname= '"+st.getFirstName() +"',lname= '"+st.getLastName()+"' where id= "+st.getId();
                cmd = connection.createStatement();
                cmd.execute(sqladd);
                cmd.close();
            }

            else{


                String sqladd = "INSERT INTO Register VALUES (" + st.getId() + ",'" + st.getFirstName() +"','" + st.getLastName() + "')";
                cmd = connection.createStatement();
                cmd.execute(sqladd);
                cmd.close();
            }


        }
        else{
            throw new Exception("DATABASE CONNECTION IS NOT SUCCESSFUL");
        }
    }

    @Override
    public void removeStudent(int regNo) throws Exception  {
        if (connection != null) {
            String sqldel = "DELETE FROM Register WHERE id = " + regNo;
            Statement cmd = connection.createStatement();
            cmd.execute(sqldel);
            cmd.close();
        }
        else {
            throw new Exception("DATABASE CONNECTION IS NOT SUCCESSFUL");
        }
    }



    @Override
    public Student findStudent(int regNo) throws Exception  {
        if (connection != null)
        {
            String sqlfind = "SELECT * FROM Register WHERE id = " + regNo;
            Statement cmd = connection.createStatement();
            ResultSet students = cmd.executeQuery(sqlfind);
            Student st = null;
            if (students.next()) {
                st = new Student(students.getInt(1), students.getString(2), students.getString(3));
            }
            cmd.close();
            return st;
        }
        else {
            throw new Exception("DATABASE CONNECTION IS NOT SUCCESSFUL");
        }
    }


    @Override
    public void reset() throws Exception  {
        if (connection != null)
        {
            String sqldel = "DELETE FROM Register";
            Statement cmd = connection.createStatement();
            cmd.execute(sqldel);
            cmd.close();
        }
        else {
            throw new Exception("DATABASE CONNECTION IS NOT SUCCESSFUL");
        }
    }

    @Override
    public ArrayList<Student> findStudentsByName(String name) throws Exception {
        ArrayList<Student> students = new ArrayList<Student>();
        if (connection != null){

            String sqlfind = "SELECT * FROM Register WHERE fname = " + name;
            Statement cmd = connection.createStatement();
            ResultSet studentSet = cmd.executeQuery(sqlfind);
            while(studentSet.next())
            {
                students.add(new Student(studentSet.getInt(1), studentSet.getString(2), studentSet.getString(3)));
            }
            cmd.close();

            sqlfind = "SELECT * FROM Register WHERE lname = " + name;
            cmd = connection.createStatement();
            studentSet = cmd.executeQuery(sqlfind);
            while(studentSet.next())
            {
                students.add(new Student(studentSet.getInt(1), studentSet.getString(2), studentSet.getString(3)));
            }
            cmd.close();
        }
        else{
            throw new Exception("DATABASE CONNECTION IS NOT SUCCESSFUL");
        }
        return students;
    }

    @Override
    public ArrayList<Integer> getAllRegistrationNumbers()throws Exception  {
        ArrayList<Integer> students = new ArrayList<Integer>();
        if (connection != null){

            String sqlfind = "SELECT id FROM Register";
            Statement cmd = connection.createStatement();
            ResultSet studentSet = cmd.executeQuery(sqlfind);
            while(studentSet.next())
            {
                students.add(studentSet.getInt(1));
            }
            cmd.close();
        }
        else{
            throw new Exception("DATABASE CONNECTION IS NOT SUCCESSFUL");
        }
        return students;
    }

}