package com.example.projectreto3.repository;

import com.example.projectreto3.entities.Client;
import com.example.projectreto3.entities.Reservation;
import com.example.projectreto3.repository.crud.ReservationCRUDrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCRUDrepository reservationCRUDrepository;

    public List<Reservation> getAll() {
        return (List<Reservation>) reservationCRUDrepository.findAll();
    }

    public Optional<Reservation> getById(int id) {
        return reservationCRUDrepository.findById(id);
    }


    public Reservation save(Reservation reservation) {return reservationCRUDrepository.save(reservation);}

    public void delete(Reservation reservation){
        reservationCRUDrepository.delete(reservation);
    }

    //////////////////////Reto 5 Informe ////////////////////////////////

    public List<Reservation> getReservationByStatus (String status){
        return reservationCRUDrepository.findAllByStatus(status);
    }

    public List<Reservation> informePeriodoTiempoReservas (Date a, Date b){
        return reservationCRUDrepository.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<CountClient> getTopClient(){
        List<CountClient> res = new ArrayList<>();
        List<Object[]> report = reservationCRUDrepository.countTotalReservationByClient();
        for(int i=0;i < report.size();i++){
            res.add(new CountClient((Long)report.get(i)[1], (Client) report.get(i)[0]));
        }
        return res;
    }
}
