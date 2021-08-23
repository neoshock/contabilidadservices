
package com.webexample;

import com.contabilidad.dao.DiarioDAO;
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
    public List<Diario> getDiariosContables() {
        return diarios;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
