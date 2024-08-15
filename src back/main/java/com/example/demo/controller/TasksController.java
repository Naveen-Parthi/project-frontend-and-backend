package com.example.demo.controller;

import com.example.demo.entities.Tasks;
import com.example.demo.servimpl.TasksImpl;
import com.example.demo.servimpl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")


public class TasksController {
    //Create Tasks
    //Get using UserId
    //Mark as Completed
    //Delete Tasks

    @Autowired
    private TasksImpl t;

    @Autowired
    private UserImpl u;

    @GetMapping("/get/{Id}")
    public List<Tasks> getAllById(@PathVariable Long Id){
        return t.getAll(Id);
    }

    @PostMapping("/add")
    public ResponseEntity<Tasks> addTask(@RequestBody Tasks task, @RequestParam Long user){
        task.setUser(u.getUser(user));
        task.setCompletionStatus(false);
        Tasks ret = t.addTask(task);
        return new ResponseEntity<>(ret, HttpStatus.CREATED);
    }

    @PatchMapping("/done/{Id}")
    public String changeStatus(@PathVariable Long Id){
        t.changeStatus(Id);
        return "Changed";
    }

    @DeleteMapping("/delete/{Id}")
    public String delTasks(@PathVariable Long Id){
        t.delTask(Id);
        return "Deleted";
    }
}
