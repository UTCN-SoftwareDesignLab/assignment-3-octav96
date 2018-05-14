package bookStore.report;

import bookStore.entity.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    public static void main(String[] args){
        List<Book> books = new ArrayList<>();
        FileWriter fileWriter = null;
        Book b = new Book("asda", "asdas", "123123", "adsdas", 13l, 12.3);
        String header = new String("author, title, isbn, genre, quantity, price");
        books.add(b);
        books.add(b);
        books.add(b);
        books.add(b);
        books.add(b);
        books.add(b);
        books.add(b);

        try {
            fileWriter = new FileWriter("CSVBookReport.csv");
            fileWriter.append(header);
            fileWriter.append("\n");
            for(int i = 0;i < books.size();i++){
                fileWriter.append(books.get(i).getAuthor());
                fileWriter.append(",");
                fileWriter.append(books.get(i).getTitle());
                fileWriter.append(",");
                fileWriter.append(books.get(i).getIsbn());
                fileWriter.append(",");
                fileWriter.append(books.get(i).getGenre());
                fileWriter.append(",");
                fileWriter.append(String.valueOf(books.get(i).getQuantity()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(books.get(i).getPrice()));
                fileWriter.append("\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();

            }

        }
    }
}
