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
    // FIXME: Maybe you can remove some unused methods here and add a modify student section ??
    
    // Adds a new student to the system
    public abstract void addStudent(Student st) throws Exception ;

    // Remove a student from the system
    public abstract void removeStudent(int regNo);

    //Finds the student with the given registration number
    public abstract Student findStudent(int regNo);

    // Cleans all the data from the student register
    public abstract void reset();

    // Finds all the students that has the given name as a part of their name.
    public abstract ArrayList<Student> findStudentsByName(String name);

    //Gives all the registration numbers of the students.
    public abstract ArrayList<Integer> getAllRegistrationNumbers();
}
