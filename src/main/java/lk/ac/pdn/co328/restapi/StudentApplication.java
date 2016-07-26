/*
 * CO328 Lab3 web services excercise
 * Author - Himesh Karunarathna
 */

package lk.ac.pdn.co328.restapi;
 
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
 
public class StudentApplication extends Application
{
    private final Set<Object> singletons = new HashSet();
    private final Set<Class<?>> empty = new HashSet();
 
    public StudentApplication() {
        // ADD YOUR RESTFUL RESOURCES HERE
        this.singletons.add(new StudentService());
    }
 
    @Override
    public Set<Class<?>> getClasses()
    {
        return this.empty;
    }
 
    @Override
    public Set<Object> getSingletons()
    {
        return this.singletons;
    }
}