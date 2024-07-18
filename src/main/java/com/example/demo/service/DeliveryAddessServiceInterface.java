package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.DeliveryAddress;

public interface DeliveryAddessServiceInterface {

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(DeliveryAddress entity);

	void deleteById(Integer id);

	long count();

	<S extends DeliveryAddress> boolean exists(Example<S> example);

	boolean existsById(Integer id);

	Optional<DeliveryAddress> findById(Integer id);

	List<DeliveryAddress> findAllById(Iterable<Integer> ids);

	List<DeliveryAddress> findAll();

	Page<DeliveryAddress> findAll(Pageable pageable);

	<S extends DeliveryAddress> S save(S entity);

}
