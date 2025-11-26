package hu.nje.java.gfekak.java.gyakorlat.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

/**
 * @author danielbodi
 */
@Entity
@Table(name="pizza")
public class Pizza {

    @Id
    @Column(name = "nev")
    private String name;

    @Column(name = "vegetarianus")
    private boolean vegetarian;

    @ManyToOne
    @JoinColumn(name = "kategorianev")
    private Category category;

    @OneToMany(mappedBy = "pizza")
    private List<Order> orderList;
}
