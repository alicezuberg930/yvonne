package server.rem.dtos;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomPageResponse<T> {
    private final List<T> content;
    private final int currentPage;
    private final int totalPages;
    private final long totalElements;
    private final int pageSize;
    private final boolean hasNext;
    private final boolean hasPrevious;
    private final Integer nextPage;
    private final Integer previousPage;

    public CustomPageResponse(Page<T> page) {
        this.content = page.getContent();
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageSize = page.getSize();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
        this.nextPage = page.hasNext() ? page.getNumber() + 1 : null;
        this.previousPage = page.hasPrevious() ? page.getNumber() - 1 : null;
    }
}