package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.AddAdvertisementDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.CarDetails;
import pl.motooto.webapp.model.User;
import pl.motooto.webapp.model.dto.AddAdvertisementDto;

@Service
public class AddAdvertisementService {
    private AddAdvertisementDao addAdvertisementDao;

    @Autowired
    public AddAdvertisementService(AddAdvertisementDao addAdvertisementDao) {
        this.addAdvertisementDao = addAdvertisementDao;
    }

    public Advertisement addNewAdvertisement(AddAdvertisementDto addAdvertisementDto) {
        Advertisement advertisement = null;
        CarDetails carDetails = null;
        User user = null;

        advertisement = new Advertisement();
        carDetails = new CarDetails();
        user = new User();

        advertisement.setTitle(addAdvertisementDto.getTitle());
        advertisement.setDescription(addAdvertisementDto.getDescription());
        advertisement.setPrice(addAdvertisementDto.getPrice());

        carDetails.setMake(addAdvertisementDto.getCarMake());
        carDetails.setModel(addAdvertisementDto.getCarModel());
        carDetails.setProductionYear(addAdvertisementDto.getProductionYear());
        carDetails.setEngineDisplacement(addAdvertisementDto.getEngineDisplacement());
        carDetails.setHorsePower(addAdvertisementDto.getHorsePower());
        carDetails.setDamaged(addAdvertisementDto.isDamaged());

        advertisement.setDetails(carDetails);

        user.setId(1);

        advertisement.setPublisher(user);

        addAdvertisementDao.save(advertisement);

        return advertisement;
    }
}
