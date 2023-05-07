package com.rs.retailstore.Encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class Bcrypt {
    public static void main(String[] args) {
        String password = "123a";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        System.out.println(hashedPassword);
    }
}
