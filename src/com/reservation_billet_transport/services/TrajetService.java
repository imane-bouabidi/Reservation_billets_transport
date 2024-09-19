package com.reservation_billet_transport.services;

import com.reservation_billet_transport.dao.TrajetDAO;
import com.reservation_billet_transport.models.Trajet;
import java.util.List;

public class TrajetService {
    private TrajetDAO trajetDAO = new TrajetDAO();

    public void ajouterTrajet(Trajet trajet) {
        trajetDAO.ajouterTrajet(trajet);
    }

//    public List<Trajet> getAllTrajets() {
//        return trajetDAO.getAllTrajets();
//    }

    public void updateTrajet(Trajet trajet) {
        trajetDAO.updateTrajet(trajet);
    }
}
