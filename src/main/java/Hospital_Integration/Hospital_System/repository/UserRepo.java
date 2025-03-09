package Hospital_Integration.Hospital_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import Hospital_Integration.Hospital_System.model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Long>{
	UserModel findByEmail(String email);
}
