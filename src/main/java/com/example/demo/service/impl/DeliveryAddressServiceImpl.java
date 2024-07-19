package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.DeliveryAddress;
import com.example.demo.domain.Users;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.service.DeliveryAddessServiceInterface;
@Service
public class DeliveryAddressServiceImpl implements DeliveryAddessServiceInterface {

	DeliveryRepository deliveryRepository;

	public DeliveryAddressServiceImpl(DeliveryRepository deliveryRepository) {
		this.deliveryRepository = deliveryRepository;
	}

	@Override
	public <S extends DeliveryAddress> S save(S entity) {
		return deliveryRepository.save(entity);
	}

	@Override
	public Page<DeliveryAddress> findAll(Pageable pageable) {
		return deliveryRepository.findAll(pageable);
	}

	@Override
	public List<DeliveryAddress> findAll() {
		return deliveryRepository.findAll();
	}

	@Override
	public List<DeliveryAddress> findAllById(Iterable<Integer> ids) {
		return deliveryRepository.findAllById(ids);
	}

	@Override
	public Optional<DeliveryAddress> findById(Integer id) {
		return deliveryRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return deliveryRepository.existsById(id);
	}

	@Override
	public <S extends DeliveryAddress> boolean exists(Example<S> example) {
		return deliveryRepository.exists(example);
	}

	@Override
	public long count() {
		return deliveryRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		deliveryRepository.deleteById(id);
	}

	@Override
	public void delete(DeliveryAddress entity) {
		deliveryRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		deliveryRepository.deleteAllById(ids);
	}

	public List<DeliveryAddress> findAllByUser(Users user) {
		return deliveryRepository.findAllByUser(user);
	}

	public DeliveryAddress findById(int id) {
		return deliveryRepository.findById(id);
	}

	public DeliveryAddress findByUserAndStatus(Users user, boolean status) {
		return deliveryRepository.findByUserAndStatus(user, status);
	}
	
	
	
}
