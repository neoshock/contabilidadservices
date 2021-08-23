
package com.webexample;

import com.contabilidad.dao.DiarioDAO;
import com.google.gson.Gson;
import com.webexample.models.Diario;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("diario")
@RequestScoped
public class DiarioService {
    private DiarioDAO diarioDAO;
    private List<Diario> diarios;
    
    @Context
    private UriInfo context;

    public DiarioService() {
        diarioDAO = new DiarioDAO();
        diarios = diarioDAO.getDiariosContables();
    }

    @GET
    @Path("getdiarios")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiariosContables() {
        Gson json = new Gson();
        String value = json.toJson(diarios, List.class);
        return Response.ok(value).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
