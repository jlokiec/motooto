package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.UserDao;
import pl.motooto.webapp.service.exception.EmailTakenException;
import pl.motooto.webapp.service.exception.UsernameTakenException;
import pl.motooto.webapp.model.User;
import pl.motooto.webapp.model.dto.UserDto;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User registerNewUser(UserDto userDto) throws UsernameTakenException, EmailTakenException {
        User user = null;

        if (usernameTaken(userDto.getUsername())) {
            throw new UsernameTakenException();
        }
        if (emailTaken(userDto.getEmail())) {
            throw new EmailTakenException();
        }

        user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(new BCryptPasswordEncoder(11).encode(userDto.getPassword()));
        user.setEnabled(true);

        userDao.save(user);

        return user;
    }

    private boolean usernameTaken(String username) {
        User user = userDao.findByUsername(username);
        return user != null;
    }

    private boolean emailTaken(String email) {
        User user = userDao.findByEmail(email);
        return user != null;
    }
}
