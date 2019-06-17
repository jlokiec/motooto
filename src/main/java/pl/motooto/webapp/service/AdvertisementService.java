package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.AdvertisementDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.service.exception.AdvertisementNotFoundException;

@Service
public class AdvertisementService {
    private AdvertisementDao advertisementDao;

    @Autowired
    public AdvertisementService(AdvertisementDao advertisementDao) {
        this.advertisementDao = advertisementDao;
    }

    public Advertisement getAdvertisement(long id) throws AdvertisementNotFoundException {
        Advertisement advertisement = advertisementDao.findAdvertisementById(id);

        if (advertisement == null) {
            throw new AdvertisementNotFoundException();
        }

        return advertisement;
    }
}
