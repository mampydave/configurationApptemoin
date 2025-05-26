package demo.spring.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.spring.dto.ReservationDetailsDTO;
import demo.spring.service.CsvGenerationService;
import demo.spring.service.PdfGenerationService;
import demo.spring.service.ReservationService;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final PdfGenerationService pdfGenerationService;
    private final CsvGenerationService csvGenerationService;

    public ReservationController(ReservationService reservationService, 
                               PdfGenerationService pdfGenerationService,CsvGenerationService csvGenerationService) {
        this.reservationService = reservationService;
        this.pdfGenerationService = pdfGenerationService;
        this.csvGenerationService = csvGenerationService;
    }

    @GetMapping("/")
    public String indexUser() {
        return "reservation-forme";
    }

    @GetMapping("/reservations")
    public String getReservations(@RequestParam Long passengerId, Model model) {
        List<ReservationDetailsDTO> reservations = reservationService.getReservationsByPassenger(passengerId);
        model.addAttribute("reservations", reservations);
        return "reservations"; 
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/reservations/{id}/download")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadReservationPdf(@PathVariable Long id) {
        try {
            ReservationDetailsDTO reservation = reservationService.getReservationDetailsByIdReservation(id);
            ByteArrayInputStream bis = pdfGenerationService.generateReservationPdf(reservation);
            // ByteArrayInputStream bisc = csvGenerationService.generateReservationCsv(reservation);
            // HttpHeaders headers = new HttpHeaders();
            // headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservation-" + id + ".pdf");
            // headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            // headers.add(HttpHeaders.PRAGMA, "no-cache");
            // headers.add(HttpHeaders.EXPIRES, "0");
            // .headers(headers)

            // .contentType(MediaType.parseMediaType("text/csv"))
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF", e);
        }
    }
}