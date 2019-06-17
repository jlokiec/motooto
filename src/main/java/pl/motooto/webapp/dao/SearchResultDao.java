package pl.motooto.webapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.motooto.webapp.model.Advertisement;

import java.util.List;

public interface SearchResultDao extends JpaRepository<Advertisement, Long> {

    @Query("SELECT u FROM Advertisement u WHERE u.details.make = ?1 and u.details.model = ?2 and u.details.productionYear > ?3 and  u.details.productionYear < ?4 and u.price >= ?5  and u.price <  ?6 and u.details.damaged = ?7 ")

    List<Advertisement> findByMade(String made, String model,int year_beg , int year_end, double price_beg, double price_end, boolean damaged);


}



/*
list = searchResultDao.findByMade(dto.getCarMake(), dto.getCarModel(), dto.getProductionYear_beg(), dto.getProductionYear_end(), dto.getEngineDisplacement_beg(), dto.getEngineDisplacement_end(), dto.getHorsePower_beg(), dto.getHorsePower_end(), dto.getPrice_beg(), dto.getPrice_end(), dto.isDamaged());

    @Query("SELECT u FROM Advertisement u WHERE u.details.make = ?1 and u.details.model = ?2" +
            "and u.details.productionYear >= ?3 and  u.details.productionYear <= ?4" +
            "and u.details.engineDisplacement >= ?5 and u.details.engineDisplacement <= ?6" +
            "and u.details.horsePower >= ?7 and u.details.horsePower <= ?8" +
            "and u.price >= ?9  and u.price <=  ?10" +
            "and u.details.damaged = ?11")
    List<Advertisement> findByMade(String made, String model, int prod_bed, int prod_end, int eng_beg, int eng_end, int hp_beg, int hp_end, double price_beg,
                                   double price_end, boolean isDamaged);
                                   List<Advertisement> findByMade(String made, String model,int prod_bed, int prod_end ,int eng_beg, int eng_end,
                                   int hp_beg, int hp_end, double price_beg ,double price_end, boolean isDamaged);
 */