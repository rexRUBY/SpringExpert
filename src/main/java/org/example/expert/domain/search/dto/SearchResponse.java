package org.example.expert.domain.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    private String title;
    private long assignedUserCount;
    private long commentCount;
}