package lk.ac.pdn.co328.studentSystem.arraylistimplementation;
import java.util.ArrayList;
import lk.ac.pdn.co328.studentSystem.Student;
import lk.ac.pdn.co328.studentSystem.StudentRegister;

public class ArraylistStudentRegister extends StudentRegister
{
    private ArrayList<Student> studentList = new ArrayList<Student>();

    // Adds a new student to the system
    @Override
    public void addStudent(Student st) throws Exception {
        for (Student student:studentList)
        {
            if(student.getId() == st.getId())
            {
                throw new Exception("StudentID already exists in the register");
            }
        }
        studentList.add(st);
    }

    // Remove a student from the system
    @Override
    public void removeStudent(int regNo)
    {
        for (int i = 0; i<studentList.size(); i++)
        {
            if(studentList.get(i).getId() == regNo)
            {
                studentList.remove(i);
            }
        }
    }

    //Finds the student with the given registration number
    @Override
    public Student findStudent(int regNo)
    {
        for (int i = 0; i<studentList.size(); i++)
        {
            if(studentList.get(i).getId() == regNo)
            {
                return studentList.get(i);
            }
        }
        return  null;
    }

    // Cleans all the data from the student register
    @Override
    public void reset()
    {
        studentList = null;
    }

    // Finds all the students that has the given name as a part of their name.
    @Override
    public ArrayList<Student> findStudentsByName(String name)
    {
        ArrayList<Student> students = new ArrayList<Student>();
        for (int i = 0; i<studentList.size(); i++)
        {
            if(studentList.get(i).getFirstName().contains(name))
            {
                studentList.add(studentList.get(i));
            }
			
			
            if(studentList.get(i).getLastName().contains(name))
            {
                studentList.add(studentList.get(i));
            }
        }
        return students;
    }

    //Gives all the registration numbers of the students.
    @Override
    public ArrayList<Integer> getAllRegistrationNumbers()
    {
        ArrayList<Integer> regNumbers = new ArrayList<Integer>();
        for (Student student: studentList)
        {
            regNumbers.add(student.getId());
        }
        return  regNumbers;
    }
}
