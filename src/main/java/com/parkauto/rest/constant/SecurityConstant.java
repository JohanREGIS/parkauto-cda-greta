package com.parkauto.rest.constant;

public class SecurityConstant {

	public static final long EXPIRATION_TIME = 432_000_000; // 5 jours en millisecondes
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String JWT_TOKEN_HEADER = "Jwt-Token";
	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
	public static final String GET_PARKAUTO_ARRAYS = "Get ParkAuto Company";
	public static final String GET_ADMINISTRATION_ARRAYS = "User Management Dashboard";
	public static final String AUTHORITIES = "authorities";
	public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page.";
	public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
	public static final String OPTION_HTTP_METHOD = "OPTIONS";
	//public static final String[] PUBLIC_URLS = {"/user/login", "user/register", "user/resetpassword/**", "user/image/**"};	// Urls que l'on ne veut pas bloquer et on autorise tout ce qui est après le /**
	public static final String[] PUBLIC_URLS = {"**"};
}
