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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("grupo")
@RequestScoped
public class GrupoService {

    private GrupoDAO grupoDAO;
    private List<Grupo> listGrupos;
    private Gson gson;

    @Context
    private UriInfo context;

    public GrupoService() {
        grupoDAO = new GrupoDAO();
        gson = new Gson();
        listGrupos = grupoDAO.getGrupoCuenta();
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiariosContables() {
        Gson json = new Gson();
        String value = json.toJson(listGrupos, List.class);
        return Response.ok(value).build();
        //return listGrupos;
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response create(String strJSONGrupo) {
        Grupo g = gson.fromJson(strJSONGrupo, Grupo.class);
        String result = "{\"message\": \"Ocurrio un error inesperado al registrar\"}";
        System.out.println("recibiendo" + g.toString());
        if (g.getCodigo() != null && g.getNombre() != null) {
            if (grupoDAO.insert(g)) {
                result = "{\"message\": \"Grupo registrado con exito\"}";
            }
        } else {
            result = "{\"message\": \"Faltan rellenar campos\"}";
        }
        return Response.ok(result).build();
    }

    @PUT
    @Path("edit")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response edit(String strJSONGrupo) {
        Grupo g = gson.fromJson(strJSONGrupo, Grupo.class);
        String result = "{\"message\": \"Ocurrio un error inesperado al modificar\"}";
        System.out.println("recibiendo" + g.toString());
        if (g.getId() > 0) {
            if (g.getNombre() != null) {
                if (grupoDAO.update(g)) {
                    result = "{\"message\": \"Grupo editado con exito\"}";
                }
            } else {
                result = "{\"message\": \"No coloco un nombre\"}";
            }
        } else {
            result = "{\"message\": \"No se encuentra el registro\"}";
        }
        return Response.ok(result).build();
    }

    @Path("/delete/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        String message = "{\"message\": \"Hubo un error al eliminar\"}";
        System.out.println(id);
        if (grupoDAO.getGrupoById(id) != null) {
            if (grupoDAO.delete(id)) {
                message = "{\"message\": \"Se ha elimnado el Grupo\"}";
            }
        } else {
            message = "{\"message\": \"No se encontro el registro para eliminar\"}";
        }
        return Response.ok(message).build();
    }
}
