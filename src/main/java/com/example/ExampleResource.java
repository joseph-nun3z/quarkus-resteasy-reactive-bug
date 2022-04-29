package com.example;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() throws CustomException {
        return Uni.createFrom().item(fail()).onFailure().invoke(failure -> Log.info(failure.getMessage())); //log not invoked
    }

    private String fail() throws CustomException {
        throw new CustomException("hello");
    }

    private String pass() {
        return "hello";
    }
}