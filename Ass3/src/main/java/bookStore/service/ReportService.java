package bookStore.service;

import bookStore.entity.Book;
import bookStore.report.Report;

import java.io.IOException;
import java.util.List;

public interface ReportService {
    public void setReport(Report report);
    public void generateReport(List<Book> books) throws IOException;
}
