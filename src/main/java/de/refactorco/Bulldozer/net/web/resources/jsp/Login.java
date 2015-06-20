package de.refactorco.Bulldozer.net.web.resources.jsp;

import com.sun.jersey.api.view.Viewable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
@Path("/login")
@Produces(MediaType.TEXT_HTML)
public class Login {
    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    @GET
    public Viewable login(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return new Viewable("/login.jsp", null);
    }

    @POST
    public String login(@Context HttpServletRequest request, @Context HttpServletResponse response, @FormParam("username") String username, @FormParam("password") String password, @FormParam("rememberMe") boolean rememberMe) {
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            logger.info("Already logged in");
            return "already logged in";
        } else {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            try {
                currentUser.login(token);
                logger.debug("Login succeeded!");
                response.sendRedirect("/home");
                return currentUser.toString();
            } catch (AuthenticationException e) {
                return e.getMessage();
            } catch (IOException e) {
                return e.getMessage();
            }
        }
    }
}