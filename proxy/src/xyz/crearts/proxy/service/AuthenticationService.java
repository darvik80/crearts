package xyz.crearts.proxy.service;

/**
 * @author ivan.kishchenko
 */
public interface AuthenticationService {
    boolean authenticate(String username, String password);
}
