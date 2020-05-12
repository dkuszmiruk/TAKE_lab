/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author DominikKuszmiruk
 */
public class Main {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        Client client = ClientBuilder.newClient();
        String count
                = client.target("http://localhost:8080/Complaints/"
                        + "resources/complaints/count")
                        .request(MediaType.TEXT_PLAIN)
                        .get(String.class);

        System.out.println("Count: " + count);

        List<Complaint> complaints = client.target("http://localhost:8080/Complaints/resources/complaints/")
                .request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<Complaint>>() {
        });

        try {
            System.out.println(mapper.writeValueAsString(complaints));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Complaint openComplaint = client.target("http://localhost:8080/Complaints/resources/complaints/1")
                .request(MediaType.APPLICATION_JSON).get(Complaint.class);

        try {
            System.out.println(mapper.writeValueAsString(openComplaint));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        openComplaint.setStatus("closed");
        client.target("http://localhost:8080/Complaints/resources/complaints/" + openComplaint.getId().toString())
                .request().put(Entity.entity(openComplaint, MediaType.APPLICATION_JSON));

        List<Complaint> openComplaints = client.target("http://localhost:8080/Complaints/resources/complaints?status=open")
                .request(MediaType.APPLICATION_JSON).get(Response.class).readEntity(new GenericType<List<Complaint>>() {
        });

        try {
            System.out.println(mapper.writeValueAsString(openComplaints));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        client.close();
    }
}
