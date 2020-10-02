package com.evertix.reviewservice.resource;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReviewResource {
    private Long id;
    private String description;
    private Short stars;
    private Long studentId;
    private Long teacherId;
}
