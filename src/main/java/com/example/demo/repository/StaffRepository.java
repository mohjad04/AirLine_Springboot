package com.example.demo.repository;


import com.example.demo.Model.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Staff findByUserId(Long userId);
}
