package com.redesign.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Details {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private int count;
    @ManyToOne(fetch = FetchType.LAZY)
    private Orderings ordering;
    @OneToOne(fetch = FetchType.LAZY)
    private Services service;

    public Details(int count, Services service) {
        this.count = count;
        this.service = service;
    }
}
