package hu.nje.java.gfekak.java.gyakorlat.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * @author danielbodi
 */
@Entity
@Table(name = "rendeles")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "az")
    private int id;

    @Column(name = "darab")
    private int count;

    @Column(name = "felvetel")
    private LocalDateTime receivedAt;

    @Column(name = "kiszallitas")
    private LocalDateTime shippedAt;

    @ManyToOne
    @JoinColumn(name = "pizzanev")
    private Pizza pizza;
}
