/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ac.pdn.co328.studentSystem;

import java.util.ArrayList;

/**
 *
 * @author himesh
 */
public abstract class StudentRegister 
{
    
    // Adds a new student to the system
    public abstract void addStudent(Student st) throws Exception ;

    // Remove a student from the system
    public abstract void removeStudent(int regNo)throws Exception ;

    //Finds the student with the given registration number
    public abstract Student findStudent(int regNo)throws Exception ;

    // Cleans all the data from the student register
    public abstract void reset()throws Exception ;

    // Finds all the students that has the given name as a part of their name.
    public abstract ArrayList<Student> findStudentsByName(String name)throws Exception ;

    //Gives all the registration numbers of the students.
    public abstract ArrayList<Integer> getAllRegistrationNumbers()throws Exception ;
}
