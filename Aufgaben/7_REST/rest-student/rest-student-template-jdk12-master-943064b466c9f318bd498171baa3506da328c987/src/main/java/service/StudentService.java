package service;

import app.OTHRestException;
import entity.Student;

import javax.annotation.processing.Generated;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.util.stream.Collectors;

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

    @DELETE
    @Path("students/{id}")
    public Student exmatriculate(@PathParam("id") int studentId) {

        if(studentDb.containsKey(studentId)) {
            Student geloescht = studentDb.remove(studentId);
            return geloescht;
        } else {
            throw new OTHRestException(404, "Student mit ID " + studentId + " ist nicht immatrikuliert");
        }

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
        //System.out.println("Printed >> " + tempStud.toString());
        if(tempStud != null)
            return tempStud;
        else
            throw new OTHRestException(404, "Student mit ID " + id + " ist nicht immatrikuliert");

    }

    @Path("update/student/{studentId}")
    @PUT
    public Student updateStudentAccount(@PathParam("studentId")int studentId, Student newData) {
        newData.setMatrikelNr(studentId);

        if(studentDb.containsKey(studentId)) {
            studentDb.put(studentId, newData);
            return newData;
        } else {
            throw new OTHRestException(404, "Student mit ID " + studentId + " ist nicht immatrikuliert");
        }

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

        /* Beispiele für mögliche Resource-Pfade zum Aufruf dieser Methode:

              /restapi/studentaffairs/students?from=100&to=108
              /restapi/studentaffairs/students?from=100
              /restapi/studentaffairs/students?to=108
              /restapi/studentaffairs/students

              Die Angabe der Query-Parameter "from" und "to" ist also nicht zwingend erforderlich.
              Werden Sie weggelassen wird entsprechend der Wert 0 übergeben
         */
        if(fromStudentId == 0 && toStudentId == 0)
            return getAllStudents();
        else if(toStudentId == 0 && fromStudentId > 0)
            return studentDb.values()
                    .stream()
                    .filter( student ->
                            student.getMatrikelNr() >= fromStudentId)
                    .collect(Collectors.toSet());
        else
            return studentDb.values()
                    .stream()
                    .filter( student ->
                            student.getMatrikelNr() >= fromStudentId
                                    && student.getMatrikelNr() <= toStudentId)
                    .collect(Collectors.toSet());

    }

        // Methode annotieren und ausimplementieren und folgende "throw"-Anweisung löschen!
        //throw new IllegalStateException("method 'getStudentsByRange' needs to be implemented first");
}

