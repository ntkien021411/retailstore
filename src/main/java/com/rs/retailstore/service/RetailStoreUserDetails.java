package com.rs.retailstore.service;

import com.rs.retailstore.model.Customer;
import com.rs.retailstore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //Dùng @Service để có thể @Autowird repository
public class RetailStoreUserDetails implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findByUsername(username);
        List<GrantedAuthority> authorities=null;
        String pass = null;
        if(customers.isEmpty()){
            throw new UsernameNotFoundException("User details not found for username = "+username);
        }
        //xác thực user và password từ database
        username = customers.get(0).getUsername();
        pass = customers.get(0).getPassword();

        //
        authorities = new ArrayList<>(); // list này chứa  role của customer
        authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole())); // add role của customer

        return new User(username,pass,authorities);
    }
}
