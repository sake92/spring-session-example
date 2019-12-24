package ba.sake.ss.advertisementservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ba.sake.ss.advertisementservice.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByUsername(String username);

}
