package com.example.demo.service;

import com.example.demo.entities.HRdetails;

public interface HRServ {

    public HRdetails getHR(Long Id);

    public HRdetails addHR(HRdetails HR);

    public HRdetails checkHR(String username,String password);

}
