package com.evertix.reviewservice.resource;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComplaintResource {

    private Long id;

    private String subject;

    private String description;

    private Long madeById;

    private Long reportedId;
}
