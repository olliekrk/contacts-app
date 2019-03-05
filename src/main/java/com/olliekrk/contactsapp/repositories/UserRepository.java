package com.olliekrk.contactsapp.repositories;

import com.olliekrk.contactsapp.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsernameAndPassword(String username, String password);
}
