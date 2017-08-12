package com.kyepot.bank.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kyepot.bank.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByNameAndEmailAllIgnoreCase(String name, String email);

}
