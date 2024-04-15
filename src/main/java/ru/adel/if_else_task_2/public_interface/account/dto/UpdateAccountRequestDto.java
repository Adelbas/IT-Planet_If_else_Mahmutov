package ru.adel.if_else_task_2.public_interface.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateAccountRequestDto(

        @Min(1)
        Long id,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @Email
        String email,
        @NotBlank
        String password
) { }
