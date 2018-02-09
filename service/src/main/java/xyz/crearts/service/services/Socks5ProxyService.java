package xyz.crearts.service.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.crearts.proxy.service.AuthenticationService;
import xyz.crearts.proxy.service.socks5.Socks5Server;
import xyz.crearts.service.model.User;
import xyz.crearts.service.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author ivan.kishchenko
 */
@Service
public class Socks5ProxyService implements AuthenticationService {
    @Value("${proxy.socks5.host:0.0.0.0}")
    private String host;
    @Value("${proxy.socks5.port:1080}")
    private int port;

    private Socks5Server socks5Server;

    private UserRepository userRepository;

    public Socks5ProxyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct()
    void postCostruct() throws IOException {
        socks5Server = new Socks5Server(this.host, this.port, this);
        socks5Server.start();
    }

    @PreDestroy()
    void preDestroy() {
        socks5Server.stop();
    }

    @Override
    public boolean authenticate(String username, String password) {
        return this.userRepository.findByLogin(username).map(User::getPassword).orElse("").equals(password);
    }
}
