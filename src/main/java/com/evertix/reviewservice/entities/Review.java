package com.evertix.reviewservice.entities;

import com.evertix.reviewservice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name="reviews")
@Getter
@Setter
public class Review extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be null")
    @Size(max = 250)
    private String description;

    // ONLY FOR STUDENT
    @Max(value = 5)
    private Short stars;

    @Transient
    private User studentModel;

    @Transient
    private User teacherModel;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "teacher_id")
    private Long teacherId;


    public Review(Short stars, String description, Long studentId, Long teacherId) {
        this.description=description;
        this.stars=stars;
        this.studentId=studentId;
        this.teacherId=teacherId;
    }

    public Review() {

    }
}
