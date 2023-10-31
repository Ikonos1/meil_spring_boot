package com.grapplesoft.meil_backend.models.pagination;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class PagedData<T> {
    private T data;
    private Long totalElements;
    private Long totalPages;
    private Long currentPage;
    private Long pageSize;
}

