package pl.motooto.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.motooto.webapp.model.Advertisement;

import java.util.List;

public interface UserAdvertisementsDao extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findAll();
}
