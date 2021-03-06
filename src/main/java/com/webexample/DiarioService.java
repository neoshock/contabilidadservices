package com.webexample;

import com.contabilidad.dao.DiarioDAO;
import com.google.gson.Gson;
import com.webexample.models.Diario;
import com.webexample.models.Message;
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

//Especifica la ruta principal del modulo para diario contables
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

    //Metodo para la obtecion de datos
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiariosContables() {
        Gson json = new Gson();
        diarios = diarioDAO.getDiariosContables();
        String value = json.toJson(diarios, List.class);
        return Response.ok(value).build();
    }

    //Metodo para realizar el registro de datos
    @Path("add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDiarioContable(String diarioJson) {
        JSONObject json = new JSONObject(diarioJson);
        String message = "";
        System.out.println(json);
        try {
            String date1 = json.getString("fechaApertura");
            String date2 = json.getString("fechaCierre");
            Diario onDiario = new Diario(json.getInt("idDiario"), json.getString("nombre"),
                    date1, date2, json.getString("descripcion"));

            if (!compareDataInsert(onDiario)) {
                message = "Ya existe un Registro con ese nombre";
            } else {
                if (diarioDAO.addNewDiario(onDiario)) {
                    message = "Registro exitoso";
                } else {
                    message = "Error Al Registrar";
                }
            }
            return Response.ok(getMessageJson(message)).build();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return Response.status(500).build();
        }

    }

    //Metodo para realizar la modificacion de algun dato
    @Path("edit")
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
                message = "No se detectaron cambios";
            } else {
                if (diarioDAO.updateDiario(onDiario)) {
                    message = "Modificacion exitosa";
                } else {
                    message = "Hubo un error al modificar el diario";
                }
            }
            return Response.ok(getMessageJson(message)).build();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return Response.status(500).build();
        }
    }

    //Metodo que raelizara la eliminacion de algun diario
    //El metodo recibe un parametro por url que es la id del diario contable
    @Path("delete/{iddiario}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDiarioContable(@PathParam("iddiario") int id) {
        String message = "";
        System.out.println(id);
        if (diarioDAO.deleteDiario(id).equals("Eliminacion Exitosa")) {
            message = "Se ha eliminado el diario contable";
        } else {
            message = "El diario selecionado no se puede eliminar o no existe";
        }
        return Response.ok(getMessageJson(message)).build();
    }

    //Permite verificar que el diario a registrar no tenga un nombre ya existente en la 
    //base de datos.
    private boolean compareDataInsert(Diario diario) {
        diarios = diarioDAO.getDiariosContables();
        long c = diarios.stream().filter(d -> diario.getNombre().equals(d.getNombre())).count();
        if (c > 0) {
            return false;
        } else {
            return true;
        }
    }

    //El siguiente metodo nos permite convertir un String en un JSON que devolvera
    //un mensaje que se lo pasa por parametro
    private String getMessageJson(String msj) {
        Gson jsonMessage = new Gson();
        Message message = new Message();
        message.setMessage(msj);
        String result = jsonMessage.toJson(message, Message.class);
        return result;
    }
    
    //Al momento de editar un diario contable, verifica que existan cambios referente al asiento 
    // a modificar
    private boolean compareDataEdit(Diario diario) {
        Diario oldDiario = diarioDAO.getDiarioById(diario.getIdDiario());
        if (oldDiario.getNombre().equals(diario.getNombre()) && 
                oldDiario.getFechaApertura().equals(diario.getFechaApertura())
                && oldDiario.getFechaCierre().equals(diario.getFechaCierre()) && 
                oldDiario.getDescripcion().equals(diario.getDescripcion())) {
            return false;
        } else {
            return true;
        }
    }
}
