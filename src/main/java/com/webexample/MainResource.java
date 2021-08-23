/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webexample;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;

@Path("Main")
@RequestScoped
public class MainResource {

    @Context
    private UriInfo context;
    
    public MainResource() {
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        return "<h1>Hola gente</h1>";
    }

    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
}
