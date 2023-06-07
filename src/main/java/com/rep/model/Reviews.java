package com.rep.model;

import com.rep.model.enums.Classifier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Reviews {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String username;
    @Column(length = 5000)
    private String review;
    private String date;
    @Enumerated(EnumType.STRING)
    private Classifier classifier;

    public Reviews(String username, String review, String date, Classifier classifier) {
        this.username = username;
        this.review = review;
        this.date = date;
        this.classifier = classifier;
    }
}
