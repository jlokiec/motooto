package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.SearchDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.service.exception.SearchedFailedException;

import java.util.List;

@Service
public class SearchService {
    private SearchDao searchDao;

    @Autowired
    public SearchService(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    public List<Advertisement> getAll() throws SearchedFailedException {
        List<Advertisement> list;
        try {
            list = searchDao.findAll();
        } catch (Exception e) {
            throw new SearchedFailedException();
        }
        return list;
    }
}
