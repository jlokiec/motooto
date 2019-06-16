package pl.motooto.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.motooto.webapp.dao.AddAdvertisementDao;
import pl.motooto.webapp.dao.UserDao;
import pl.motooto.webapp.model.dto.AddAdvertisementDto;
import pl.motooto.webapp.service.AddAdvertisementService;
import pl.motooto.webapp.service.exception.UserNotLoggedInException;

import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class AddAdvertisementServiceTest {
    private static AddAdvertisementService service;
    private static AddAdvertisementDto addAdvertisementDto;

    @BeforeClass
    public static void setup() {
        UserDao userDao = mock(UserDao.class);
        AddAdvertisementDao addAdvertisementDao = mock(AddAdvertisementDao.class);

        setupAddAdvertisements();

        service = new AddAdvertisementService(userDao, addAdvertisementDao);
    }

    @Test(expected = UserNotLoggedInException.class)
    public void testAddNewAdvertisement() throws UserNotLoggedInException {
        service.addNewAdvertisement(addAdvertisementDto);
    }

    private static void setupAddAdvertisements() {
        addAdvertisementDto = new AddAdvertisementDto();
        addAdvertisementDto.setTitle("VW Passat B5 1.9TDI Okazja");
        addAdvertisementDto.setDescription("Jak w tytule");
        addAdvertisementDto.setPrice(3900);
        addAdvertisementDto.setCarMake("Volkswagen");
        addAdvertisementDto.setCarModel("Passat B5");
        addAdvertisementDto.setProductionYear(1996);
        addAdvertisementDto.setEngineDisplacement(1896);
        addAdvertisementDto.setHorsePower(110);
        addAdvertisementDto.setDamaged(false);
    }
}
