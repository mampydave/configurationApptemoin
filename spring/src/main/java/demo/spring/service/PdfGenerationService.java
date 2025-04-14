package demo.spring.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import demo.spring.dto.ReservationDetailsDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class PdfGenerationService {

    public ByteArrayInputStream generateReservationPdf(ReservationDetailsDTO reservation) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // Titre du document
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Détails de la réservation", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);

            // Tableau des informations
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            // addTableHeader(table);
            // addReservationData(table, reservation);

            // document.add(table);
            addReservationData(document, writer,reservation);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    // private void addTableHeader(PdfPTable table) {
    //     PdfPCell cell;
        
    //     cell = new PdfPCell(new Phrase("Champ"));
    //     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    //     table.addCell(cell);

    //     cell = new PdfPCell(new Phrase("Valeur"));
    //     cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    //     table.addCell(cell);
    // }

    // private void addReservationData(PdfPTable table, ReservationDetailsDTO reservation) {
    //     addRow(table, "Numéro de réservation", reservation.getIdReservation().toString());
    //     addRow(table, "Numéro de vol", reservation.getNumeroVol());
    //     addRow(table, "Date de départ", reservation.getDateDepart().toString());
    //     addRow(table, "Heure de départ", reservation.getHeureDepart().toString());
    //     addRow(table, "Ville de départ", reservation.getVilleDepart());
    //     addRow(table, "Ville d'arrivée", reservation.getVilleArrivee());
    //     addRow(table, "Siège", reservation.getNumeroSiege());
    //     addRow(table, "Prix", reservation.getPrixBillet().toString());
    //     addRow(table, "TypeSiege", reservation.getTypeSiege());
    //     addRow(table, "Beneficiaire", reservation.getBeneficiaire());
    // }


    // private void addRow(PdfPTable table, String label, String value) {
    //     table.addCell(label);
    //     table.addCell(value != null ? value : "");
    // }
    private void addReservationData(Document document, PdfWriter writer, ReservationDetailsDTO reservation) throws DocumentException, IOException {
        // Police personnalisée
        Font fontHeader = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.WHITE);
        Font fontTitle = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.DARK_GRAY);
        Font fontValue = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
        
        // En-tête avec logo et titre
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        
        // Partie gauche - Logo compagnie aérienne
        Image logo = Image.getInstance(getClass().getResource("/static/vol.jfif"));
        logo.scaleToFit(80, 80);
        PdfPCell logoCell = new PdfPCell(logo);
        logoCell.setBorder(Rectangle.NO_BORDER);
        logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        headerTable.addCell(logoCell);
        
        // Partie droite - Titre
        PdfPCell titleCell = new PdfPCell(new Phrase("BOARDING PASS\nPASSAGE DE BORD", fontHeader));
        titleCell.setBackgroundColor(new BaseColor(0, 56, 107)); 
        titleCell.setBorder(Rectangle.NO_BORDER);
        titleCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        titleCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        titleCell.setPadding(10);
        headerTable.addCell(titleCell);
        
        document.add(headerTable);
        document.add(Chunk.NEWLINE);
        
        // Section principale en 2 colonnes
        PdfPTable mainTable = new PdfPTable(2);
        mainTable.setWidthPercentage(100);
        mainTable.setWidths(new float[]{1, 1});
        
        // Colonne gauche - Informations vol
        PdfPCell leftCell = new PdfPCell();
        leftCell.setBorder(Rectangle.NO_BORDER);
        leftCell.setPadding(5);
        
        addSectionTitle(leftCell, "FLIGHT INFORMATION", fontTitle);
        addKeyValue(leftCell, "Flight:", reservation.getNumeroVol(), fontValue);
        addKeyValue(leftCell, "Date:", formatDate(reservation.getDateDepart()), fontValue);
        addKeyValue(leftCell, "Time:", reservation.getHeureDepart().toString(), fontValue);
        addKeyValue(leftCell, "Class:", reservation.getTypeSiege(), fontValue);
        addKeyValue(leftCell, "Seat:", reservation.getNumeroSiege(), fontValue);
        
        // Colonne droite - Informations passager
        PdfPCell rightCell = new PdfPCell();
        rightCell.setBorder(Rectangle.NO_BORDER);
        rightCell.setPadding(5);
        
        addSectionTitle(rightCell, "PASSENGER INFORMATION", fontTitle);
        addKeyValue(rightCell, "Type:", reservation.getBeneficiaire(), fontValue);
        addKeyValue(rightCell, "From:", reservation.getVilleDepart(), fontValue);
        addKeyValue(rightCell, "To:", reservation.getVilleArrivee(), fontValue);
        // addKeyValue(rightCell, "Reservation:", reservation.getIdReservation().toString(), fontValue);
        addKeyValue(rightCell, "Fare:", reservation.getPrixBillet() + "", fontValue);
        
        mainTable.addCell(leftCell);
        mainTable.addCell(rightCell);
        document.add(mainTable);
        
        // Code-barres
        document.add(Chunk.NEWLINE);
        Barcode128 barcode = new Barcode128();
        barcode.setCode(reservation.getIdReservation() + "-" + reservation.getNumeroVol());
        barcode.setBarHeight(40);
        
        PdfContentByte cb = writer.getDirectContent();  // récupérer le contexte du PDF
        Image barcodeImage = barcode.createImageWithBarcode(cb, null, null);
        document.add(barcodeImage);
        
        // Footer
        Paragraph footer = new Paragraph("Thank you for flying with us",
                new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY));
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
    }


    private void addSectionTitle(PdfPCell cell, String title, Font font) {
        Paragraph p = new Paragraph(title, font);
        p.setSpacingAfter(5);
        cell.addElement(p);
    }

    private void addKeyValue(PdfPCell cell, String key, String value, Font font) {
        Paragraph p = new Paragraph();
        p.add(new Chunk(key + " ", new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
        p.add(new Chunk(value, font));
        p.setSpacingAfter(3);
        cell.addElement(p);
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return sdf.format(date);
    }
}