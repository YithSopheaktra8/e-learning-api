package co.istad.elearningapi.base;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseError<T> {
    private String code;
    private T description;
}