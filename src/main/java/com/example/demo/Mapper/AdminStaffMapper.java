package com.example.demo.Mapper;

import com.example.demo.DTO.Request.CreateStaffRequest;
import com.example.demo.Model.entities.Staff;
import com.example.demo.Model.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AdminStaffMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", source = "password")
    @Mapping(target = "createdAt", ignore = true)
    User toUser(CreateStaffRequest req);

    @Mapping(target = "userId", ignore = true)       // set after saving user
    @Mapping(target = "isEnabled", constant = "true")
    Staff toStaff(CreateStaffRequest req);
}
