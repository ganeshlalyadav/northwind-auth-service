package User.northwind.service;

import User.northwind.dao.LoginDto;

public interface AuthService {
        String login(LoginDto loginDto);
}
