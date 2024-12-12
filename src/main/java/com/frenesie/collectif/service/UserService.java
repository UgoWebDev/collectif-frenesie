package com.frenesie.collectif.service;


import java.util.Optional;

import com.frenesie.collectif.model.User;


public interface UserService extends CrudService<User> {

	Optional<User> findUserByEmail(String email);
    
}
