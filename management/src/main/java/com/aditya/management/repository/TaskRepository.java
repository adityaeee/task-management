package com.aditya.management.repository;

import com.aditya.management.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    @Query(value = "SELECT * FROM tasks WHERE title LIKE %:keyword%", nativeQuery = true)
    List<Task> searchTasksByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM tasks WHERE due_date < ?1 AND status !='Selesai'", nativeQuery = true)
    List<Task> findOverdueTasks(Date dueDate);

    @Query(value = "SELECT * FROM tasks WHERE user_id = ?1", nativeQuery = true)
    List<Task> findTasksByUserId(String userId);
}
