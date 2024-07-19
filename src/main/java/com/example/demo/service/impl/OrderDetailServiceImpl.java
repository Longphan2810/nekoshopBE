package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.Orders;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.service.OrderServiceInterface;
@Service
public class OrderDetailServiceImpl implements OrderServiceInterface {

	OrderDetailRepository orderDetailRepository;

	public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {

		this.orderDetailRepository = orderDetailRepository;
	}

	public <S extends OrderDetail> S save(S entity) {
		return orderDetailRepository.save(entity);
	}

	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return orderDetailRepository.saveAll(entities);
	}

	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return orderDetailRepository.findOne(example);
	}

	public List<OrderDetail> findAll(Sort sort) {
		return orderDetailRepository.findAll(sort);
	}

	public void flush() {
		orderDetailRepository.flush();
	}

	public Page<OrderDetail> findAll(Pageable pageable) {
		return orderDetailRepository.findAll(pageable);
	}

	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return orderDetailRepository.saveAndFlush(entity);
	}

	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderDetailRepository.saveAllAndFlush(entities);
	}

	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	public List<OrderDetail> findAllById(Iterable<Integer> ids) {
		return orderDetailRepository.findAllById(ids);
	}

	public void deleteInBatch(Iterable<OrderDetail> entities) {
		orderDetailRepository.deleteInBatch(entities);
	}

	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderDetailRepository.findAll(example, pageable);
	}

	public Optional<OrderDetail> findById(Integer id) {
		return orderDetailRepository.findById(id);
	}

	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		orderDetailRepository.deleteAllInBatch(entities);
	}

	public boolean existsById(Integer id) {
		return orderDetailRepository.existsById(id);
	}

	public <S extends OrderDetail> long count(Example<S> example) {
		return orderDetailRepository.count(example);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderDetailRepository.deleteAllByIdInBatch(ids);
	}

	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return orderDetailRepository.exists(example);
	}

	public void deleteAllInBatch() {
		orderDetailRepository.deleteAllInBatch();
	}

	public OrderDetail getOne(Integer id) {
		return orderDetailRepository.getOne(id);
	}

	public <S extends OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderDetailRepository.findBy(example, queryFunction);
	}

	public long count() {
		return orderDetailRepository.count();
	}

	public void deleteById(Integer id) {
		orderDetailRepository.deleteById(id);
	}

	public OrderDetail getById(Integer id) {
		return orderDetailRepository.getById(id);
	}

	public void delete(OrderDetail entity) {
		orderDetailRepository.delete(entity);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderDetailRepository.deleteAllById(ids);
	}

	public OrderDetail getReferenceById(Integer id) {
		return orderDetailRepository.getReferenceById(id);
	}

	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		orderDetailRepository.deleteAll(entities);
	}

	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return orderDetailRepository.findAll(example);
	}

	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return orderDetailRepository.findAll(example, sort);
	}

	public void deleteAll() {
		orderDetailRepository.deleteAll();
	}

	public void deleteAllByOrder(Orders order) {
		orderDetailRepository.deleteAllByOrder(order);
	}

	
	
	
	
	
}
