package ru.geekbrains.spring_lesson41;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
@NamedQueries({
        @NamedQuery(name = "Product.findById",
                query = "select p from Product p where p.id = :id")})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "manufacture_date")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;


    public Product(Long id, String title, BigDecimal cost, LocalDate date, String name) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.date = date;
        this.manufacturer = new Manufacturer(name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", cost = " + cost +
                ", date = " + date +
                ", manufacturer = '" + manufacturer.getName() + '\'' +
                '}';
    }
}


