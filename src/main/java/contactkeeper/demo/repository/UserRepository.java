package contactkeeper.demo.repository;

import contactkeeper.demo.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, String> {
    Optional<MyUser> findMyUserByUsername(String username);
}
