package com.cliente.Model.Response;

import lombok.Data;

import java.util.List;

@Data
public class PaginacionResponse<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int pageNumber;
    private int pageSize;
}
