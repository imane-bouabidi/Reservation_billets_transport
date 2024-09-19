package com.reservation_billet_transport.services;

import com.reservation_billet_transport.dao.ReservationDAO;
import com.reservation_billet_transport.models.Reservation;
import java.util.List;
import java.util.UUID;

public class ReservationService {
    private ReservationDAO reservationDAO = new ReservationDAO();

    public void reserver(UUID clientId,UUID billetId) {
        reservationDAO.Reserver(clientId, billetId);
    }

    public List<Reservation> getReservationsByUserId(UUID userId) {
        return reservationDAO.getReservationsByUserId(userId);
    }
}
