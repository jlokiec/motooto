package pl.motooto.webapp.model.dto;

import lombok.Data;
import pl.motooto.webapp.model.Advertisement;

import java.util.List;

@Data
public class UserAdvertisementsDto {
    List<Advertisement> advertisements;
}
