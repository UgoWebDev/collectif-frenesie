package com.frenesie.collectif.service;

import com.frenesie.collectif.exception.UserNotFoundException;
import com.frenesie.collectif.model.User;
import com.frenesie.collectif.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public void add(User User) {
    	userRepository.add(User);
    }
    
    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Optional<User> getById(int id) {
        return userRepository.getById(id);
    }
    
    public void update(User user) {
      	 Optional<User> userOpt = getById(user.getId());
           if (userOpt.isPresent()) {
          	 userRepository.update(user);	 
           }else {
          	 //TODO gerer l'erreur
          	 throw new UserNotFoundException();
           }
          
      }
    
    public void delete(int id) {
        userRepository.delete(id);
    }

 	@Override
 	public void save(User entity) {
 		
 		if(entity.getId()==null) {
 			this.add(entity);
 			return;
 		}
 		this.update(entity);
 		
 	}
    
    @Override
    public Optional<User> findUserByEmail(String email){
    	return userRepository.findUserByEmail(email);
    }
    



}
