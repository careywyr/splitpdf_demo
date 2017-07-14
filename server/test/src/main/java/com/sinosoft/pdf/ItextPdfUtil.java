package com.sinosoft.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Created by Carey on 2017/7/10.
 */
public class ItextPdfUtil
{
    public static void main(String[] args) {
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss-SSS");
//            System.out.println(sdf.format(new Date()));
//            FileInputStream fis = new FileInputStream("d:\\test1.pdf");
//            int pages = getTotalPages(fis);
//            for (int i = 0; i < pages; i++) {
//                String out = "d:\\output"+(i+1)+".pdf";
//                splitPDF(new FileInputStream("d:\\test1.pdf"), new FileOutputStream(out), i + 1, i + 1);
//            }
//
//            System.out.println(sdf.format(new Date()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void splitPDF(InputStream inputStream,
                                OutputStream outputStream, int fromPage, int toPage) {

        Document document = new Document();
        try {
            PdfReader inputPDF = new PdfReader(inputStream);
            int totalPages = inputPDF.getNumberOfPages();

            // make fromPage equals to toPage if it is greater
            if (fromPage > toPage) {
                fromPage = toPage;
            }
            if (toPage > totalPages) {
                toPage = totalPages;
            }

            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF data
            PdfImportedPage page;

            while (fromPage <= toPage) {
                document.newPage();
                page = writer.getImportedPage(inputPDF, fromPage);
                cb.addTemplate(page, 0, 0);
                fromPage++;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static int getTotalPages(InputStream inputStream) {
        int totalPages = 0;
        try {
            PdfReader inputPDF = new PdfReader(inputStream);
            totalPages = inputPDF.getNumberOfPages();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPages;
    }
}
