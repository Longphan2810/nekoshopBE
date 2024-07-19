package com.example.demo.service.impl;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceInterface;

@Service
public class UserServiceImpl implements UserServiceInterface {

	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	@Override
	public <S extends Users> S save(S entity) {
		return userRepository.save(entity);
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public List<Users> findAll() {
		return userRepository.findAll();
	}

	@Override
	public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
		return userRepository.findAll(example, pageable);
	}

	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}

	@Override
	public <S extends Users> boolean exists(Example<S> example) {
		return userRepository.exists(example);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Users getOne(Integer id) {
		return userRepository.getOne(id);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Users getById(Integer id) {
		return userRepository.getById(id);
	}

	@Override
	public void delete(Users entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		userRepository.deleteAllById(ids);
	}

	public Page<Users> findAllByNameLike(String name, Pageable pageable) {
		return userRepository.findAllByNameLike(name, pageable);
	}

	public Page<Users> findAllByNameLikeAndEmailLike(String name, String email, Pageable pageable) {
		return userRepository.findAllByNameLikeAndEmailLike(name, email, pageable);
	}

	public Users findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Users findByToken(String token) {
		return userRepository.findByToken(token);
	}
	
	
	
	
}
