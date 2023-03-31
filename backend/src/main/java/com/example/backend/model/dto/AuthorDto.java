package com.example.backend.model.dto;

import com.example.backend.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private String name;

    private String surname;

    private Long country;
}
