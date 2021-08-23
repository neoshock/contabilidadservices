
package com.webexample;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addResourceClasses(resources);
        return resources;
    }
    
    private void addResourceClasses(Set<Class<?>> resources) {
        resources.add(com.webexample.MainResource.class);
        resources.add(com.webexample.ExampleResource.class);
        resources.add(com.webexample.DiarioService.class);
    }
}
