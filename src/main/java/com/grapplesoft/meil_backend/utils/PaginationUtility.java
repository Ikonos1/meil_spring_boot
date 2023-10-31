package com.grapplesoft.meil_backend.utils;


import com.grapplesoft.meil_backend.models.pagination.PagedListData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PaginationUtility {
    public static Pageable buildPageable(int page, int size) {
        return Pageable.ofSize(size).withPage(page);
    }

    public static Pageable buildPageableUnpaged() {
        return Pageable.unpaged();
    }

    public static <T> PagedListData<T> buildPagedListData(Page<T> data) {
        return PagedListData.<T>builder()
                .data(data.toList())
                .totalPages(data.getTotalPages())
                .totalElements(data.getTotalElements())
                .currentPage(data.getPageable().isPaged() ? data.getPageable().getPageNumber() : 0)
                .pageSize(data.getSize())
                .build();
    }

    public static <T> PagedListData<T> buildPagedListData(List<T> data, Long totalElements, int totalPages, int currentPage, int pageSize) {
        return PagedListData.<T>builder()
                .data(data)
                .totalPages(Math.max(totalPages, 0))
                .totalElements(totalElements)
                .currentPage(currentPage)
                .pageSize(pageSize)
                .build();
    }
}
