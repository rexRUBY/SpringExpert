package org.example.expert.domain.search.repository;

import org.example.expert.domain.search.dto.SearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchQueryRepository {
    Page<SearchResponse> search(String title, String createdFrom, String createdTo, String nickname, Pageable pageable);
}

