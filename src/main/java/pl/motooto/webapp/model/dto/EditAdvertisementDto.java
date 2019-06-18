package pl.motooto.webapp.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class EditAdvertisementDto {
    @NotEmpty
    @Size(max = 64)
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private String phoneNumber;

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
