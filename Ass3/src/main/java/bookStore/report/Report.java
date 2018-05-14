package bookStore.report;

import bookStore.entity.Book;

import java.io.IOException;
import java.util.List;

public interface Report {
    void generateReport(List<Book> books) throws IOException;
}
