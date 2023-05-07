package com.rs.retailstore.repository;

import com.rs.retailstore.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository // để sử dụng được các hàm tương tác với database
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findByUsername(String username); //FindBy + field

}
