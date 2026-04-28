package server.rem.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class QueryPaginate {
    private final String limit;

    private final String page;
}