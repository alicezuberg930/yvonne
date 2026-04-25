package server.rem.dtos;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaginateQuery {
    private String limit;

    private String page;
}