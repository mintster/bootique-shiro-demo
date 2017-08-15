package com.nixmash.shiro.controller;

import com.nixmash.shiro.models.User;
import com.nixmash.shiro.views.PageView;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by daveburke on 6/26/17.
 */
@Path("/")
public class GeneralController {

    @GET
    public PageView home() {
        User user = new User("bob", "bob@aol.com", "Bob", "Planter","password");
        return new PageView("home.mustache", user);
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.APPLICATION_JSON)
    public String sayHello() {
        return "hello!";
    }

    @GET
    @Path("/users")
    public PageView users() {
        Subject subject = SecurityUtils.getSubject();
        return new PageView("users.mustache", null);
    }

    @GET
    @Path("/login.jsp")
    public Response redirectLogin() throws URISyntaxException {
        URI targetURIForRedirection = new URI("/login");
        return Response.seeOther(targetURIForRedirection).build();
    }

    @GET
    @Path("/admin")
    public PageView admin() {
        return new PageView("admin.mustache", null);
    }

    @GET
    @Path("/logout")
    public Response logout() throws URISyntaxException {
        SecurityUtils.getSubject().logout();
        URI targetURIForRedirection = new URI("/login?logout=true");
        return Response.temporaryRedirect(targetURIForRedirection).build();
    }

    @GET
    @Path("/unauthorized")
    public PageView unauthorized() throws URISyntaxException {
        return new PageView("unauthorized.mustache", null);
    }
}
