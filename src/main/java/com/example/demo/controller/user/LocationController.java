package com.example.demo.controller.user;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.DeliveryAddress;
import com.example.demo.domain.Users;
import com.example.demo.request.AddressDeliveryResquest;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.impl.DeliveryAddressServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;


@CrossOrigin(originPatterns = {"**"})
@RestController
@RequestMapping("api")
public class LocationController {

	@Autowired
	DeliveryAddressServiceImpl deliveryAddressServiceImpl;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	@GetMapping("/locationCostumer/{email}")
	public  ApiRespone<List<DeliveryAddress>> getlocationCostumer(@PathVariable("email") String email) {
		 ApiRespone<List<DeliveryAddress>> response = new  ApiRespone<>();
		Users userCurrent = userServiceImpl.findByEmail(email);
		List<DeliveryAddress> listDeliveryByUser = deliveryAddressServiceImpl.findAllByUser(userCurrent);
	
		response.setResult(listDeliveryByUser);
		return response;

	}
	
//	@GetMapping("/locationCostumer/{id}")
//	public  ApiRespone<List<DeliveryAddress>> getlocationCostumerById(@PathVariable("id") int id) {
//		 ApiRespone<List<DeliveryAddress>> response = new  ApiRespone<>();
//		Users userCurrent = userServiceImpl.findByEmail(email);
//		List<DeliveryAddress> listDeliveryByUser = deliveryAddressServiceImpl.findAllByUser(userCurrent);
//	
//		response.setResult(listDeliveryByUser);
//		return response;
//
//	}
	
	@PostMapping("/locationCostumer")
	public ApiRespone<DeliveryAddress> getlocationCostumerAddAddress(@RequestBody AddressDeliveryResquest addressDeliveryResquest) {
	
		ApiRespone<DeliveryAddress> response = new ApiRespone<>()	;
		
		System.out.println(addressDeliveryResquest.toString());
		
		Users userCurrent = userServiceImpl.findByEmail(addressDeliveryResquest.getMailUserCurrent());
		
		DeliveryAddress deliveryAddress = new DeliveryAddress();


		try {
			Long.parseLong(addressDeliveryResquest.getPhone());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(addressDeliveryResquest.getPhone());
			throw new RuntimeException("phoneInvalid");
		}

		if(addressDeliveryResquest.getStatus()) {
			checkAndResetDefaultLocation(userCurrent);
			
		}
		
		BeanUtils.copyProperties(addressDeliveryResquest, deliveryAddress);
		
		deliveryAddress.setUser(userCurrent);

		deliveryAddressServiceImpl.save(deliveryAddress);

		
		return response;

	}
	
	
	@PutMapping("/locationCostumer/{id}")
	public ApiRespone<DeliveryAddress> putlocationCostumerAddAddress(@PathVariable("id") int idAddress,@RequestBody AddressDeliveryResquest addressDeliveryResquest) {
	
		ApiRespone<DeliveryAddress> response = new ApiRespone<>()	;
		
		System.out.println(addressDeliveryResquest.toString());
		
		DeliveryAddress deliveryAddress = deliveryAddressServiceImpl.findById(idAddress);
		
		Users userCurrent = userServiceImpl.findByEmail(addressDeliveryResquest.getMailUserCurrent());
		

		try {
			Long.parseLong(addressDeliveryResquest.getPhone());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(addressDeliveryResquest.getPhone());
			throw new RuntimeException("phoneInvalid");
		}

		if(addressDeliveryResquest.getStatus()) {
			checkAndResetDefaultLocation(userCurrent);
			
		}
		
		BeanUtils.copyProperties(addressDeliveryResquest, deliveryAddress);
		
		deliveryAddress.setUser(userCurrent);

		deliveryAddressServiceImpl.save(deliveryAddress);

		
		return response;

	}
	
	
	public void checkAndResetDefaultLocation( Users userCurrent) {

		List<DeliveryAddress> listDeliveryByUser = deliveryAddressServiceImpl.findAllByUser(userCurrent);

		if (!listDeliveryByUser.isEmpty()) {

			for (DeliveryAddress element : listDeliveryByUser) {
				element.setStatus(false);
				deliveryAddressServiceImpl.save(element);
			}

		}

	}
	
	
}
