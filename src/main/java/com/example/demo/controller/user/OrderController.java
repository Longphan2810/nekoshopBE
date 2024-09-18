package com.example.demo.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.DeliveryAddress;
import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.Orders;
import com.example.demo.domain.ProductDetail;
import com.example.demo.domain.ShopingCart;
import com.example.demo.domain.Users;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.OrderDetailResponse;
import com.example.demo.service.impl.DeliveryAddressServiceImpl;
import com.example.demo.service.impl.OrderDetailServiceImpl;
import com.example.demo.service.impl.OrderServiceImpl;
import com.example.demo.service.impl.ProductDetailServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;

@CrossOrigin(originPatterns = {"**"})
@RestController
@RequestMapping("api")
public class OrderController {
	
	@Autowired
	ProductDetailServiceImpl productDetailServiceImpl;

	@Autowired
	OrderServiceImpl orderServiceImpl;

	@Autowired
	OrderDetailServiceImpl orderDetailServiceImpl;

	@Autowired
	DeliveryAddressServiceImpl deliveryAddressServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	@GetMapping("/order")
	public ApiRespone<List<Orders>> getListOrder(@RequestParam  String email){
		ApiRespone<List<Orders>> response = new ApiRespone<>();
		
		Users userCurrent = userServiceImpl.findByEmail(email);
		
		response.setResult(userCurrent.getListOrder());
		
		return response;
	}
	
	@GetMapping("/order/{idOrder}")
	public ApiRespone<List<OrderDetailResponse>> getOrderDetail(@PathVariable("idOrder")int idOrder){
		ApiRespone<List<OrderDetailResponse>> response = new ApiRespone<>();
		
		List<OrderDetailResponse> listResponse= new ArrayList<>();
		Orders order = orderServiceImpl.findById(idOrder).orElseThrow(()->new RuntimeException("ORDER_NULL"));
		
		for (OrderDetail orderDetail : order.getListOrderDetails()) {
			OrderDetailResponse temp = new OrderDetailResponse();
			
			temp.setAddress(order.getAddress());
			temp.setCity(order.getCity());
			temp.setImgProduct(orderDetail.getProductDetail().getProduct().getNameImage());
			temp.setNameProduct(orderDetail.getProductDetail().getProduct().getName());
			temp.setPhone(order.getPhone());
			temp.setName(order.getName());
			temp.setStatus(order.getStatus());
			temp.setPrice(orderDetail.getPrice());
			temp.setQuantity(orderDetail.getQuanlity());
			temp.setSize(orderDetail.getProductDetail().getSize());
			
			listResponse.add(temp);
		}
		
		response.setResult(listResponse);
		return response;
	}
	
	@PostMapping("/create-order")
	public ApiRespone<String> postOrder(@RequestParam String email) {
		ApiRespone<String> response = new ApiRespone<>();
		System.out.println("ok");
		System.out.println(email);
		Users userCurrent = userServiceImpl.findByEmail(email);

		DeliveryAddress da = deliveryAddressServiceImpl.findByUserAndStatus(userCurrent, true);

		
		// check stock
		if (!checkOutOfStock(userCurrent.getListShopingCart())) {

			throw new RuntimeException("LARGER_STOCK");
			
		}
		// check delivery address2810
		
		if (!checkDeliveryAddress(userCurrent)) {
//			model.addAttribute("message", "Vui lòng thêm hoặc kiểm tra địa chỉ giao hàng !");
//			model.addAttribute("mapShopCart", shopCartService.getMap());
			
			throw new RuntimeException("ADDRESS_DELIVERY_INVALID");
		}
		
			handldePaymentCash(userCurrent, da);
//		
			response.setMessage("done");

		return response;
	}

	private void handldePaymentCash(Users userCurrent,DeliveryAddress da) {


		List<ShopingCart> listShopingCart = userCurrent.getListShopingCart();

		float total  = 0;
		for (ShopingCart shopingCart : listShopingCart) {
			total =total +(shopingCart.getPrice() * shopingCart.getQuantity());
		}

		
		Orders order = new Orders();
		order.setUser(userCurrent);
		order.setAmount(total);
		order.setStatus("waiting");
		order.setPaymentMethod("cash");
		order.setName(da.getName());
		order.setAddress(da.getAddress());
		order.setCity(da.getCity());
		order.setPhone(da.getPhone());
		
		orderServiceImpl.save(order);

		for (ShopingCart shopingCart : listShopingCart) {
			ProductDetail productDetail = shopingCart.getProductDetail();

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProductDetail(productDetail);
			orderDetail.setOrder(order);
			orderDetail.setPrice(shopingCart.getPrice());
			orderDetail.setQuanlity(shopingCart.getQuantity());

			productDetail.setQuality(productDetail.getQuality() - shopingCart.getQuantity());

			orderDetailServiceImpl.save(orderDetail);
			productDetailServiceImpl.save(productDetail);

		}

	}
	
	
	// function will put value map parameter

		public boolean checkOutOfStock(List<ShopingCart> listShopingCart ) {
			

			boolean flatCheck = true;
			for (ShopingCart shopingCart : listShopingCart) {
				ProductDetail productDetail = productDetailServiceImpl.findById(shopingCart.getProductDetail().getIdProductDetail());
				if (shopingCart.getQuantity() > productDetail.getQuality()) {

					flatCheck = false;
				}

			}

			return flatCheck;
		}

		// check delivery address

		public boolean checkDeliveryAddress(Users userCurrent) {

			boolean check = true;
			DeliveryAddress da = deliveryAddressServiceImpl.findByUserAndStatus(userCurrent, true);
			if (da == null) {
				check = false;
			}
			return check;
		}
		

}
