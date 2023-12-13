package com.kh.springdb.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.kh.springdb.model.SiteUser;
import com.kh.springdb.model.UserRole;
import com.kh.springdb.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	//회원가입할 경우 계정을 생성해주기 위해 Service를 만들어줌 
	//기존 서비스에서 했던 회원가입과 조금 다른 점은
	//비밀번호를 암호화 처리해서 저장해주는 것이 조금 다름
	
	
	public SiteUser createUser(String username, String email, String password, UserRole role) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		
		//사용자 역할 설정
		user.setIsRole(role);
		
		
		userRepository.save(user);
		
		return user;
	}
	@Transactional(readOnly = true)
    public ModelAndView login(String username, String password) {
        ModelAndView modelAndView = new ModelAndView();

        Optional<SiteUser> optionalUser = userRepository.findByusername(username);

        optionalUser.ifPresent(user -> {
            boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());

            if (isPasswordMatch) {
                modelAndView.setViewName("redirect:/list");
                modelAndView.addObject("message", "환영합니다!");
            } else {
                modelAndView.setViewName("redirect:/login?error=true");
            }
        });

        if (optionalUser.isEmpty()) {
            modelAndView.setViewName("redirect:/login?error=true");
        }

        return modelAndView;
    }

}
