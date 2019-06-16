package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.AdvertisementDao;
import pl.motooto.webapp.dao.UserDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.CarDetails;
import pl.motooto.webapp.model.User;
import pl.motooto.webapp.model.dto.EditAdvertisementDto;
import pl.motooto.webapp.service.exception.UserNotLoggedInException;

@Service
public class EditAdvertisementService {
    private UserDao userDao;
    private AdvertisementDao advertisementDao;

    @Autowired
    public EditAdvertisementService(UserDao userDao, AdvertisementDao advertisementDao) {
        this.userDao = userDao;
        this.advertisementDao = advertisementDao;
    }

    public Advertisement updateAdvertisement(long id, EditAdvertisementDto editAdvertisementDto) throws UserNotLoggedInException {
        Advertisement advertisement = advertisementDao.findAdvertisementById(id);
        CarDetails carDetails = null;
        User user = null;

        carDetails = new CarDetails();

        advertisement.setTitle(editAdvertisementDto.getTitle());
        advertisement.setDescription(editAdvertisementDto.getDescription());
        advertisement.setPrice(editAdvertisementDto.getPrice());

        carDetails.setMake(editAdvertisementDto.getCarMake());
        carDetails.setModel(editAdvertisementDto.getCarModel());
        carDetails.setProductionYear(editAdvertisementDto.getProductionYear());
        carDetails.setEngineDisplacement(editAdvertisementDto.getEngineDisplacement());
        carDetails.setHorsePower(editAdvertisementDto.getHorsePower());
        carDetails.setDamaged(editAdvertisementDto.isDamaged());

        advertisement.setDetails(carDetails);

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = userDao.findByUsername(authentication.getName());
        } catch (Exception e) {
            throw new UserNotLoggedInException();
        }

        advertisement.setPublisher(user);

        advertisementDao.save(advertisement);

        return advertisement;
    }
}
