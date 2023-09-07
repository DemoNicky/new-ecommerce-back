package com.dobudobu.ecommerce.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseHandling<T> {

    private T data;

    private String errors;

}
