package bookStore.report;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bookStore.entity.Book;
import bookStore.repository.BookRepository;
import bookStore.service.BookService;
import bookStore.service.BookServiceImpl;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Main {
   // public BookService bookService;
    public static void main(String args[]) throws IOException {
        List<String> books = new ArrayList<>();

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");

        books.add("1");
       // String fileName = "PDFReport-"+new Date().toString()+ ".pdf";
        //Loading an existing document
        PDDocument doc = new PDDocument();

        //Creating a PDF Document
        PDPage page = new PDPage();
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont( PDType1Font.TIMES_ROMAN, 30 );

        //dlv
        contentStream.newLineAtOffset(25, 725 );
        contentStream.setLeading(14.5f);
        contentStream.showText("PDF BOOK REPORT");
        contentStream.newLine();
        contentStream.newLine();

        //Setting the position for the line

        String text = "This is an example of adding text to a page in the pdf document." +
        "we can add as many lines as we want like this using the draw string method" +
        "of the ContentStream class";

        //Adding text in the form of string
        contentStream.showText(text);
        contentStream.newLine();
        contentStream.newLine();
        int count = 0;
        for(int i =0;i<books.size();i++){
            contentStream.showText(books.get(i));
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
                contentStream.setFont( PDType1Font.TIMES_ROMAN, 30 );
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
