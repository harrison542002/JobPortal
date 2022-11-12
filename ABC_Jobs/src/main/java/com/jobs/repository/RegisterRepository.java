package com.jobs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jobs.model.User;

@Repository
public interface RegisterRepository extends CrudRepository<User, Integer>{

	@Modifying
	@Transactional
	@Query("UPDATE User SET password=:password WHERE email LIKE :email")
	Integer updatePassword(@Param("password")String password, @Param("email") String email);

	@Modifying
	@Transactional
	@Query("UPDATE User SET password=:password, email=:email WHERE user_id=:user_id")
	Integer editUserInfo(@Param("password")String password, @Param("email") String email,
						   @Param("user_id") Integer user_id);

	@Query("SELECT user FROM User user WHERE email=:email AND password=:password")
	List<User> getUsers(@Param("email") String email, @Param("password") String password);

	@Query("SELECT user FROM User user WHERE email LIKE :email")
	List<User> getUser(@Param("email") String email);

	@Modifying
	@Transactional
	@Query("DELETE FROM User WHERE user_id =:u_id")
	public void deleteById(@Param("u_id") Integer u_id);

}
