package pl.motooto.webapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "car_details")
@Data
public class CarDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 32)
    private String make;

    @Column(nullable = false, length = 32)
    private String model;

    private int productionYear;

    private int engineDisplacement;

    private int horsePower;

    private boolean damaged;
}
