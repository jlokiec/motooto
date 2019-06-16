package pl.motooto.webapp.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AddAdvertisementDto {
    @NotEmpty
    @Size(max = 64)
    private String title;

    @NotEmpty
    private String description;

    private double price;

    @NotEmpty
    private String carMake;

    @NotEmpty
    private String carModel;

    private int productionYear;
    private int engineDisplacement;
    private int horsePower;
    private boolean damaged;
}
