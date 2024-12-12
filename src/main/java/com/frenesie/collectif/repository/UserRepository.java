package com.frenesie.collectif.repository;


import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.User;

@Repository
public interface UserRepository extends CrudRepository<User> {

	public Optional<User> findUserByEmail(String email);


}