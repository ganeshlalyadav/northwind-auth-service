package User.northwind.interfacee;

import User.northwind.dao.UserDto;
import User.northwind.entity.Role;
import User.northwind.entity.User;
import User.northwind.repo.UsersRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j

public class CustomUserDetailsService implements UserDetailsService {
    private UsersRepo userRepository;


    /* @Override
     public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

         User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                 .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

         Set<GrantedAuthority> authorities = user.getRoles().stream()
                 .map((role) -> new SimpleGrantedAuthority(role.getName()))
                 .collect(Collectors.toSet());

         return new org.springframework.security.core.userdetails.User(
                 usernameOrEmail,
                 user.getPassword(),
                 authorities
         );
     }*/
    private static final int MAX_ATTEMPTS = 3;

    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (!optionalUser.isPresent()) {
            log.info("User not found with username: {}", usernameOrEmail);
            throw new UsernameNotFoundException("User not found with this name: " + usernameOrEmail);
        }
        User user = optionalUser.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return roles.stream().map(String::new).map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}


