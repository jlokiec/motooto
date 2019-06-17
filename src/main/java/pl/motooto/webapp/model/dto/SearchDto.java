package pl.motooto.webapp.model.dto;

import lombok.Data;

@Data
public class SearchDto {

    private double price_beg;

    private double price_end;

    private String carMake;

    private String carModel;

    private int productionYear_beg;

    private int productionYear_end;

    private boolean damaged;

}
