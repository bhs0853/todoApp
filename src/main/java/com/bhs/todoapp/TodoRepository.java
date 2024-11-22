package com.bhs.todoapp;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    @Query("SELECT t FROM Todo t WHERE (:id = 0 or t.id = :id) and (:title = t.title or :title = '' or :title is null) and (:user_id = 0 or t.user_id = :user_id)")
    List<Todo> findBy(@Param("id") int id,@Param("user_id") int user_id,@Param("title") String title);

    @Query("select t from Todo t WHERE (t.completed = true)")
    List<Todo> findCompleted();

    @Modifying
    @Transactional
    @Query(value = "update Todo set title = :title where id = :id and :title is not null",nativeQuery = true)
    void updateTitle(@Param("id") int id,@Param("title") String title);

    @Modifying
    @Transactional
    @Query(value = "update Todo set completed = :completed where id = :id",nativeQuery = true)
    void updateStatus(@Param("id") int id,@Param("completed") boolean completed);
}
