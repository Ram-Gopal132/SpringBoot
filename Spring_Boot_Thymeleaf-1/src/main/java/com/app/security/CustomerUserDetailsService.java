package com.app.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.app.repositeries.UserRepositry;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CustomerUserDetailsService implements UserDetailsService{
	

	@Autowired
	private UserRepositry userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		com.app.entity.User user=userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Email  "+username+" not found"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(com.app.entity.User user) {
		// TODO Auto-generated method stub
		String[] userRoles= user.getRole().stream().map(role->role.getName()).toArray(String[] :: new );
		
		Collection<GrantedAuthority> authorities=AuthorityUtils.createAuthorityList(userRoles);
		
		return authorities;
		
	}

}
