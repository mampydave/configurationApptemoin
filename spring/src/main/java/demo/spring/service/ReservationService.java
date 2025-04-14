package demo.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.spring.dto.ReservationDetailsDTO;
import demo.spring.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService {
    
    private ReservationRepository reservationRepository;
    
    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    } 
    public List<ReservationDetailsDTO> getReservationsByPassenger(Long passengerId) {
        return reservationRepository.findReservationOfPassenger(passengerId);
    }

    public ReservationDetailsDTO getReservationDetailsByIdReservation(Long idReservation) {
        return reservationRepository.findReservationById(idReservation)
                .orElseThrow(() -> new EntityNotFoundException("Réservation non trouvée avec l'ID: " + idReservation));
    }
    
}
