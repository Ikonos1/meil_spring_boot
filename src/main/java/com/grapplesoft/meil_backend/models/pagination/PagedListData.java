package com.grapplesoft.meil_backend.models.pagination;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class PagedListData<T> {
    private List<T> data;
    private Long totalElements;
    private int totalPages;
    private int currentPage;
    private int pageSize;
}
