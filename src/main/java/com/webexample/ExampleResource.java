package com.webexample;

import com.webexample.models.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

@Path("Example")
@RequestScoped
public class ExampleResource {

    private List<Persona> personas = new ArrayList<>();

    @Context
    private UriInfo context;

    public ExampleResource() {

    }

    @GET
    @Path("personas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> getJson() {
        personas.add(new Persona("Manolo", "Cabrera", "Masculino"));
        personas.add(new Persona("Manolo", "Cabrera", "Masculino"));
        personas.add(new Persona("Manolo", "Cabrera", "Masculino"));
        //TODO return proper representation object
        return personas;
    }

    /**
     * PUT method for updating or creating an instance of ExampleResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
