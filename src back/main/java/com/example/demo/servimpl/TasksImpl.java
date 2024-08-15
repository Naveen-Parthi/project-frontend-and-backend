package com.example.demo.servimpl;

import com.example.demo.entities.Tasks;
import com.example.demo.entities.Userdetails;
import com.example.demo.repositories.Tasksrepo;
import com.example.demo.service.TasksServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TasksImpl implements TasksServ {

    @Autowired
    private Tasksrepo t;

    @Override
    public List<Tasks> getAll(Long Id){
        List<Tasks> gets = new ArrayList<>();
        for(Tasks t : t.findAll()){
            if(t.getUser().getId().equals(Id))
                gets.add(t);
        }
        return gets;
    }

    @Override
    public Tasks addTask(Tasks task){
        return t.save(task);
    }

    @Override
    public void changeStatus(Long Id){
        Tasks c = t.findById(Id).orElse(null);
        if(c==null){
            return;
        }
        c.setCompletionStatus(true);
        t.save(c);
    }

    @Override
    public void delTask(Long Id){
        t.deleteById(Id);
    }
}
