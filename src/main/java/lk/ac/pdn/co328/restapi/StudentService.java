package lk.ac.pdn.co328.restapi;
import lk.ac.pdn.co328.studentSystem.Student;
import lk.ac.pdn.co328.studentSystem.StudentRegister;
import lk.ac.pdn.co328.studentSystem.dbimplementation.DerbyStudentRegister;
import org.jboss.resteasy.util.HttpResponseCodes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rest")
public class StudentService
{
    private static StudentRegister register = new DerbyStudentRegister();

    @GET
    @Path("student/{id}")
    @Produces( MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML )
    public Response viewStudent(@PathParam("id") int id) {
        try {
            Student st = register.findStudent(id);
            if(st == null){
                return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
            }
            return Response.status(HttpResponseCodes.SC_OK).entity(st).build();
        } catch (Exception e) {
            return Response.status(HttpResponseCodes.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("student/{id}")
    @Consumes(MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML)
    public Response modifyStudent(@PathParam("id") int id, Student input)
    {
        Student temp=null;
        if(input != null) {
            try {
                temp= register.findStudent(id);
            }
            catch (Exception e) {
                e.printStackTrace();
                return Response.status(HttpResponseCodes.SC_FOUND).entity("ID FINDING ERROR").build();
            }

            if(temp==null){

                return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();

            }

            else{

                try {
                    register.addStudent(input);
                } catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(HttpResponseCodes.SC_FOUND).entity("ID UPDATING ERROR").build();

                }
            }

        }
        else{

            return Response.status(HttpResponseCodes.SC_FOUND).entity("UPDATING ERROR").build();

        }

        return Response.status(HttpResponseCodes.SC_OK).build();
    }

    @DELETE
    @Path("student/{id}")
    public Response deleteStudent(@PathParam("id") int id) {
        try {
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
        } catch (Exception e) {
            return Response.status(HttpResponseCodes.SC_INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("student/new")
    @Consumes(MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML)
    public Response addStudent(Student input) {

        Student temp=null;
        if(input != null) {
            try {
                temp= register.findStudent(input.getId());
            }
            catch (Exception e) {
                e.printStackTrace();
                return Response.status(HttpResponseCodes.SC_FOUND).entity("ID FINDING ERROR").build();
            }

            if(temp==null) {
                try {
                    register.addStudent(input);
                    return Response.status(HttpResponseCodes.SC_OK).build();
                } catch (Exception e) {
                    e.printStackTrace();
                    return Response.status(HttpResponseCodes.SC_BAD_REQUEST).build();
                }
            }else{

                return Response.status(HttpResponseCodes.SC_INTERNAL_SERVER_ERROR).build();


            }

        }else{
            return Response.status(HttpResponseCodes.SC_BAD_REQUEST).build();
        }
    }
}