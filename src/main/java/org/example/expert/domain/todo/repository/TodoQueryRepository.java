package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;

public interface TodoQueryRepository {
    Todo findByIdWithUser(long todoId);
}
