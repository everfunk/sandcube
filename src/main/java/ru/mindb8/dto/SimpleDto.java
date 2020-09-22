package ru.mindb8.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleDto {
    @JsonProperty("id")
    private String id;
}
