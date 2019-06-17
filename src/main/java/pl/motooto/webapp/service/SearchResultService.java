package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.SearchResultDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.dto.SearchDto;
import pl.motooto.webapp.service.exception.SearchedFailedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchResultService {
    private SearchResultDao searchResultDao;

    @Autowired
    public SearchResultService(SearchResultDao searchResultDao) {
        this.searchResultDao = searchResultDao;
    }

    public List<Advertisement> filter(SearchDto dto) {
        List<Advertisement> allAdvertisements = searchResultDao.findAll();
        List<Advertisement> filtered = new ArrayList<>();
        if (!dto.getCarMake().equals("") && dto.getCarMake() != null) {
            String make = dto.getCarMake();
            filtered = allAdvertisements.stream().filter(advertisement -> advertisement.getDetails().getMake().equals(make)).collect(Collectors.toList());

        }
        if (dto.getCarModel() != null && !dto.getCarModel().equals("")) {
            String model = dto.getCarModel();
            if (filtered.size() > 0) {
                filtered = filtered.stream().filter(advertisement -> advertisement.getDetails().getModel().equals(model)).collect(Collectors.toList());
            } else {
                filtered = allAdvertisements.stream().filter(advertisement -> advertisement.getDetails().getModel().equals(model)).collect(Collectors.toList());
            }
        }
        if(dto.getProductionYear_beg() != 0 && dto.getProductionYear_end() != 0){
            int year_beg = dto.getProductionYear_beg();
            int year_end = dto.getProductionYear_end();
            if (filtered.size() > 0) {
                filtered = filtered.stream().filter(advertisement -> advertisement.getDetails().getProductionYear() >= year_beg && advertisement.getDetails().getProductionYear() <= year_end).collect(Collectors.toList());
            } else {
                filtered = allAdvertisements.stream().filter(advertisement -> advertisement.getDetails().getProductionYear() >= year_beg && advertisement.getDetails().getProductionYear() <= year_end).collect(Collectors.toList());
            }
        }
        if(dto.getPrice_beg() != 0 && dto.getPrice_end() != 0){
            double price_beg = dto.getPrice_beg();
            double price_end = dto.getPrice_end();
            if (filtered.size() > 0) {
                filtered = filtered.stream().filter(advertisement -> advertisement.getPrice() >= price_beg && advertisement.getPrice() <= price_end).collect(Collectors.toList());
            } else {
                filtered = allAdvertisements.stream().filter(advertisement -> advertisement.getPrice() >= price_beg && advertisement.getPrice() <= price_end).collect(Collectors.toList());
            }
        }

        return filtered;
    }
}
