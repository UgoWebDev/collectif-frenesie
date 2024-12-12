package com.frenesie.collectif.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.frenesie.collectif.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
    private static int idxUser = 1;
    private List<User> users;

    public UserRepositoryImpl() {
    	users = new ArrayList<>();
    	users.add(new User(idxUser++, "Ugo", "123456", "ugo@ugo.fr", "ADMIN"));
    	users.add(new User(idxUser++, "Laurane", "123456", "laurane@laurane.fr", "ADMIN"));
    	users.add(new User(idxUser++, "Celia", "123456", "celia@celia.fr", "USER"));
    	users.add(new User(idxUser++, "Arthur", "123456", "arthur@arthur.fr", "ARTIST"));
    }

    @Override
    public void add(User user) {
    	idxUser++;
    	user.setId(idxUser);
    	System.out.println(user);
    	this.users.add(user);
    }
    
    @Override
    public List<User> getAll() {
    	return users.stream().collect(Collectors.toList());
    }
    
    @Override
    public  Optional<User> getById(int id) {
        return this.users.stream().filter(c ->  c.getId() == id).findFirst();
    }
    
    public void update(User user) {
        Optional<User> oldUserOptional = getById(user.getId());
        if (oldUserOptional.isPresent()) {
        	
        	User oldUser = oldUserOptional.get();
            BeanUtils.copyProperties(user, oldUser);
        }
    }

    public void delete(int id) {
        Optional<User> userOptional = getById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
           users.remove(user);
        }
    }

	@Override
	public Optional<User> findUserByEmail(String email) {
		return this.users.stream().filter(c -> c.getEmail() == email).findFirst();
	}

	
}
