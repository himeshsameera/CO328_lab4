package lk.ac.pdn.co328.studentSystem;
import lk.ac.pdn.co328.studentSystem.arraylistimplementation.ArraylistStudentRegister;
import org.junit.*;
import java.util.ArrayList;

public class StudentRegisterTest {
    ArraylistStudentRegister register;

    @Before
    public void setupTest()
    {
        System.out.println("A new test is starting.");
    }

    @After
    public void finishTest()
    {
        System.out.println("Test finished");
    }

    @BeforeClass
    public  static void beforeClass()
    {
        System.out.println("Evaluating test cases in StudentRegisterTest.java");
    }

    @AfterClass
    public static void afterClass()
    {
        System.out.println("All tests are done");
    }

   @Test
    public void testAddStudent()
   {
       register = new ArraylistStudentRegister();
       try
       {
           register.addStudent(new Student(2, "nimal", "kumara"));
           register.addStudent(new Student(5, "fawzan", "mohomad"));
       }
       catch (Exception ex)
       {
           Assert.fail("Adding student failed");
       }
       System.out.println("Testing add student method");

       Student student = new Student();
       try {
           student = register.findStudent(2);
       } catch (Exception e) {
           Assert.fail("Finding a student failed");
       }
       Assert.assertEquals("Student Id is wrong",2,student.getId());
   }

    @Test
    public void testRemoveStudent()
    {
        register = new ArraylistStudentRegister();
        try
        {
            register.addStudent(new Student(2, "nimal", "kumara"));
            register.addStudent(new Student(1, "ruwan", "tharaka"));
            register.addStudent(new Student(5, "gayan", "chamara"));
        }
        catch (Exception ex)
        {
            Assert.fail("Add student failed");
        }
        Student student = new Student();
        try {
            register.removeStudent(1);
            student = register.findStudent(1);
        } catch (Exception e) {
            Assert.fail("Finding a student failed");
        }
        Assert.assertNull("student was not removed",student);
    }

    @Test
    public void testGetRegNumbers()
    {
        register = new ArraylistStudentRegister();
        try
        {
            register.addStudent(new Student(1, "ruwan", "tharaka"));
            register.addStudent(new Student(2, "nimal", "kumara"));
            register.addStudent(new Student(5, "gayan", "chamara"));
        }
        catch (Exception ex)
        {
            Assert.fail("Adding student failed");
        }
        ArrayList<Integer> numbers =new ArrayList<Integer>();
        try {
            numbers = register.getAllRegistrationNumbers();
            Student student = register.findStudent(1);
        } catch (Exception e) {
            Assert.fail("Finding a student failed");
        }
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(5);
        Assert.assertTrue(numbers.equals(expected));
    }
}
