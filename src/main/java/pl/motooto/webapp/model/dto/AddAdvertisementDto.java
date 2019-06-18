package pl.motooto.webapp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AddAdvertisementDto {
    @NotEmpty
    @Size(min = 2, max = 64)
    private String title;

    @NotEmpty
    @Size(min = 2, max = 64)
    private String description;

    @NotEmpty
    @Size(min = 2, max = 16)
    private String phoneNumber;

    private double price;

    @NotEmpty
    @Size(min = 2, max = 64)
    private String carMake;

    @NotEmpty
    @Size(min = 2, max = 64)
    private String carModel;

    private int productionYear;
    private int engineDisplacement;
    private int horsePower;
    private boolean damaged;
}
