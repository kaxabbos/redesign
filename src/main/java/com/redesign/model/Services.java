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
public class Services {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String unit;
    private int price;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stats stats;

    public Services(String name, String unit, int price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }
}
