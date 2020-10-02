package com.evertix.reviewservice.entities;


import com.evertix.reviewservice.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "complaints")
@Data
@EqualsAndHashCode(callSuper = true)
public class Complaint extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 250)
    private String subject;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 250)
    private String description;

    @Column(name = "madeby_id")
    private Long madeById;

    @Column(name = "reported_id")
    private Long reportedId;

    @Transient
    private User madeBy;

    @Transient
    private User reported;


    public Complaint(String subject, String description, Long madeById, Long reportedId) {
        this.description=description;
        this.subject=subject;
        this.madeById=madeById;
        this.reportedId=reportedId;
    }
}
