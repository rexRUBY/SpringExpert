package org.example.expert.domain.search.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Todo, Long>, SearchQueryRepository {
}
