package de.refactorco.Bulldozer.net.web.resources.api;

import com.google.inject.Inject;
import de.refactorco.Bulldozer.beans.RegisterData;
import de.refactorco.Bulldozer.dao.UserDAO;
import de.refactorco.Bulldozer.beans.LoginData;
import de.refactorco.Bulldozer.beans.SessionInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
public class User {
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Inject
    private UserDAO userDAO;

    public SessionInfo getSessionInfo() {
        SessionInfo sessionInfo = new SessionInfo();
        Subject currentUser = SecurityUtils.getSubject();

        sessionInfo.isAuthenticated = currentUser.isAuthenticated();
        Object principal = currentUser.getPrincipal();
        String userName = principal != null ? principal.toString() : "Guest";
        sessionInfo.userName = userName;

        return sessionInfo;
    }

    @GET
    @Path("session")
    public SessionInfo getSession() {
        return getSessionInfo();
    }

    @POST
    @Path("session")
    @Consumes(MediaType.APPLICATION_JSON)
    public SessionInfo createSession(LoginData loginData) {
        Subject currentUser = SecurityUtils.getSubject();
        SessionInfo sessionInfo = getSessionInfo();


        if (currentUser.isAuthenticated()) {
            logger.info("Already logged in");
            sessionInfo.message = "Already logged in";
        } else {
            UsernamePasswordToken token = new UsernamePasswordToken(loginData.getUserName(), loginData.getPassword());
            token.setRememberMe(loginData.isRememberMe());

            try {
                currentUser.login(token);
                logger.debug("Login succeeded!");
                sessionInfo = getSessionInfo();
            } catch (AuthenticationException e) {
                sessionInfo.message = e.getMessage();
            }
        }

        return sessionInfo;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("register")
    public SessionInfo createUser(RegisterData registerData) {
        Subject currentUser = SecurityUtils.getSubject();
        SessionInfo sessionInfo = getSessionInfo();

        if (currentUser.isAuthenticated()) {
            logger.info("Already logged in");
            sessionInfo.message = "Already logged in";
        } else {
            if (! userDAO.userExists(registerData.getUserName())) {
                if (userDAO.createUser(registerData.getUserName(), registerData.getPassword(), registerData.getEmail())) {
                    UsernamePasswordToken token = new UsernamePasswordToken(registerData.getUserName(), registerData.getPassword());

                    try {
                        currentUser.login(token);
                        logger.debug("Login succeeded!");
                        sessionInfo = getSessionInfo();
                        sessionInfo.message = "Account created!";
                    } catch (AuthenticationException e) {
                        sessionInfo.message = "Failed to register, please try again later";
                    }
                }
            } else {
                sessionInfo.message = "Username already in use";
            }
        }

        return sessionInfo;
    }
}
