/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Restaurant;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.Paper;
import javafx.print.PrintQuality;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;
import javax.swing.text.AttributeSet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;

/**
 *
 * @author root
 */
public class PrintPDF {
    public static void printPDF(String path) throws IOException, PrintException{
    PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
    DocPrintJob printJob = printService.createPrintJob();
   

    PDDocument pdDocument = PDDocument.load(new File(path));
    
        try {
            printWithPaper(pdDocument);
            /*
            PDFPageable pdfPageable = new PDFPageable(pdDocument);
            DocAttributeSet das = new HashDocAttributeSet();
            das.add(MediaSizeName.ISO_A4);
            
            PrintRequestAttributeSet patts = new HashPrintRequestAttributeSet();
            patts.add(Sides.ONE_SIDED);
            patts.add(MediaSizeName.ISO_A4);
            
            //AttributeSet attributes = (AttributeSet) printService.getAttributes();
            
            SimpleDoc doc = new SimpleDoc(pdfPageable, DocFlavor.SERVICE_FORMATTED.PAGEABLE,das );
            
            printJob.print(doc,patts );
        */  } catch (PrinterException ex) {
            Logger.getLogger(PrintPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
}
     private static void printWithPaper(PDDocument document)
	            throws IOException, PrinterException
	    {
	        PrinterJob job = PrinterJob.getPrinterJob();
	        job.setPageable(new PDFPageable(document));
	
	        // define custom paper
	        java.awt.print.Paper paper = new java.awt.print.Paper();
	        paper.setSize(595d, 830d);
                paper.setImageableArea(0,0, paper.getWidth(), paper.getHeight()); // no margins
	
	        // custom page format
	        PageFormat pageFormat = new PageFormat();
	        pageFormat.setPaper(paper);
	        
	        // override the page format
	        Book book = new Book();
	        // append all pages
	        book.append(new PDFPrintable(document), pageFormat, document.getNumberOfPages());
	        job.setPageable(book);
	        
	        job.print();
	    }
    
    
}
