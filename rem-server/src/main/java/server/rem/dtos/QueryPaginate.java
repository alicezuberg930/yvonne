package server.rem.dtos;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QueryPaginate {
    private String limit;

    private String page;
}