package org.example.expert.domain.search.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.search.dto.SearchResponse;
import org.example.expert.domain.search.repository.SearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;

    public Page<SearchResponse> search(String title, String createdFrom, String createdTo, String nickname, Pageable pageable) {
        return searchRepository.search(title,createdFrom, createdTo, nickname, pageable);
    }
}
