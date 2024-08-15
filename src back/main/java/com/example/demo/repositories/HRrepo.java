package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.HRdetails;

import java.util.Optional;

public interface HRrepo extends JpaRepository<HRdetails ,Long>{

    HRdetails findByUsername(String username);
}
