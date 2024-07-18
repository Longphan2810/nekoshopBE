package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.Users;

public interface UserServiceInterface {

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Users entity);

	Users getById(Integer id);

	void deleteById(Integer id);

	long count();

	Users getOne(Integer id);

	<S extends Users> boolean exists(Example<S> example);

	boolean existsById(Integer id);

	<S extends Users> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Users> findAll();

	Page<Users> findAll(Pageable pageable);

	<S extends Users> S save(S entity);

}
