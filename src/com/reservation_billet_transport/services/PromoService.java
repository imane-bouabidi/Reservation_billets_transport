package com.reservation_billet_transport.services;

import com.reservation_billet_transport.dao.PromosDAO;
import com.reservation_billet_transport.models.Promos;

import java.util.UUID;

public class PromoService {
    private PromosDAO promosDAO;

    public PromoService() {
        this.promosDAO = new PromosDAO();
    }

    public void addPromo(Promos promo) {
        promosDAO.addPromo(promo);
    }

    public void updatePromo(Promos promo) {
        promosDAO.updatePromo(promo);
    }

    public void deletePromo(UUID id) {
        promosDAO.deletePromo(id);
    }

    public Promos getPromoById(UUID id) {
        return promosDAO.getPromoById(id);
    }
}
