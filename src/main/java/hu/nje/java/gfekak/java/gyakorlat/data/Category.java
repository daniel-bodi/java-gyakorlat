package hu.nje.java.gfekak.java.gyakorlat.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

/**
 * @author danielbodi
 */
@Entity
@Table(name = "kategoria")
public class Category {

    @Id
    @Column(name = "nev")
    private String name;

    @Column(name = "ar")
    private int price;

    @OneToMany(mappedBy = "category")
    private List<Pizza> pizzaList;
}
