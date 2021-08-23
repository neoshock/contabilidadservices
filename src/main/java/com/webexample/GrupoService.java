
package com.webexample;

import com.contabilidad.dao.GrupoDAO;
import com.google.gson.Gson;
import com.webexample.models.Grupo;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("grupo")
@RequestScoped
public class GrupoService {
    private GrupoDAO grupoDAO;
    private List<Grupo> listGrupos;
    
    @Context
    private UriInfo context;

    public GrupoService() {
        grupoDAO = new GrupoDAO();
        listGrupos = grupoDAO.getGrupoCuenta();
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiariosContables() {
        Gson json = new Gson();
        String value = json.toJson(listGrupos, List.class);
        return Response.ok(value).build();
    }
}
