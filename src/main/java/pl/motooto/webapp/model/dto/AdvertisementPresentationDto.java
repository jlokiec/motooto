package pl.motooto.webapp.model.dto;

import lombok.Data;

@Data
public class AdvertisementPresentationDto {
    private String title;
    private String username;
    private String lastModificationDate;
    private String priceInPln;
    private String priceInEur;
    private String priceInUsd;
    private String priceInGbp;
    private String description;
    private String carMake;
    private String carModel;
    private int productionYear;
    private int engineDisplacement;
    private int horsePower;
    private String carStatus;
    private String phoneNumber;
}
