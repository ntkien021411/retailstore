package com.rs.retailstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Configuration //quét qua các class này và check config với bean : cấu hình
// @Configuaration dùng được tính chất của java config
public class SecurityConfig {//Spring Security filter tất cả các config
    //Spring Security bắt buộc tất cả api phải login mới dc dùng

//        @Bean //Cho spring security duyệt quá cái bean này
//        public UserDetailsManager users(DataSource dataSource){ //Dùng UserDetailsManager chứ ko dùng UserDetailsService
//            //Tạo User với bulder chèn thêm field username và password và set giá trị
//            //Lưu trữ trên memory
//            UserDetails user = User.builder()
//                    .username("k1el")
//                    .password(bCrypt("123"))
//                    .roles("USER")
//                    .build();
////            UserDetails user = User.builder() // THÊM BCryptPasswordEncoder ở dưới thì ko cần thêm {bcrypt} trước chuỗi mã hóa
////                    .username("kien")
////                    .password("{bcrypt}$2a$10$A2uQMCa2Iw74eyTSrSYsDONUIOPG6wkK7myY4Q4/G8io4gnxDXstu")
////                    .roles("USER")
////                    .build();
//            UserDetails admin = User.builder()
//                    .username("adminn")
//                    .password(bCrypt("123a"))
//                    .roles("USER","ADMIN")
//                    .build();
//
//            //Tương tác với DB
//            JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource); // chỉ cần có khai báo này t có thể Read , Create , Update , Delete với database table user
//            //Tạo User mới trong database
////            users.createUser(admin);
////            users.createUser(user);
////
//
////            return new InMemoryUserDetailsManager(user,admin); // dùng với memory thì user và pass sẽ lấy từ đối tượng UserDetails ở trên
//                return users; // lấy từ database để check khi login với spring security
//        }

        @Bean // đặt bean để xác định passwordencode là encode kiểu bcrypt
        public PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        //Mã hóa bcrypt
        public String bCrypt(String password){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(password);
           return hashedPassword;
        }

        @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            //HttpSecurity:Chứa tất cả thông tin vè request
            //Mặc định security filter qua tất cả request api
            //Tất cả api cần authen trừ register với đoạn code dưới đây
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/v1/register").permitAll()
                .requestMatchers("/v1/greeting").authenticated()
                .and().formLogin()
//                .loginPage("/v1/testing") // điều hướng tới 1 trang ko phải trang login mặc định
                .and().httpBasic();
        return httpSecurity.build();

        }




}
