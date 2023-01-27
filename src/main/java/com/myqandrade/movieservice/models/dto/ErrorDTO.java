package com.myqandrade.movieservice.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDTO {

    private Integer status;
    private String message;
    private Date timestamp;
}
