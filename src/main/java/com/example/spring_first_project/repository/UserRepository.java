package com.example.spring_first_project.repository;

import com.example.spring_first_project.model.UserDemo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserDemo, Integer>
{
    List<UserDemo> findAll();
}