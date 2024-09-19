package com.reservation_billet_transport.services;

import com.reservation_billet_transport.dao.UserDAO;
import com.reservation_billet_transport.models.User;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void ajouterUser(User user) {
        userDAO.AjouterUser(user);
    }

    public boolean validerUser(String email) {
        return userDAO.validerUser(email);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
}
