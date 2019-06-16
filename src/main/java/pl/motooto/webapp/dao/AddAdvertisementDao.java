package pl.motooto.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motooto.webapp.model.Advertisement;

public interface AddAdvertisementDao extends JpaRepository<Advertisement, Long> {
    Advertisement save(Advertisement advertisement);
}
