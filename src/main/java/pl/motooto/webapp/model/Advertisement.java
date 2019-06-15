package pl.motooto.webapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "advertisements")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false, length = 64)
    @Getter
    @Setter
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    @Getter
    @Setter
    private User publisher;

    @OneToOne
    @Getter
    @Setter
    private CarDetails details;

    @Getter
    @Setter
    private long lastModificationTimestamp = System.currentTimeMillis();

    @Getter
    @Setter
    private boolean active = true;

    @Getter
    @Setter
    private double price;

    @Column(columnDefinition = "TEXT")
    @Getter
    @Setter
    private String description;
}
