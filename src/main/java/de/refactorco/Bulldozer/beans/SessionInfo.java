package de.refactorco.Bulldozer.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
@XmlRootElement
public class SessionInfo {
    public boolean isAuthenticated;
    public String userName;
    public String message;
}
