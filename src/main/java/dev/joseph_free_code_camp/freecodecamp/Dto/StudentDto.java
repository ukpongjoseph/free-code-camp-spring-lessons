package dev.joseph_free_code_camp.freecodecamp.Dto;

import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
    @NotEmpty 
    String firstname,

    @NotEmpty
    String lastname,

    String email,
    
    Integer schoolId
) {
    
}
