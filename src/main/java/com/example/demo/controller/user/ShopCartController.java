package com.example.demo.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ProductDetail;
import com.example.demo.domain.ShopingCart;
import com.example.demo.domain.Users;
import com.example.demo.request.CartRequest;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.CartRespone;
import com.example.demo.service.impl.CookieService;
import com.example.demo.service.impl.ProductDetailServiceImpl;
import com.example.demo.service.impl.SessionService;
import com.example.demo.service.impl.ShopingCartService;
import com.example.demo.service.impl.UserServiceImpl;

@CrossOrigin(originPatterns = { "**" })
@RestController
@RequestMapping("api")
public class ShopCartController {

	@Autowired
	ShopingCartService shopingCartService;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	SessionService session;

	@Autowired
	CookieService cookieService;

	@Autowired
	ProductDetailServiceImpl productDetailServiceImpl;

	@GetMapping("/shopingCart/{email}")
	public ApiRespone<List<CartRespone>> getShopingCart(@PathVariable("email") String email) {

		ApiRespone<List<CartRespone>> respone = new ApiRespone<>();

		Users user = userServiceImpl.findByEmail(email);

		System.out.println(user.getIdUser());

		List<ShopingCart> cart = shopingCartService.findAllByUser(user);
		List<CartRespone> cartRespone = new ArrayList<>();
		for (ShopingCart shopingCart : cart) {
			CartRespone temp = new CartRespone();
			temp.setIdProductDetail(shopingCart.getProductDetail().getIdProductDetail());
			temp.setName(shopingCart.getProductDetail().getProduct().getName());
			temp.setImgName(shopingCart.getProductDetail().getProduct().getNameImage());
			temp.setPrice(shopingCart.getPrice());
			temp.setQuantity(shopingCart.getQuantity());
			temp.setSize(shopingCart.getProductDetail().getSize());

			cartRespone.add(temp);

		}

		respone.setResult(cartRespone);
		return respone;
	}
	@GetMapping("/shopCartCheck/{email}")
	public ApiRespone<List<ProductDetail>> getShopCartCheck(@PathVariable("email") String email) {

		ApiRespone<List<ProductDetail>> respone = new ApiRespone<>();

		Users user = userServiceImpl.findByEmail(email);


		List<ShopingCart> cart = shopingCartService.findAllByUser(user);
		List<ProductDetail> listProductDetail = new ArrayList<>();
		for (ShopingCart shopingCart : cart) {

			listProductDetail.add(shopingCart.getProductDetail());

		}

		respone.setResult(listProductDetail);
		return respone;
	}

	@PostMapping("/shopingCart")
	public ApiRespone<ShopingCart> postCart(@RequestBody CartRequest cartRequest) {
		ApiRespone<ShopingCart> respone = new ApiRespone<>();

		respone.setResult(shopingCartService.addCart(cartRequest));
		System.out.println(cartRequest);

		return respone;
	}

	@PutMapping("/shopingCart")
	public ApiRespone<ShopingCart> putCart(@RequestBody CartRequest cartRequest) {
		ApiRespone<ShopingCart> respone = new ApiRespone<>();

		respone.setResult(shopingCartService.updateCart(cartRequest));

		return respone;
	}

	@DeleteMapping("/shopingCart/{emailUser}/{idProductDetail}")
	public ApiRespone<ShopingCart> deleteCart(@PathVariable("idProductDetail") int idProductDetail
			,@PathVariable("emailUser") String emailUser) {
		ApiRespone<ShopingCart> respone = new ApiRespone<>();

		respone.setResult(shopingCartService.deleteCart(idProductDetail,emailUser));

		return respone;
	}

}
