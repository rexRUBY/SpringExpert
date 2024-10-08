package org.example.expert.domain.search.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.search.dto.SearchResponse;
import org.example.expert.domain.search.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<Page<SearchResponse>> search(
            @AuthenticationPrincipal AuthUser authUser,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "createdFrom", required = false) String createdFrom,
            @RequestParam(value = "createdTo", required = false) String createdTo,
            @RequestParam(value = "nickname", required = false) String nickname,
            @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(searchService.search(title, createdFrom, createdTo, nickname, pageable));
    }
}
