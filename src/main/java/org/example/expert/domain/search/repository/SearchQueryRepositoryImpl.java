package org.example.expert.domain.search.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.search.dto.SearchResponse;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class SearchQueryRepositoryImpl implements SearchQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<SearchResponse> search(String title, String createdFrom, String createdTo, String nickname, Pageable pageable) {
        QTodo todo = QTodo.todo;

        // 데이터 조회
        List<Todo> results = queryFactory
                .selectFrom(todo)
                .where(
                        titleContains(title),
                        createdDateBetween(createdFrom, createdTo),
                        nicknameContains(nickname)
                )
                .orderBy(todo.createdAt.desc()) // 최신순 정렬
                .offset(pageable.getOffset())     // 페이지 시작 위치
                .limit(pageable.getPageSize())    // 페이지 크기 설정
                .fetch();

        List<SearchResponse> responseList = results.stream()
                .map(todoItem -> new SearchResponse(
                        todoItem.getTitle(),
                        todoItem.getManagers().size(),
                        todoItem.getComments().size()
                ))
                .collect(Collectors.toList());

        // 총 데이터 개수 조회
        long total = queryFactory
                .select(todo.count())
                .from(todo)
                .where(
                        titleContains(title),
                        createdDateBetween(createdFrom, createdTo),
                        nicknameContains(nickname)
                )
                .fetchOne();

        return new PageImpl<>(responseList, pageable, total);
    }


    private BooleanExpression titleContains(String title) {
        return title != null ? QTodo.todo.title.containsIgnoreCase(title) : null;
    }

    private BooleanExpression createdDateBetween(String createdFrom, String createdTo) {
        LocalDateTime from = createdFrom != null ? LocalDateTime.parse(createdFrom) : null;
        LocalDateTime to = createdTo != null ? LocalDateTime.parse(createdTo) : null;

        if (from != null && to != null) {
            return QTodo.todo.createdAt.between(from, to);
        } else if (from != null) {
            return QTodo.todo.createdAt.goe(from);
        } else if (to != null) {
            return QTodo.todo.createdAt.loe(to);
        } else {
            return null;
        }
    }

    private BooleanExpression nicknameContains(String nickname) {
        return nickname != null ? QTodo.todo.managers.any().user.nickname.containsIgnoreCase(nickname) : null;
    }
}
