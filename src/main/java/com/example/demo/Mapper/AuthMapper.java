package com.example.demo.Mapper;

import com.example.demo.DTO.Request.CreateCustomerRequest;
import com.example.demo.Model.entities.Customer;
import com.example.demo.Model.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", source = "password")
    @Mapping(target = "createdAt", ignore = true)
    User toUser(CreateCustomerRequest req);

    @Mapping(target = "userId", ignore = true)
    Customer toCustomer(CreateCustomerRequest req);
}
