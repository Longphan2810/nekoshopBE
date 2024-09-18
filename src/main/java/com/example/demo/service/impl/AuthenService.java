package com.example.demo.service.impl;

import java.time.Instant;
import java.util.Date;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Enum.UserStatusEnum;
import com.example.demo.domain.Users;
import com.example.demo.request.AuthenRequest;
import com.example.demo.respone.ApiRespone;
import com.example.demo.respone.AuthenRespone;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

@Service
public class AuthenService {

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	CookieService cookieService;

	@Value("${jwt.secretKey}")
	private String secretKey;

	public ApiRespone<AuthenRespone> authenAccount(AuthenRequest authRequest) {

		Users userNeedAuthen = userServiceImpl.findByEmail(authRequest.getEmail());

		PasswordEncoder pe = new BCryptPasswordEncoder(10);		
		if (userNeedAuthen == null) {
			throw new RuntimeException("USE_NOT_EXIST");
		}
		if (userNeedAuthen.getStatus().equalsIgnoreCase(UserStatusEnum.block.name())) {
			throw new RuntimeException("BLOCKED_USER");
		}
		if (userNeedAuthen.getStatus().equalsIgnoreCase(UserStatusEnum.wait.name())) {
			throw new RuntimeException("USER_NEED_VERIFY_ACCOUNT");
		}
		
		if (!pe.matches(authRequest.getPassword(), userNeedAuthen.getPassword())) {
			System.out.println(pe.encode(userNeedAuthen.getPassword()));
			throw new RuntimeException("WRONG_PASSWORD");
		}
		ApiRespone<AuthenRespone> respone = new ApiRespone<AuthenRespone>();
		AuthenRespone authRespone = new AuthenRespone();
		String token = generateToken(userNeedAuthen);

		authRespone.setAuthen(true);
		authRespone.setToken(token);
		authRespone.setEmailUser(authRequest.getEmail());
		respone.setResult(authRespone);

		return respone;
	}

	private String generateToken(Users userNeedAuthen) {

		JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

		JWTClaimsSet claim = new JWTClaimsSet.Builder().issueTime(new Date())
				.expirationTime(new Date(Instant.now().plusSeconds(60 * 60 * 24).toEpochMilli()))
				.claim("scope", userNeedAuthen.isRole() ? "ADMIN" : "USER").claim("mail", userNeedAuthen.getEmail())
				.subject(userNeedAuthen.getName()).build();

		Payload payload = new Payload(claim.toJSONObject());

		JWSObject jwsObject = new JWSObject(header, payload);

		try {
			jwsObject.sign(new MACSigner(secretKey.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("chua biet ");
		}

	}

}
