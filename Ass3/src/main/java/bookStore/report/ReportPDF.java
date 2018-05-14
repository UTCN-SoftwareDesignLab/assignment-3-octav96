package bookStore.report;

import bookStore.entity.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class ReportPDF implements Report{
    @Override
    public void generateReport(List<Book> books) throws IOException {
        PDDocument doc = new PDDocument();

        //Creating a PDF Document
        PDPage page = new PDPage();
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont( PDType1Font.TIMES_ROMAN, 15 );

        //dlv
        contentStream.newLineAtOffset(25, 725 );
        contentStream.setLeading(14.5f);
        contentStream.showText("PDF BOOK REPORT");
        contentStream.newLine();
        contentStream.newLine();

        //Setting the position for the line


        int count = 0;
        for(int i =0;i<books.size();i++){
            contentStream.showText(books.get(i).toString());
            contentStream.newLine();
            contentStream.newLine();
            count++;
            if(count == 15){
                contentStream.endText();
                contentStream.close();
                doc.addPage(page);
                page = new PDPage();
                contentStream = new PDPageContentStream(doc, page);
                contentStream.beginText();
                contentStream.setFont( PDType1Font.TIMES_ROMAN, 15 );
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25,725);
                count = 0;
            }
        }
        //Ending the content stream
        contentStream.endText();
        System.out.println("Content added");

        //Closing the content stream
        contentStream.close();
        doc.addPage(page);
        //Saving the document
        doc.save("PDFBookReport.pdf");

        //Closing the document
        doc.close();
    }
}
