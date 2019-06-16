package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.UserAdvertisementsDao;
import pl.motooto.webapp.dao.UserDao;
import pl.motooto.webapp.model.Advertisement;
import pl.motooto.webapp.model.User;
import pl.motooto.webapp.service.exception.UserNotLoggedInException;

import java.util.List;

@Service
public class UserAdvertisementsService {
    private UserDao userDao;
    private UserAdvertisementsDao userAdvertisementsDao;

    @Autowired
    public UserAdvertisementsService(UserDao userDao, UserAdvertisementsDao userAdvertisementsDao) {
        this.userDao = userDao;
        this.userAdvertisementsDao = userAdvertisementsDao;
    }

    public List<Advertisement> getUserAdvertisements() throws UserNotLoggedInException {
        User user = null;

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            user = userDao.findByUsername(authentication.getName());
        } catch (Exception e) {
            throw new UserNotLoggedInException();
        }

        List<Advertisement> advertisements = userAdvertisementsDao.findAll();

        long userId = user.getId();
        advertisements.removeIf(p -> p.getPublisher().getId() != userId);

        return advertisements;
    }
}
