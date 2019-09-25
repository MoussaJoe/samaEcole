package model;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenererPDF {

    public String genererRefId() {
        String chaineRef = ""; 
        String contenu = "";
        Random rd = new Random();

        for (int i = 9000; i < 12000; i++) {
            contenu = "" + rd.nextInt(12000);
            chaineRef = contenu;
        }
        return chaineRef;
    }

    public void documentPDF(ArrayList<EleveDevoir> elD, String annee, String classe) throws DocumentException, BadElementException {

        System.out.println("nomClasse PDF "+classe);
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);        
        try {
            PdfWriter.getInstance(document, new FileOutputStream("D:/Personnel/7Tup/Projet_7Tup/samaEcole/web/Fiche/fiche_devoir.pdf"));
            document.open();

            Paragraph paragraph = new Paragraph("RELEVE DE NOTES", new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD, BaseColor.BLACK));

            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15);
            paragraph.clear();

            Phrase phrase = new Phrase("Classe : " + classe + "   Annee Scolaire : " + annee);
            paragraph = new Paragraph(phrase);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            paragraph.clear();

            document.add(new Paragraph("    "));
            document.add(tableau(elD));
            /////////////////////////////////////////////////////////////////////////////////
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenererPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenererPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static PdfPTable tableau(ArrayList<EleveDevoir> eleve) {
        PdfPTable table = new PdfPTable(5);

        try {

            float[] larg = {20f, 15f, 15f, 15f, 15f};
            table.setWidths(larg);

            PdfPCell cell = new PdfPCell();

            ///// la premiere colonne/////////////////////////////
            cell.setPhrase(new Phrase("Prenom"));
            cell.setBackgroundColor(BaseColor.GRAY);
            cell.setBorderWidth(1);
            cell.setNoWrap(true);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setPadding(2);
            table.addCell(cell);

            cell.setPhrase(new Phrase("Nom"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Devoir 1"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Devoir 2"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Devoir 3"));
            table.addCell(cell);

            /////////////les colonnes suivantes////////////////////////////////////
            for (EleveDevoir e : eleve) {

                cell.setPhrase(new Phrase(e.getPrenom() + " "));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderWidth(1);
                cell.setNoWrap(true);
                cell.setBorderColor(BaseColor.BLACK);
                cell.setPadding(2);
                table.addCell(cell);

                cell.setPhrase(new Phrase(e.getNom() + " "));
                table.addCell(cell);

                cell.setPhrase(new Phrase(" "));
                table.addCell(cell);

                cell.setPhrase(new Phrase(" "));
                table.addCell(cell);

                cell.setPhrase(new Phrase(" "));
                table.addCell(cell);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(GenererPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;
    }

}
