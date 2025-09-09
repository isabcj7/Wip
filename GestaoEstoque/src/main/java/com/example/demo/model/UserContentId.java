package com.example.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserContentId implements Serializable {
    private Integer user; 
    private Integer content; 
}