package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Orders;
import com.example.demo.domain.Users;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderServiceInterface;

@Service
public class OrderServiceImpl implements OrderServiceInterface{

	OrderRepository orderRepository;
	
	@Autowired
	OrderDetailServiceImpl orderDetailServiceImpl;

	public OrderServiceImpl(OrderRepository orderRepository) {
		
		this.orderRepository = orderRepository;
	}

	public <S extends Orders> S save(S entity) {
		return orderRepository.save(entity);
	}

	public <S extends Orders> List<S> saveAll(Iterable<S> entities) {
		return orderRepository.saveAll(entities);
	}

	public <S extends Orders> Optional<S> findOne(Example<S> example) {
		return orderRepository.findOne(example);
	}

	public List<Orders> findAll(Sort sort) {
		return orderRepository.findAll(sort);
	}

	public void flush() {
		orderRepository.flush();
	}

	public Page<Orders> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	public <S extends Orders> S saveAndFlush(S entity) {
		return orderRepository.saveAndFlush(entity);
	}

	public <S extends Orders> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderRepository.saveAllAndFlush(entities);
	}

	public List<Orders> findAll() {
		return orderRepository.findAll();
	}

	public List<Orders> findAllById(Iterable<Integer> ids) {
		return orderRepository.findAllById(ids);
	}

	public void deleteInBatch(Iterable<Orders> entities) {
		orderRepository.deleteInBatch(entities);
	}

	public <S extends Orders> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderRepository.findAll(example, pageable);
	}

	public Optional<Orders> findById(Integer id) {
		return orderRepository.findById(id);
	}

	public void deleteAllInBatch(Iterable<Orders> entities) {
		orderRepository.deleteAllInBatch(entities);
	}

	public boolean existsById(Integer id) {
		return orderRepository.existsById(id);
	}

	public <S extends Orders> long count(Example<S> example) {
		return orderRepository.count(example);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderRepository.deleteAllByIdInBatch(ids);
	}

	public <S extends Orders> boolean exists(Example<S> example) {
		return orderRepository.exists(example);
	}

	public void deleteAllInBatch() {
		orderRepository.deleteAllInBatch();
	}

	public Orders getOne(Integer id) {
		return orderRepository.getOne(id);
	}

	public <S extends Orders, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderRepository.findBy(example, queryFunction);
	}

	public long count() {
		return orderRepository.count();
	}

	public void deleteById(Integer id) {
		orderRepository.deleteById(id);
	}

	public Orders getById(Integer id) {
		return orderRepository.getById(id);
	}

	public void delete(Orders entity) {
		orderRepository.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderRepository.deleteAllById(ids);
	}

	public Orders getReferenceById(Integer id) {
		return orderRepository.getReferenceById(id);
	}

	public void deleteAll(Iterable<? extends Orders> entities) {
		orderRepository.deleteAll(entities);
	}

	public <S extends Orders> List<S> findAll(Example<S> example) {
		return orderRepository.findAll(example);
	}

	public <S extends Orders> List<S> findAll(Example<S> example, Sort sort) {
		return orderRepository.findAll(example, sort);
	}

	public void deleteAll() {
		orderRepository.deleteAll();
	}

	public Page<Orders> findAllByUser(Users user, Pageable pageable) {
		return orderRepository.findAllByUser(user, pageable);
	}

	public Page<Orders> findAllByIdOrder(int id, Pageable pageable) {
		return orderRepository.findAllByIdOrder(id, pageable);
	}

	public Orders findBytransactionCode(String transactionCode) {
		return orderRepository.findBytransactionCode(transactionCode);
	}
	
	
	public List<Orders> findAllByPayedVNpayFalse() {
		return orderRepository.findAllByPayedVNpayFalse();
	}

	@Scheduled(fixedDelay = 60000*15)
	public void clearOrderOutOfTimepayVNpay() {
		List<Orders> list = this.findAllByPayedVNpayFalse(); 
		Date date = new Date();
		for (Orders orders : list) {
			if(date.getTime()>orders.getCountDownTimeTranVNpay()) {
				orderDetailServiceImpl.deleteAllByOrder(orders);
				this.delete(orders);
				
			}
		}
		
	}
	
}
