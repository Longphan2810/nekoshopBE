package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryServiceInterface;

@Service
public class CategoryServiceImpl implements CategoryServiceInterface {

	CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@SuppressWarnings("unchecked")
	public Category save(Category cate) {
		if (categoryRepository.findByNameLike(cate.getName()) != null) {
			throw new RuntimeException("CATE_ALREADY_EXISTS");
		} else {
			return categoryRepository.save(cate);
		}
	}

	@SuppressWarnings("unchecked")
	public Category update(Category cate, int idCate) {

		if (getById(idCate) == null) {
			throw new RuntimeException("CATE_NOTFOUND");
		}

		if (categoryRepository.findByNameLike(cate.getName()) != null) {
			throw new RuntimeException("CATE_ALREADY_EXISTS");
		}
		cate.setIdCategory(idCate);
		return categoryRepository.save(cate);

	}

	public void deleteById(int idCate) {
		if (getById(idCate) == null) {
			throw new RuntimeException("CATE_NOTFOUND");
		}
		Category cate = getById(idCate);
		this.delete(cate);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	public Category getById(Integer id) {

		return findById(id).orElseThrow(() -> new RuntimeException("CATE_NOTFOUND"));

	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categoryRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		return categoryRepository.exists(example);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		return categoryRepository.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

	@Override
	public List<Category> findAllByNameLike(String name) {
		return categoryRepository.findAllByNameLike(name);
	}

	public Category findByNameLike(String name) {
		return categoryRepository.findByNameLike(name);
	}

}
