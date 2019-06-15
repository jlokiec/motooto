package pl.motooto.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motooto.webapp.model.Advertisement;

public interface AdvertisementDao extends JpaRepository<Advertisement, Long> {
    Advertisement save(Advertisement advertisement);

    Advertisement findAdvertisementById(long id);
}
