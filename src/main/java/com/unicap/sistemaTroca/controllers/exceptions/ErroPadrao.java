package com.unicap.sistemaTroca.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErroPadrao implements Serializable {

    private ZonedDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
