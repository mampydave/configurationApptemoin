package demo.spring.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import demo.spring.dto.ReservationDetailsDTO;
import demo.spring.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    @Query(value = "SELECT " +
           "r.id_reservation as idReservation, " +
           "r.id_passager as idPassager, " +
           "v.id_vol as idVol, " +
           "CAST(v.date_heure_depart AS date) as dateDepart, " +
           "CAST(v.date_heure_depart AS time) as heureDepart, " +
           "v.numero_vol as numeroVol, " +
           "ville_depart.nom_ville as villeDepart, " +
           "ville_arrivee.nom_ville as villeArrivee, " +
           "s.numero_siege as numeroSiege, " +
           "r.date_reservation as dateReservation, " +
           "r.statut_reservation as statutReservation, " +
           "r.prixBillet, " +
           "ts.types as typeSiege, " +
           "r.beneficiaire  " +
           "FROM reservation r " +
           "JOIN vol v ON r.id_vol = v.id_vol " +
           "JOIN sieges s ON s.id_siege = r.id_siege " +
           "JOIN villes ville_depart ON v.aeroport_depart = ville_depart.id_ville " +
           "JOIN villes ville_arrivee ON v.aeroport_arrivee = ville_arrivee.id_ville " +
           "JOIN type_siege ts ON s.id_type_siege = ts.id_type_siege " +
           "WHERE r.id_passager = :passenger AND r.statut_reservation = 'Reserve'", 
           nativeQuery = true)
    List<Object[]> findReservationOfPassengerNative(@Param("passenger") Long idPassager);
    
    @Query(value = "SELECT " +
           "r.id_reservation as idReservation, " +
           "r.id_passager as idPassager, " +
           "v.id_vol as idVol, " +
           "CAST(v.date_heure_depart AS date) as dateDepart, " +
           "CAST(v.date_heure_depart AS time) as heureDepart, " +
           "v.numero_vol as numeroVol, " +
           "ville_depart.nom_ville as villeDepart, " +
           "ville_arrivee.nom_ville as villeArrivee, " +
           "s.numero_siege as numeroSiege, " +
           "r.date_reservation as dateReservation, " +
           "r.statut_reservation as statutReservation, " +
           "r.prixBillet, " +
           "ts.types as typeSiege, " +
           "r.beneficiaire  " +
           "FROM reservation r " +
           "JOIN vol v ON r.id_vol = v.id_vol " +
           "JOIN sieges s ON s.id_siege = r.id_siege " +
           "JOIN villes ville_depart ON v.aeroport_depart = ville_depart.id_ville " +
           "JOIN villes ville_arrivee ON v.aeroport_arrivee = ville_arrivee.id_ville " +
           "JOIN type_siege ts ON s.id_type_siege = ts.id_type_siege " +
           "WHERE r.id_reservation = :idReservation", 
           nativeQuery = true)
    List<Object[]> findReservationByIdNative(@Param("idReservation") Long idReservation);

    default List<ReservationDetailsDTO> findReservationOfPassenger(Long idPassager) {
        return findReservationOfPassengerNative(idPassager).stream()
            .map(this::mapToReservationDetailsDTO)
            .collect(Collectors.toList());
            
    }
    

    default Optional<ReservationDetailsDTO> findReservationById(Long idReservation) {
        List<Object[]> results = findReservationByIdNative(idReservation);
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapToReservationDetailsDTO(results.get(0)));
    }
    
    private ReservationDetailsDTO mapToReservationDetailsDTO(Object[] result) {
        return new ReservationDetailsDTO(
            ((Number) result[0]).longValue(),  
            ((Number) result[1]).longValue(),  
            ((Number) result[2]).longValue(),  // idVol
            (Date) result[3],                  // dateDepart
            (Time) result[4],                  // heureDepart
            (String) result[5],                // numeroVol
            (String) result[6],               // villeDepart
            (String) result[7],               // villeArrivee
            (String) result[8],               // numeroSiege
            (Timestamp) result[9],            // dateReservation
            (String) result[10],              // statutReservation
            (BigDecimal) result[11],             // prixBillet
            (String) result[12],              // typeSiege
            (String) result[13]              // beneficiaire

        );
    }

}
