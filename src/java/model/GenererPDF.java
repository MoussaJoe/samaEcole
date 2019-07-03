/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moussa Joseph Sarr
 */
public class GenererPDF {

    public void documentPDF(Bulletin bulletin) throws DocumentException, BadElementException {

        Bulletin bulletin1 = bulletin;
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\ibrah\\Downloads\\Compressed\\memoire-20190223T001329Z-001\\memoire\\web\\bulletin\\" + bulletin.getNom() + "" + bulletin.getPrenom() + ".pdf"));
            document.open();

            Image img = Image.getInstance("C:\\Users\\ibrah\\Downloads\\Compressed\\memoire-20190223T001329Z-001\\memoire\\web\\images\\logo_ecole.jpg");
            document.add(img);
            Paragraph paragraph = new Paragraph("BULLETIN DE NOTES", new Font(Font.FontFamily.TIMES_ROMAN, 25, Font.BOLD, BaseColor.BLACK));

            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15);
            paragraph.clear();

            Phrase phrase = new Phrase("Prénoms : " + bulletin.getPrenom() + "                                                                    Noms : " + bulletin.getNom());
            paragraph = new Paragraph(phrase);
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            paragraph.clear();

            paragraph = new Paragraph("Né(e) le " + bulletin.getDateNaissance() + " à " + bulletin.getLieuNaissance() + "                                                                 Classe : " + bulletin.getNomClasse());
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            paragraph.clear();

            paragraph = new Paragraph("Matricule : " + bulletin.getLogin() + "                                                                              Nbre d'éléves : " + bulletin.getNbreEleve());
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            paragraph.clear();
            
            if(bulletin.getSemestre().equals("2eme_semestre"))
            paragraph = new Paragraph("Semestre : 2                                                                        Annnée-Scolaire : " + bulletin.getAnnee());
            else
                          paragraph = new Paragraph("Semestre : 1                                                                         Annnée-Scolaire : " + bulletin.getAnnee());
  
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(paragraph);
            paragraph.clear();
            
            
            document.add(new Paragraph("    "));
            document.add(tableau(bulletin1));
            document.add(new Paragraph("    "));
            if (bulletin.getSemestre().equals("2eme_semestre")) {
                document.add(tableau1(bulletin));
            }
            document.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenererPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenererPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static PdfPTable tableau(Bulletin bulletin) {
        PdfPTable table = new PdfPTable(8);

        try {

            float[] larg = {20f, 15f, 15f, 15f, 10f, 15f, 10f, 25f};
            table.setWidths(larg);

            PdfPCell cell = new PdfPCell();

            ///// la premiere colonne/////////////////////////////
            cell.setPhrase(new Phrase("Disciplines"));
            cell.setBackgroundColor(BaseColor.GRAY);
            cell.setBorderWidth(1);
            cell.setNoWrap(true);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setPadding(2);
            table.addCell(cell);

            cell.setPhrase(new Phrase("Devoir"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Comp"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Moy/20"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("coef"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Moy x"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Rang"));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Appréciations"));
            table.addCell(cell);

            /////////////les colonnes suivantes : EVALUATION/////////////////////////////////////
            for (Evaluation e : bulletin.getEvaluation()) {

                cell.setPhrase(new Phrase(e.getNomMatiere()));
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderWidth(1);
                cell.setNoWrap(true);
                cell.setBorderColor(BaseColor.BLACK);
                cell.setPadding(2);
                table.addCell(cell);

                cell.setPhrase(new Phrase(e.getDevoir() + ""));
                table.addCell(cell);

                cell.setPhrase(new Phrase(e.getComposition() + ""));
                table.addCell(cell);

                cell.setPhrase(new Phrase(e.getMoyenneCC() + ""));
                table.addCell(cell);

                cell.setPhrase(new Phrase(e.getCoef() + ""));
                table.addCell(cell);

                cell.setPhrase(new Phrase(e.getMoyX() + ""));
                table.addCell(cell);

                cell.setPhrase(new Phrase(e.getRang() + ""));
                table.addCell(cell);

                cell.setPhrase(new Phrase(e.getAppreciations() + ""));
                table.addCell(cell);
            }
            /////////////////////////////////////////////////////////////////////////////////

            cell.setPhrase(new Phrase("TOTAL"));
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setBorderWidth(1);
            cell.setNoWrap(true);
            cell.setBorderColor(BaseColor.BLACK);
            cell.setPadding(2);
            table.addCell(cell);

            cell.setPhrase(new Phrase(""));
            cell.setColspan(3);
            table.addCell(cell);

            cell.setPhrase(new Phrase(bulletin.getTotalCoef() + ""));
            cell.setColspan(1);
            table.addCell(cell);

            cell.setPhrase(new Phrase(bulletin.getTotalMoyenne() + ""));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Absences : " + bulletin.getAbsences()));
            cell.setColspan(2);
            table.addCell(cell);

            //////////////////////////////////////////////////////////////////////////////
            cell.setPhrase(new Phrase("Moyenne"));
            cell.setColspan(1);
            table.addCell(cell);

            cell.setPhrase(new Phrase(bulletin.getMoyenneGenerale() + " /20"));
            cell.setColspan(2);
            table.addCell(cell);

            cell.setPhrase(new Phrase("Rang : " + bulletin.getRang()));
            cell.setColspan(2);
            table.addCell(cell);

            cell.setPhrase(new Phrase("Retards"));
            cell.setColspan(2);
            table.addCell(cell);

            cell.setPhrase(new Phrase(bulletin.getRetards() + ""));
            table.addCell(cell);

            cell.setPhrase(new Phrase("Blame"));
            cell.setColspan(1);
            table.addCell(cell);

            cell.setPhrase(new Phrase("Avertissement"));
            cell.setColspan(2);
            table.addCell(cell);

            cell.setPhrase(new Phrase("Tableau d'honneur"));
            cell.setColspan(3);
            if (bulletin.getMoyenneGenerale() >= 12) {
                cell.setBackgroundColor(BaseColor.GREEN);
            }
            table.addCell(cell);

            cell.setPhrase(new Phrase("Encouragement"));
            cell.setColspan(2);
            if (bulletin.getMoyenneGenerale() <= 12) {
                cell.setBackgroundColor(BaseColor.GREEN);
            }
            table.addCell(cell);

        } catch (DocumentException ex) {
            Logger.getLogger(GenererPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;
    }

    public static PdfPTable tableau1(Bulletin bulletin){

        PdfPTable table1 = new PdfPTable(4);

        try {
            float[] larg1 = {52f, 10f, 8f, 40f};
            table1.setWidths(larg1);

            PdfPCell cell1 = new PdfPCell();

            cell1.setPhrase(new Phrase(" Décision du conseil"));
            cell1.setColspan(2);
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase(" ")));
            cell1.setColspan(2);
            cell1.setBorder(0);
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase("Admis(e) en classe supérieure")));
            cell1.setColspan(1);
            cell1.setBorderWidthLeft(1);
            cell1.setBorderWidthBottom(1);
            cell1.setBorderWidthRight(1);
            cell1.setBorderWidthTop(1);
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase(" ")));
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase(" ")));
            cell1.setBorderWidthTop(0);
            cell1.setBorderWidthBottom(0);
            table1.addCell(cell1);
            
                cell1.setPhrase((new Phrase("Moy.1er sem. : "+bulletin.getMoySemestre1()+" Moy.2eme sem. : "+bulletin.getMoySemestre2()+" Moy.Gén : "+(bulletin.getMoySemestre1()+bulletin.getMoySemestre2())/2)));
                cell1.setBorderWidthTop(1);
                cell1.setBorderWidthBottom(1);
                cell1.setRowspan(4);
                cell1.setNoWrap(false);
                table1.addCell(cell1);

            cell1.setPhrase((new Phrase("autorisé(e) à redoubler")));
            cell1.setRowspan(1);
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase("  ")));
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase(" ")));
            cell1.setBorderWidthTop(0);
            cell1.setBorderWidthBottom(0);
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase("Exclusion")));
            cell1.setBorderWidthTop(1);
            cell1.setBorderWidthBottom(1);
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase(" ")));
            table1.addCell(cell1);

            cell1.setPhrase((new Phrase(" ")));
            cell1.setBorderWidthTop(0);
            cell1.setBorderWidthBottom(0);
            table1.addCell(cell1);
        } catch (DocumentException ex) {
            Logger.getLogger(GenererPDF.class.getName()).log(Level.SEVERE, null, ex);
        }

        return table1;
    }

}
