package com.rs.retailstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity //để spring biết dc class này và tạo bean cho table customer trong database
//@Data // Dùng lombok tự động tạo get set
@Getter //Tạo get
@Setter // Tạo set
public class Customer {

    //@Column // các field trùng tên nên là ko cần phải set name trong column cho các field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tự động tạo và chèn vào database , CHUYỂN TỪ AUTO SANG IDENTITY trong trường hợp register lỗi
    private int id;


    private  String username;
    private String password;

    private String role;
}
