package com.example.demo.servimpl;

import com.example.demo.entities.HRdetails;
import com.example.demo.repositories.HRrepo;
import com.example.demo.service.HRServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRImpl implements HRServ {

    @Autowired
    private HRrepo h;

    @Override
    public HRdetails getHR(Long Id){
        return h.findById(Id).orElse(null);
    }

    @Override
    public HRdetails addHR(HRdetails hr){
        return h.save(hr);
    }

    @Override
    public HRdetails checkHR(String username, String password){
        HRdetails got = h.findByUsername(username);
        if(got==null)
            return null;
        else if (got.getPassword().equals(password))
            return got;
        return null;
    }
}
