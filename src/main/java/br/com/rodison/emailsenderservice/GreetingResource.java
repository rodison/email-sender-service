package br.com.rodison.emailsenderservice;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted
    @Timed
    public String hello() {
        return "Hello RESTEasy";
    }
}