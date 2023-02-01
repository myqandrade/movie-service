package com.myqandrade.movieservice.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ErrorDTO {

    private List<String> errorMessage;

    public ErrorDTO(List<String> list){
        this.errorMessage = list;
    }

    public ErrorDTO(String error){
        this.errorMessage = Arrays.asList(error);
    }
}
