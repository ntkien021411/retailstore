package com.rs.retailstore.model;


import lombok.Builder;
import lombok.Data;

@Data //Dùng thư viện lombok Tự động generate hàm Set Get
@Builder // Dùng thư viện  lombok để gán dữ liệu cho Greeting
public class Greeting {

    private long id;
    private String content;


}
