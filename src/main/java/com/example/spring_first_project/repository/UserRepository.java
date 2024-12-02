package com.example.spring_first_project.repository;

import com.example.spring_first_project.model.UserDemo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserDemo, Integer>
{
    List<UserDemo> findAll();
    UserDemo findByEmail(String email);
}