package com.example.demo.controller;

import com.example.demo.entities.HRdetails;
import com.example.demo.servimpl.HRImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
public class HRController {

    @Autowired
    private HRImpl h;

    @GetMapping("/get/{Id}")
    public ResponseEntity<HRdetails> getHR(@PathVariable Long Id) {
        return ResponseEntity.ok(h.getHR(Id));
    }

    @PostMapping("/add")
    public ResponseEntity<HRdetails> addHR(@RequestBody HRdetails hr) {
        hr.setRole("hr");
        HRdetails ret = h.addHR(hr);
        return new ResponseEntity<>(hr, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<HRdetails> signIn(@RequestParam String username, @RequestParam String password) {
        HRdetails hr = h.checkHR(username, password);
        if (hr == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  // 401 Unauthorized
        }
        return new ResponseEntity<>(hr, HttpStatus.OK);  // 200 OK
    }
}
