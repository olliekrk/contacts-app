package com.olliekrk.contactsapp;

import com.olliekrk.contactsapp.entities.User;
import com.olliekrk.contactsapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.save(user);
    }
}
