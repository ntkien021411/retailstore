package com.rs.retailstore.controller;

import com.rs.retailstore.model.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1") // Chạy từ localhost xong đến v1 thì mới có thể truy cập vào các api bên trong
public class CustomerGreetingController {


    private static final String greetingTemplate = "Hello, %s %s";
   private final AtomicLong counter = new AtomicLong(); // Chứa 1 số kiểu long

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value ="gender",defaultValue = "0") boolean gender
    ,@RequestParam(value = "customer",defaultValue = "Customer") String name){
        return Greeting.builder()
                .id(counter.incrementAndGet()) // incrementAndGet() tự động tăng lên 1 sau khi vào http Get Method
                .content(String.format(greetingTemplate,gender ? "Mr" : "Mrs",name)) //format(string,parameter) : paramter phải tương ứng với số % trong string
                .build();
    }

    @GetMapping("/testing")
    public Greeting testing(@RequestParam(value ="gender",defaultValue = "0") boolean gender
            ,@RequestParam(value = "customer",defaultValue = "Cus") String name){
        return Greeting.builder()
                .id(counter.incrementAndGet()) // incrementAndGet() tự động tăng lên 1 sau khi vào http Get Method
                .content(String.format(greetingTemplate,gender ? "Mrr" : "Mrss",name)) //format(string,parameter) : paramter phải tương ứng với số % trong string
                .build();
    }
}

