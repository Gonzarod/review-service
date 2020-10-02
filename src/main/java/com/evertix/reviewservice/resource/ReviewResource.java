package com.evertix.reviewservice.resource;

import lombok.Data;


@Data
public class ReviewResource {
    private Long id;
    private String description;
    private Short stars;
    private Long studentId;
    private Long teacherId;
}
