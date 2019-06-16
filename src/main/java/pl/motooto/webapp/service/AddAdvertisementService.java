package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.AddAdvertisementDao;
import pl.motooto.webapp.dao.UserDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.CarDetails;
import pl.motooto.webapp.model.User;
import pl.motooto.webapp.model.dto.AddAdvertisementDto;
import pl.motooto.webapp.service.exception.UserNotLoggedInException;

@Service
public class AddAdvertisementService {
    private UserDao userDao;
    private AddAdvertisementDao addAdvertisementDao;

    @Autowired
    public AddAdvertisementService(UserDao userDao, AddAdvertisementDao addAdvertisementDao) {
        this.userDao = userDao;
        this.addAdvertisementDao = addAdvertisementDao;
    }

    public Advertisement addNewAdvertisement(AddAdvertisementDto addAdvertisementDto) throws UserNotLoggedInException {
        Advertisement advertisement = null;
        CarDetails carDetails = null;
        User user = null;

        advertisement = new Advertisement();
        carDetails = new CarDetails();

        advertisement.setTitle(addAdvertisementDto.getTitle());
        advertisement.setDescription(addAdvertisementDto.getDescription());
        advertisement.setPhoneNumber(addAdvertisementDto.getPhoneNumber());
        advertisement.setPrice(addAdvertisementDto.getPrice());

        carDetails.setMake(addAdvertisementDto.getCarMake());
        carDetails.setModel(addAdvertisementDto.getCarModel());
        carDetails.setProductionYear(addAdvertisementDto.getProductionYear());
        carDetails.setEngineDisplacement(addAdvertisementDto.getEngineDisplacement());
        carDetails.setHorsePower(addAdvertisementDto.getHorsePower());
        carDetails.setDamaged(addAdvertisementDto.isDamaged());

        advertisement.setDetails(carDetails);

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = userDao.findByUsername(authentication.getName());
        } catch (Exception e) {
            throw new UserNotLoggedInException();
        }

        advertisement.setPublisher(user);

        addAdvertisementDao.save(advertisement);

        return advertisement;
    }
}
