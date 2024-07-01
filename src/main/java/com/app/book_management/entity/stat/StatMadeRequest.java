package com.app.book_management.entity.stat;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StatMadeRequest {

    @NotNull
    List<String> selectFields;
    @NotNull
    String fromView;
    Map<String, String> whereConditions;
    Integer limit;
    Integer offset;
}
