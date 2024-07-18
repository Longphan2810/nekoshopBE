package com.example.demo.repository;



import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.DTO.ReportByProduct;
import com.example.demo.domain.Orders;
import com.example.demo.domain.ProductDetail;
import com.example.demo.domain.Users;

public interface OrderRepository extends JpaRepository<Orders, Integer>{

	public Page<Orders> findAllByUser(Users user, Pageable pageable);
	
	public Page<Orders> findAllByIdOrder(int id, Pageable pageable);
	
	public Page<Orders> findAllByIdOrderLike(int id, Pageable pageable);
	
	// Select * from Orders o where o.status ='done'
	
	@Query(" Select new com.example.demo.DTO.ReportByProduct(od.productDetail, sum( od.quanlity)) from OrderDetail od where od.order IN (Select o from Orders o where o.status ='done')"
			+ " Group by od.productDetail")
	public List<ReportByProduct> getReportProduct();
	
	@Query(" Select new com.example.demo.DTO.ReportByProduct(od.productDetail, sum( od.quanlity)) from OrderDetail od where od.order IN (Select o from Orders o where o.status ='done' and o.orderDate = ?1 )"
			+ " Group by od.productDetail")
	public List<ReportByProduct> getReportProductDate(Date date);
	
	
	public Orders findBytransactionCode(String transactionCode);
	
	public List<Orders> findAllByPayedVNpayFalse();
	
	@Query("select o.orderDate from Orders o  group by o.orderDate")
	public List<Date> getListDateOrder();

	
}
