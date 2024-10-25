package io.wiem.services;


import io.wiem.exceptions.UserAlreadyExistException;
import io.wiem.exceptions.UserNotFoundException;
import io.wiem.model.User;

import java.util.List;

public interface UserService {
    List<User> getall() throws UserNotFoundException;

    User addUser(User user) throws UserAlreadyExistException;

    User getUserByUserName(String username)  throws UserNotFoundException;
}
