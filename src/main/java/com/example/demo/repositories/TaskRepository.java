package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    //Optional<Task> findById(Long id);
    List<Task> findByUser_Id(Long id);
    
    //@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    //List<Task> findByUser_id(@Param("id") Long id);

    //@Query(value = "SELECT * FROM task WHERE t.user_id = :id", nativeQuery= true)
    //List<Task> findByUser_id(@Param("id") Long id);

}