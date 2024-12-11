package com.frenesie.collectif.service;

import java.util.Optional;

import com.frenesie.collectif.model.User;
import com.frenesie.collectif.repository.CrudRepository;


public interface UserService extends CrudRepository<User> {
    
	public Optional<User> findUserByEmail(String Email);

	void save(User user);

}
