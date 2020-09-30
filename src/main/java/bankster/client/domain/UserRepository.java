package bankster.client.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository
 *
 * @author Subhasmita
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
