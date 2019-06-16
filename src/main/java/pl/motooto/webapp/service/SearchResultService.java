package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.SearchResultDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.dto.SearchDto;
import pl.motooto.webapp.service.exception.SearchedFailedException;

import java.util.ArrayList;
import java.util.List;
@Service
public class SearchResultService {
    private SearchResultDao searchResultDao;

    @Autowired
    public SearchResultService(SearchResultDao searchResultDao) {
        this.searchResultDao = searchResultDao;
    }

    public List<Advertisement> filter(SearchDto dto) throws SearchedFailedException
    {
        List<Advertisement> list = new ArrayList<Advertisement>();
        try {
             list = searchResultDao.findByMade(dto.getCarMake(), dto.getCarModel(), dto.getProductionYear_beg(), dto.getProductionYear_end(), dto.getPrice_beg(), dto.getProductionYear_end(), dto.isDamaged());
        }
        catch (Exception e){
            throw new SearchedFailedException();
        }
        return list;
    }
}
