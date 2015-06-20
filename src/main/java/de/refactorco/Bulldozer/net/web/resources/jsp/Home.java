package de.refactorco.Bulldozer.net.web.resources.jsp;

import com.sun.jersey.api.view.Viewable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
@Path("/home")
@Produces(MediaType.TEXT_HTML)
public class Home {
    @GET
    public Viewable index(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        request.setAttribute("obj", new String("IT Works"));
        return new Viewable("/home.jsp", null);

    }
}