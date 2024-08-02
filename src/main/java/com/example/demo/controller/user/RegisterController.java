package com.example.demo.controller.user;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserDTO;
import com.example.demo.domain.Users;
import com.example.demo.respone.ApiRespone;
import com.example.demo.service.impl.MailServiceImpl;
import com.example.demo.service.impl.RegisterService;
import com.example.demo.service.impl.UserServiceImpl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(originPatterns = {"**"})
@RequestMapping("api")
public class RegisterController {
	@Autowired
	MailServiceImpl mailServiceImpl;
	@Autowired
	HttpServletRequest request;
	@Autowired
	RegisterService registerService;
	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("/register")
	public ApiRespone<String> getRegister(@RequestBody @Valid UserDTO userDto
			  ) throws MessagingException {
		
		
		System.out.println(userDto.toString());
		
		ApiRespone<String> respone = new ApiRespone<>();
			
	
		
		if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
//			model.addAttribute("signup", "s-signup");
//			model.addAttribute("message", "Vui lòng nhập 2 mật khẩu giống nhau !");
//			model.addAttribute("userDTO", userDto);
			System.out.println(userDto.getPassword());
			System.out.println(userDto.getConfirmPassword());
			throw new  RuntimeException("WRONG_PASS_CONFIRM");
		}
		
		
		Users userDB = userServiceImpl.findByEmail(userDto.getEmail());
		
		if(userDB!=null) {
			
			System.out.println("null");
//			model.addAttribute("signup", "s-signup");
//			model.addAttribute("message", "Đã tồn tại email trong hệ thống");
//			model.addAttribute("userDTO", userDto);
			throw new  RuntimeException("USE_ALREADY_EXIST");
		
		} 
		userDB = new Users();
		BeanUtils.copyProperties(userDto, userDB);
		
		registerService.setupToken(userDB);
		
		userServiceImpl.save(userDB);
		String linkCofirmAccount = userDB.getToken();
		mailServiceImpl.sendMaild(userDB.getEmail(),"Xác thực tài khoản",linkCofirmAccount);
		
	
		respone.setMessage("Đăng ký thành công, Vui lòng kiểm tra mail");
		return respone;
	}
	
	@GetMapping("/ConfirmAccount/{token}")
	public ApiRespone<String> getConfirmAcc(@PathVariable("token") String tokenUser) {
		Users userNeedConfirm = userServiceImpl.findByToken(tokenUser);
		ApiRespone<String> respone = new ApiRespone<>();
		System.out.println("+++++++++++++++++++++++++++++++++++");
		if(userNeedConfirm==null) {
			System.out.println("alo");
			throw new RuntimeException("OLD_TOKEN");
		}
		
		Date dateCurrent = new Date();
		
		if(dateCurrent.getTime()<userNeedConfirm.getTimeToken()) {
			System.out.println("ok");
			userNeedConfirm.setTimeToken(null);
			userNeedConfirm.setToken(null);
			userNeedConfirm.setStatus("active");
			userServiceImpl.save(userNeedConfirm);
		} else {
			userServiceImpl.delete(userNeedConfirm);
			
			throw new RuntimeException("EXPIRATION_TOKEN");
			
		}
		
		respone.setMessage("Đã xác thực tài khoản!");
		return respone;
	}
	
	@SuppressWarnings("unused")
	private String getUrlServier() {

		StringBuffer URL = request.getRequestURL();
		URL.delete(URL.lastIndexOf("/"), URL.length());

		return URL.toString() + "/ConfirmAccount?TokenUser=";
	}

}
