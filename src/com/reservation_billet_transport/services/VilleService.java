package com.reservation_billet_transport.services;

import com.reservation_billet_transport.dao.VilleDAO;
import com.reservation_billet_transport.models.Ville;
import java.util.List;

public class VilleService {
    private VilleDAO villeDAO = new VilleDAO();

    public void ajouterVille(Ville ville) {
        villeDAO.ajouterVille(ville);
    }

//    public List<Ville> getAllVilles() {
//        return villeDAO.getAllVilles();
//    }
}
