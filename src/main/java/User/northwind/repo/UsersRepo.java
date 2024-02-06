package User.northwind.repo;

import User.northwind.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User,Integer> {
}
