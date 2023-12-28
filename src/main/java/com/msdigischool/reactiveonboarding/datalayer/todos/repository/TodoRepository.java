package com.msdigischool.reactiveonboarding.datalayer.todos.repository;

import com.msdigischool.reactiveonboarding.datalayer.todos.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo , Long> {
}
