package de.refactorco.Bulldozer.beans;

/**
 * Bulldozer,
 * Edited: 20/06/15.
 */
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String passwordSalt;

    public User(Long id, String username, String email, String password, String passwordSalt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordSalt = passwordSalt;
    }
}
