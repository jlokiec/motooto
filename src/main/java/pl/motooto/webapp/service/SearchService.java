package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.SearchDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.dto.SearchDto;
import pl.motooto.webapp.service.exception.SearchedFailedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private SearchDao searchDao;

    @Autowired
    public SearchService(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    public List<Advertisement> getAll() throws SearchedFailedException
    {
        List<Advertisement> list = new ArrayList<Advertisement>();
        try {
            list = searchDao.findAll();

        }
        catch(Exception e) {
            throw new SearchedFailedException();
        }
        return list;

    }
}
