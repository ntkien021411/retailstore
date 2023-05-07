package com.rs.retailstore.config;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName(); //Lấy username khi đăng nhập
        String password = authentication.getCredentials().toString(); //lấy password khi đăng nhập

        List<Customer> customerList =customerRepository.findByUsername(name);

        if(CollectionUtils.isEmpty(customerList)){
            throw new BadCredentialsException("No customer registerd with this username= " + name);
        }else{
            if(passwordEncoder.matches(password, customerList.get(0).getPassword() )){ // so sánh password với password trong databse với BCrypt
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customerList.get(0).getRole()));

                return new UsernamePasswordAuthenticationToken(name,password,authorities);
            }else{
                throw new BadCredentialsException("Invalid password for username= " + name);
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
