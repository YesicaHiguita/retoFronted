package com.example.projectreto3.services;

import com.example.projectreto3.entities.Message;
import com.example.projectreto3.entities.Reservation;
import com.example.projectreto3.repository.CountClient;
import com.example.projectreto3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId) {
        return reservationRepository.getById(reservationId);
    }

    public Reservation save(Reservation c){
        if(c.getIdReservation()==null){
            return reservationRepository.save(c);
        }else{
            Optional<Reservation> pa=reservationRepository.getById(c.getIdReservation());
            if(!pa.isPresent()){
                return reservationRepository.save(c);
            }
        }
        return c;

    }

    public Reservation update(Reservation a){
        if(a.getIdReservation()!=null){
            Optional<Reservation> pa=reservationRepository.getById(a.getIdReservation());
            if(pa.isPresent()){
                Reservation result=pa.get();

                if(a.getStartDate()!=null){
                    result.setStartDate(a.getStartDate());
                }
                if(a.getDevolutionDate()!=null){
                    result.setDevolutionDate(a.getDevolutionDate());
                }
                if(a.getStatus()!=null){
                    result.setStatus(a.getStatus());
                }

                return reservationRepository.save(result);
            }
        }
        return a;
    }

    public boolean deleteReservation (int id){
        Boolean d = getReservation(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return d;
    }

    ////////////////////////Reto 5 Informes///////////////////////////////

    public Status getReservationStatusReport(){
        List <Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List <Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");
        return new Status (completed.size(), cancelled.size());
    }

    public List<Reservation> informePeriodoTiempoReservas(String datoA, String datoB){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date a = new Date();
        Date b = new Date();

        try {
            a = parser.parse(datoA);
            b = parser.parse(datoB);
        }catch(ParseException e){
            e.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.informePeriodoTiempoReservas(a, b);
        }else{
            return new ArrayList<>();
        }
    }

    public List<CountClient> getTopClients(){
        return reservationRepository.getTopClient();
    }
}
