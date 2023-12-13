package com.kh.springdb.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
  변수
  	변할 수 있는 수
  	
   
   
  상수
  	상시적으로 언제나 한결같은 수 (final) 
  	
  	
  	
  	
  	public static final
  	private static final
  	private final 
  	
  	
  	enum : final 상수 집합을 나타낼 때 사용하는 값
  
  
 * */



@Getter
public enum UserRole {
	//ADMIN 
	// 나열해야 하는 상수들은 , 사용해서 나열하고 나열을 종료할 때는 최종적으로 ;을 사용함 
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
	//현재 유저가 ADMIN 인지 USER인지 로그인하기 전까지 파악되지 않기 때문에 value 라는 
	// 값으로 추후에 로그인 할 경우 value 에다가 admin 또는 user를 넣어준다
	private final String value;
	
	
	UserRole(String value){
		this.value = value;
		
	}
	
}
