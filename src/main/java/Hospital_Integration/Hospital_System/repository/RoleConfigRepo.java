package Hospital_Integration.Hospital_System.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Hospital_Integration.Hospital_System.model.RoleConfig;

public interface RoleConfigRepo extends JpaRepository<RoleConfig, Long> {
    Optional<RoleConfig> findByUsername(String username);
	
}
