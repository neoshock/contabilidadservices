package com.webexample;

import com.contabilidad.dao.DiarioDAO;
import com.google.gson.Gson;
import com.webexample.models.Diario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.json.JSONObject;

@Path("diarios")
@RequestScoped
public class DiarioService {

    private DiarioDAO diarioDAO;
    private List<Diario> diarios;

    @Context
    private UriInfo context;

    public DiarioService() {
        diarioDAO = new DiarioDAO();
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiariosContables() {
        Gson json = new Gson();
        diarios = diarioDAO.getDiariosContables();
        String value = json.toJson(diarios, List.class);
        return Response.ok(value).build();
    }

    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDiarioContable(String diarioJson) {
        JSONObject json = new JSONObject(diarioJson);
        System.err.println(json);
        String message = "";
        try {
            String date1 = json.getString("fechaApertura");
            String date2 = json.getString("fechaCierre");
            Diario onDiario = new Diario(json.getInt("idDiario"), json.getString("nombre"),
                    date1, date2, json.getString("descripcion"));

            if (!compareDataInsert(onDiario)) {
                message = "{'message':'Ya_existe_un_Registro_con_ese_nombre'}";
                return Response.status(406).build();
            } else {
                if (diarioDAO.addNewDiario(onDiario)) {
                    message = "{'message':'Registro_exitoso'}";
                    return Response.ok(message).build();
                } else {
                    message = "{'message':'Error_Al_Registrar'}";
                    return Response.status(400).build();
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return Response.status(500).build();
        }

    }

    @Path("/edit")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editDiarioContable(String diarioJson) {
        JSONObject json = new JSONObject(diarioJson);
        System.err.println(json);
        String message = "";
        try {
            String date1 = json.getString("fechaApertura");
            String date2 = json.getString("fechaCierre");
            Diario onDiario = new Diario(json.getInt("idDiario"), json.getString("nombre"),
                    date1, date2, json.getString("descripcion"));

            if (!compareDataEdit(onDiario)) {
                message = "{'message':'Ya_existe_un_Registro_con_ese_nombre'}";
                return Response.status(406).build();
            } else {
                if (diarioDAO.updateDiario(onDiario)) {
                    message = "{'message':'Modificacion_exitosa'}";
                    return Response.ok(message).build();
                } else {
                    message = "{'message':'Error_Al_Registrar'}";
                    return Response.status(400).build();
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return Response.status(500).build();
        }
    }
    
    @Path("/delete/{iddiario}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDiarioContable(@PathParam("iddiario") int id) {
        String message = "";
        System.out.println(id);
        if(diarioDAO.deleteDiario(id).equals("Eliminacion Exitosa")){
            message = "{'message':'Eliminacion_exitosa'}";
            return Response.ok(message).build();
        }else{
            return Response.status(400).build();
        }
    }

    private boolean compareDataInsert(Diario diario) {
        diarios = diarioDAO.getDiariosContables();
        long c = diarios.stream().filter(d -> diario.getNombre().equals(d.getNombre())).count();
        if (c > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean compareDataEdit(Diario diario) {
        Diario oldDiario = diarioDAO.getDiarioById(diario.getIdDiario());
        if (oldDiario.getNombre().equals(diario.getNombre()) && oldDiario.getFechaApertura().equals(diario.getFechaApertura())
                && oldDiario.getFechaCierre().equals(diario.getFechaCierre()) && oldDiario.getDescripcion().equals(diario.getDescripcion())) {
            return false;
        } else {
            return true;
        }
    }
}
