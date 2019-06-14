package pl.motooto.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.motooto.webapp.dao.UserDao;
import pl.motooto.webapp.model.User;
import pl.motooto.webapp.model.dto.UserDto;
import pl.motooto.webapp.service.exception.EmailTakenException;
import pl.motooto.webapp.service.exception.PasswordsDontMatchException;
import pl.motooto.webapp.service.exception.UsernameTakenException;

@Service
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User registerNewUser(UserDto userDto) throws UsernameTakenException, EmailTakenException, PasswordsDontMatchException {
        User user = null;

        if (checkUsernameTaken(userDto)) {
            throw new UsernameTakenException();
        }
        if (checkEmailTaken(userDto)) {
            throw new EmailTakenException();
        }
        if (!checkPasswordMatch(userDto)) {
            throw new PasswordsDontMatchException();
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

    private boolean checkUsernameTaken(UserDto userDto) {
        User user = userDao.findByUsername(userDto.getUsername());
        return user != null;
    }

    private boolean checkEmailTaken(UserDto userDto) {
        User user = userDao.findByEmail(userDto.getEmail());
        return user != null;
    }

    private boolean checkPasswordMatch(UserDto userDto) {
        String password = userDto.getPassword();
        String passwordRepeat = userDto.getPasswordRepeat();

        return password.equals(passwordRepeat);
    }
}
