package com.example.demo.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private UserService userService;

    public Task findById(Long id) {
        Optional <Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
            "Tarefa nao encontrada"
        ));
    }

    @Transactional
    public Task create(Task obj){
        User user = this.userService.findById(obj.getId());
        obj.setId(null);
        obj.setUser(user);
        obj =this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    //@Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw  new RuntimeException("Não é possivel deletar pois há entidades relacionadas");
        }
    }
}
