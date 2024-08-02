package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ProductDetail;
import com.example.demo.domain.ShopingCart;
import com.example.demo.domain.Users;
import com.example.demo.repository.ShopingCartRepository;
import com.example.demo.request.CartRequest;

@Service
public class ShopingCartService {

	@Autowired
	ShopingCartRepository shopingCartRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	ProductDetailServiceImpl productDetailServiceImpl;

	public List<ShopingCart> findAllByUser(Users user) {
		return shopingCartRepository.findAllByUser(user);
	}

	public ShopingCart addCart(CartRequest cartRequest) {

		Users user = userServiceImpl.findByEmail(cartRequest.getEmailUser());
		
		System.out.println(cartRequest.getSize());
		System.out.println(cartRequest.getIdProduct());

		ProductDetail productDetail = productDetailServiceImpl.findBySizeAndIdProduct(cartRequest.getSize(), cartRequest.getIdProduct());

		
		
		List<ShopingCart> carts = this.findAllByUser(user);

		for (ShopingCart shopingCart : carts) {
			if (shopingCart.getProductDetail().getIdProductDetail() == productDetail.getIdProductDetail()) {
				shopingCart.setQuantity(shopingCart.getQuantity() + cartRequest.getQuantity());
				shopingCartRepository.save(shopingCart);
				return shopingCart;
			}
		}
		ShopingCart cart = new ShopingCart();
		cart.setUser(user);
		cart.setProductDetail(productDetail);
		cart.setQuantity(cartRequest.getQuantity());
		cart.setPrice(productDetail.getProduct().getPrice());
		shopingCartRepository.save(cart);

		return cart;
	}

	public ShopingCart updateCart(CartRequest cartRequest) {

		Users user = userServiceImpl.findByEmail(cartRequest.getEmailUser());

		ProductDetail productDetail = productDetailServiceImpl.findById(cartRequest.getIdProductDetail());


		List<ShopingCart> carts = this.findAllByUser(user);

		for (ShopingCart shopingCart : carts) {
			if (shopingCart.getProductDetail().getIdProductDetail() == productDetail.getIdProductDetail()) {
				if (cartRequest.getQuantity() <= 0) {
					shopingCartRepository.delete(shopingCart);
				} else {
					shopingCart.setQuantity(cartRequest.getQuantity());
					shopingCartRepository.save(shopingCart);
				}
				return shopingCart;
			}
		}

		throw new RuntimeException("");
	}
	
	public ShopingCart deleteCart(int idProductDetail,String emailUser) {

		Users user = userServiceImpl.findByEmail(emailUser);

		List<ShopingCart> carts = this.findAllByUser(user);

		for (ShopingCart shopingCart : carts) {
			if (shopingCart.getProductDetail().getIdProductDetail() == idProductDetail) {
				shopingCartRepository.delete(shopingCart);
				return shopingCart;
			}
		}

		throw new RuntimeException("");
	}

}
