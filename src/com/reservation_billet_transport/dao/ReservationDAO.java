package com.reservation_billet_transport.dao;

import java.sql.Connection;

import com.reservation_billet_transport.database.DbConnection;

public class ReservationDAO {
	
	private Connection conn;
	
	public ReservationDAO(){
		this.conn = DbConnection.getInstance().getConnection();
	}
	
	public void Reserver() {
		
	}

}
