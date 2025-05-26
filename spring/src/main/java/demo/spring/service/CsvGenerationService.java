package demo.spring.service;

import demo.spring.dto.ReservationDetailsDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvGenerationService {

    public ByteArrayInputStream generateReservationCsv(ReservationDetailsDTO reservation) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try (PrintWriter writer = new PrintWriter(out)) {
            String header = String.join(",",
                "Reservation Number",
                "Flight Number",
                "Departure Date",
                "Departure Time",
                "Departure City",
                "Arrival City",
                "Seat Number",
                "Seat Type",
                "Passenger Type",
                "Ticket Price"
            );
            writer.println(header);
    
            String data = String.join(",",
                reservation.getIdReservation().toString(),
                escapeCsvField(reservation.getNumeroVol()),
                formatDate(reservation.getDateDepart()),
                reservation.getHeureDepart().toString(),
                escapeCsvField(reservation.getVilleDepart()),
                escapeCsvField(reservation.getVilleArrivee()),
                escapeCsvField(reservation.getNumeroSiege()),
                escapeCsvField(reservation.getTypeSiege()),
                escapeCsvField(reservation.getBeneficiaire()),
                reservation.getPrixBillet().toString()
            );
            writer.println(data);
            
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
        
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    private String escapeCsvField(String field) {
        if (field == null) {
            return "";
        }

        if (field.contains(",") || field.contains("\n") || field.contains("\"")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }
    
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}