package ba.sake.ss.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ba.sake.ss.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByUsername(String username);

}
