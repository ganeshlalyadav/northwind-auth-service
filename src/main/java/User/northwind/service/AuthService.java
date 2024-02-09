package User.northwind.service;

import User.northwind.dao.JWTAuthResponse;
import User.northwind.dao.LoginDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
        JWTAuthResponse login(LoginDto loginDto);
        void authenticate(String username, String password);
}
