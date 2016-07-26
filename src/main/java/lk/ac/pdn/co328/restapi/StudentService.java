
package lk.ac.pdn.co328.restapi;
import lk.ac.pdn.co328.studentSystem.Student;
import lk.ac.pdn.co328.studentSystem.StudentRegister;
import org.jboss.resteasy.util.HttpResponseCodes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lk.ac.pdn.co328.studentSystem.arraylistimplementation.ArraylistStudentRegister;

@Path("rest")
public class StudentService
{
    private static StudentRegister register = new ArraylistStudentRegister();

    @GET
    @Path("student/{id}")
    // Uncommenting this will let the reciver know that you are sending a json
    @Produces( MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML )
    public Response viewStudent(@PathParam("id") int id) {
        Student st = register.findStudent(id);
        if(st == null){
            return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
        }
        return Response.status(HttpResponseCodes.SC_OK).entity(st).build();
    }

    @PUT
    @Path("student/{id}")
    @Consumes("application/xml")
    public Response modifyStudent(@PathParam("id") int id, Student input)
    {
        if(input == null) {
            try {
                register.addStudent(input);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(HttpResponseCodes.SC_INTERNAL_SERVER_ERROR).build();
            }
        }
        else{
            register.removeStudent(id);
            try {
                register.addStudent(input);
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(HttpResponseCodes.SC_FOUND).entity("Error.Student is modified.").build();
            }
        }
        return Response.status(HttpResponseCodes.SC_OK).build();
    }

    @DELETE
    @Path("student/{id}")

    public Response deleteStudent(@PathParam("id") int id) {
        if ((register.findStudent(id) != (null))) {
            try {
                register.removeStudent(id);
               return Response.status(HttpResponseCodes.SC_OK).build();
            } catch (Exception e) {
                return Response.status(HttpResponseCodes.SC_INTERNAL_SERVER_ERROR).build();
            }
        }else {
            return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
        }
    }

    @POST
    @Path("student/new")
    @Consumes(MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML)
    public Response addStudent(Student input) {
        if (input != (null)) {
            try {
                register.addStudent(input);
                return Response.status(HttpResponseCodes.SC_OK).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.status(HttpResponseCodes.SC_BAD_REQUEST).build();
            }
        }else{
            return Response.status(HttpResponseCodes.SC_BAD_REQUEST).build();
        }
    }
}