package de.refactorco.Bulldozer.net.web.resources.jsp;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;
import de.refactorco.Bulldozer.dao.UserDAO;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
@Path("/register")
@Produces(MediaType.TEXT_HTML)
public class Register {
    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    @Inject
    private UserDAO userDAO;

    @GET
    public Viewable register(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return new Viewable("/register.jsp", null);
    }

    @POST
    public String register(@Context HttpServletRequest request,
                           @Context HttpServletResponse response,
                           @NotNull @FormParam("username") String username,
                           @NotNull @FormParam("password") String password,
                           @NotNull @FormParam("email") String email) {
        Subject currentUser = SecurityUtils.getSubject();

        if (currentUser.isAuthenticated()) {
            logger.info("Already logged in");
            return "already logged in";
        } else {
            if (EmailValidator.getInstance().isValid(email)
                && userDAO.createUser(username, password, email)) {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);

                try {
                    currentUser.login(token);
                    logger.debug("Login succeeded!");
                    return currentUser.toString();
                } catch (AuthenticationException e) {
                    return e.getMessage();
                }
            } else {
                return "username already in use";
            }
        }
    }
}
