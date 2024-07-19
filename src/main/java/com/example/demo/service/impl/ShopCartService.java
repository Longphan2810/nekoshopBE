package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.DTO.ItemForCart;
import com.example.demo.domain.ProductDetail;




@SessionScope
@Service
public class ShopCartService {

	@Autowired
	 ProductDetailServiceImpl productDetailServiceImpl;
	
	
	Map<ItemForCart, Integer> shopCart = new HashMap<>();
	
	public Map<ItemForCart, Integer> getMap(){
		return this.shopCart;
	}
	
	public void reFillQualityProduct() {
		
		Set<ItemForCart> keySet = shopCart.keySet();
		 Map<ItemForCart, Integer> updatedCart = new HashMap<>();
		
		for (ItemForCart itemForCart : keySet) {
			
			int qualityBuy = shopCart.get(itemForCart);
			ProductDetail pd = productDetailServiceImpl.findById(itemForCart.getIdProductDetail());
		
			itemForCart.setQuality(pd.getQuality());
			updatedCart.put(itemForCart, qualityBuy);
		}
		
		
		shopCart.clear();
		shopCart.putAll(updatedCart);
		
	}
	
	public void setMap(Map<ItemForCart, Integer> shopCartNew) {
		
		shopCart =shopCartNew;
	}
	
	public float getTotalPrice() {
		
		float totalPrice =0;
		
		Set<ItemForCart> keySet = shopCart.keySet();
		
		for (ItemForCart itemForCart : keySet) {
			
			totalPrice += (itemForCart.getPrice() *shopCart.get(itemForCart)) ;
		}
		
		return totalPrice;
		
	}
	
	
	public int size() {
		
		return this.shopCart.size();
	}

	public void addItem(ItemForCart product, int soLuong) {

		if (shopCart.containsKey(product)) {

			shopCart.put(product, shopCart.get(product) + soLuong);
			

		} else {

			shopCart.put(product, soLuong);
		

		}

	}

	public void remove(ItemForCart product) {

		if (shopCart.containsKey(product)) {

			shopCart.remove(product);

		}

	}

	public void update(ItemForCart product, int soLuong) {

		if (shopCart.containsKey(product)) {

			if(soLuong<1) {
				shopCart.remove(product);
			} else {
				shopCart.put(product, soLuong);

			}
			
		}

	}
	
	public void clear() {
		
		shopCart.clear();
	}
	
	

}
