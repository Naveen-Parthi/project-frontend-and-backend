package com.example.demo.service;


import com.example.demo.entities.Tasks;

import java.util.List;

public interface TasksServ {
    public List<Tasks> getAll(Long Id);
    public Tasks addTask(Tasks task);

    public void changeStatus(Long Id);

    public void delTask(Long Id);
}
