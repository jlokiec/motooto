package pl.motooto.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import pl.motooto.webapp.dao.AdvertisementDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.service.AdvertisementService;
import pl.motooto.webapp.service.exception.AdvertisementNotFoundException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class AdvertisementServiceTest {
    private static final long VALID_ADVERTISEMENT_ID = 1L;
    private static final long INVALID_ADVERTISEMENT_ID = 2L;

    private static AdvertisementService service;

    @BeforeClass
    public static void setup() {
        AdvertisementDao advertisementDao = mock(AdvertisementDao.class);

        when(advertisementDao.findAdvertisementById(VALID_ADVERTISEMENT_ID)).thenReturn(new Advertisement());
        when(advertisementDao.findAdvertisementById(INVALID_ADVERTISEMENT_ID)).thenReturn(null);

        service = new AdvertisementService(advertisementDao);
    }

    @Test
    public void testGetAdvertisementValidId() throws AdvertisementNotFoundException {
        service.getAdvertisement(VALID_ADVERTISEMENT_ID);
    }

    @Test(expected = AdvertisementNotFoundException.class)
    public void testGetAdvertisementInvalidId() throws AdvertisementNotFoundException {
        service.getAdvertisement(INVALID_ADVERTISEMENT_ID);
    }
}
