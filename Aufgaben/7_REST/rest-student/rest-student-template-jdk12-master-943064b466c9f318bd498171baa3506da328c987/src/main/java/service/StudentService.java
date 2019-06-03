package service;

import entity.Student;

import javax.annotation.processing.Generated;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("studentservice")
public class StudentService {

    private static int nextStudentId = 1;
    private static Map<Integer, Student> studentDb = new TreeMap<>();  // kann als interne Datenbank verwendet werden

    @Path("matriculate/newstudent/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student matriculate(Student s) {
        s.setMatrikelNr(nextStudentId++);
        System.out.println("newstudent-post engaged...");
        studentDb.put(s.getMatrikelNr(), s);
        return s;

        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        //throw new IllegalStateException("method 'matriculate' needs to be implemented first");
    }

    @Path("matriculate/newstudents/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student[] matriculate(Student[] s) {
        for(Student a: s) {
            a.setMatrikelNr(nextStudentId++);
            studentDb.put(a.getMatrikelNr(), a);
        }

        return s;

        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        //throw new IllegalStateException("method 'matriculate' needs to be implemented first");
    }

    public Student exmatriculate(int studentId) {

        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        throw new IllegalStateException("method 'exmatriculate' needs to be implemented first");

    }

    @Path("getallstudentsstring")
    @GET
    public String getAllStudents1() {
        String temp = "";
        Iterator it = studentDb.entrySet().iterator();
        System.out.println("GetAllStudents1 works!");
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            temp += ("ID: " + pair.getKey() + ", Information: " + pair.getValue().toString() + "\n");
            System.out.println(temp);
            it.remove();
        }


        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        //throw new IllegalStateException("method 'getStudentById' needs to be implemented first");
        return temp;
    }


    @Path("studid/{studentId}")
    @GET
    public Student getStudentById(@PathParam("studentId") int id) {
        System.out.println("\nStudentId-Function => works!");
        String temp = ("Es wurde der Student mit der ID : " + id + " angefordert!");
        Student tempStud = studentDb.get(id);
        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        //throw new IllegalStateException("method 'getStudentById' needs to be implemented first");
        return tempStud;
    }


    public Student updateStudentAccount(int studentId, Student newData) {

        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        throw new IllegalStateException("method 'updateStudentAccount' needs to be implemented first");

    }

    @Path("getallstudents")
    @GET
    public Collection<Student> getAllStudents() {
        System.out.println("Collection output comes now...");
        return (Collection<Student>) studentDb.values();
        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        //throw new IllegalStateException("method 'getAllStudents' needs to be implemented first");

    }

    @Path("getallstudentsbyrange")
    @GET
    public Collection<Student> getStudentsByRange(@QueryParam("from") int fromStudentId,@QueryParam("to") int toStudentId) {
        if(fromStudentId == 0 && toStudentId == 0) {
            return getAllStudents();
        }
        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        //throw new IllegalStateException("method 'getStudentsByRange' needs to be implemented first");
        return null;
    }
}
