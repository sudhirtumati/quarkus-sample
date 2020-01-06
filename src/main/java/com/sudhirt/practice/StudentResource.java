package com.sudhirt.practice;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sudhirt.practice.entity.Student;
import com.sudhirt.practice.repository.StudentRepository;

@Path("/students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {

    @Inject
    StudentRepository studentRepository;

    @GET
    public List<Student> get() {
        return studentRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Student getOne(@PathParam(value = "id") String id) {
        Student dbStudent = studentRepository.findById(id);
        if(dbStudent == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return dbStudent;
    }

    @POST
    @Transactional
    public Response add(Student student) {
        Student dbStudent = studentRepository.findById(student.getId());
        if(dbStudent != null) {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
        studentRepository.persist(student);
        return Response.ok().status(Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam(value = "id") String id, Student student) {
        Student dbStudent = getOne(id);
        dbStudent.setFirstName(student.getFirstName() == null ? dbStudent.getFirstName() : student.getFirstName());
        dbStudent.setLastName(student.getLastName() == null ? dbStudent.getLastName() : student.getLastName());
        dbStudent.setGender(student.getGender() == null ? dbStudent.getGender() : student.getGender());
        dbStudent.setDateOfBirth(student.getDateOfBirth() == null ? dbStudent.getDateOfBirth() : student.getDateOfBirth());
        studentRepository.persist(dbStudent);
        return Response.ok().status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam(value = "id") String id) {
        Student dbStudent = getOne(id);
        studentRepository.delete(dbStudent);
        return Response.ok().status(Status.NO_CONTENT).build();
    }
}