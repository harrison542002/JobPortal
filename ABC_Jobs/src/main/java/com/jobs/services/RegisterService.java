package com.jobs.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobs.model.User;
import com.jobs.repository.RegisterRepository;

@Service
@Transactional
public class RegisterService {
	
	@Autowired 
	RegisterRepository repo;
	
	//Add user to database
	public User save(User user) {
		return repo.save(user);
	}

	public List<User> findUser(String email, String password) {
		return repo.getUsers(email,password);
	}
	
	public Optional<User> getUser(Integer id) {
		return repo.findById(id);
	}
	
	//Delete user from admin
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	//Update password for forget password feature
	public Integer updatePassword(String password, String email) {

		return repo.updatePassword(password, email);
	}

	//Get user for justify user existence
	public List<User> findUser(String email){
		return repo.getUser(email);
	}

}
