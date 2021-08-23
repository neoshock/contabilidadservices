package com.webexample;

import com.google.gson.Gson;
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
import javax.ws.rs.core.Response;

@Path("Example")
public class ExampleResource {

    private List<Persona> personas = new ArrayList<>();

    @Context
    private UriInfo context;

    public ExampleResource() {

    }

    @Path("personas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson() {
        personas.add(new Persona("Manolo", "Cabrera", "Masculino"));
        personas.add(new Persona("Manolo", "Cabrera", "Masculino"));
        personas.add(new Persona("Manolo", "Cabrera", "Masculino"));
        Gson json = new Gson();
        String value = json.toJson(personas, List.class);
        //TODO return proper representation object
        return Response.ok(value).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
