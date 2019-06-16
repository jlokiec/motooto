package pl.motooto.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.motooto.webapp.dao.UserAdvertisementsDao;
import pl.motooto.webapp.dao.UserDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.CarDetails;
import pl.motooto.webapp.model.dto.UserAdvertisementsDto;
import pl.motooto.webapp.service.UserAdvertisementsService;
import pl.motooto.webapp.service.exception.UserNotLoggedInException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class UserAdvertisementsServiceTest {
    private static UserAdvertisementsService service;
    private static UserAdvertisementsDto userAdvertisementsDto;

    @BeforeClass
    public static void setup() {
        UserDao userDao = mock(UserDao.class);
        UserAdvertisementsDao userAdvertisementsDao = mock(UserAdvertisementsDao.class);

        setupUserAdvertisements();

        service = new UserAdvertisementsService(userDao, userAdvertisementsDao);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void testShowUserAdvertisements() throws UserNotLoggedInException {
        service.getUserAdvertisements();
    }

    private static void setupUserAdvertisements() {
        CarDetails car = new CarDetails();
        car.setMake("Volkswagen");
        car.setModel("Passat B5");
        car.setProductionYear(1996);
        car.setEngineDisplacement(1896);
        car.setHorsePower(110);
        car.setDamaged(false);

        Advertisement advertisement = new Advertisement();
        advertisement.setTitle("VW Passat B5 1.9TDI Okazja");
        advertisement.setDescription("Jak w tytule");
        advertisement.setPrice(3900);
        advertisement.setDetails(car);

        List<Advertisement> advertisements = new ArrayList<>();
        advertisements.add(advertisement);

        userAdvertisementsDto = new UserAdvertisementsDto();
        userAdvertisementsDto.setAdvertisements(advertisements);
    }
}
