package com.rep.model;

import com.rep.model.enums.OrderingStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Orderings {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String date;
    @Enumerated(EnumType.STRING)
    private OrderingStatus status;

    @OneToMany(mappedBy = "ordering", orphanRemoval = true, fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Details> details;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;

    public Orderings(String date) {
        this.date = date;
        this.status = OrderingStatus.IN_THE_DESIGN;
        this.details = new ArrayList<>();
    }

    public void addDetail(Details details) {
        this.details.add(details);
        details.setOrdering(this);
    }

    public void removeDetail(Details details) {
        this.details.remove(details);
        details.setOrdering(null);
    }

    public int getPrice() {
        return details.stream().reduce(0, (i, d) -> i + (d.getCount() * d.getService().getPrice()), Integer::sum);
    }
}
