package com.evertix.reviewservice.resource;

import lombok.Data;

@Data
public class ComplaintResource {

    private Long id;

    private String subject;

    private String description;

    private Long madeById;

    private Long reportedId;
}
