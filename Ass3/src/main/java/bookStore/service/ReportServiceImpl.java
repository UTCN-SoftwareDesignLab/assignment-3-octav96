package bookStore.service;

import bookStore.entity.Book;
import bookStore.report.Report;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    private Report report;

    @Override
    public void setReport(Report report){
        this.report = report;
    }

    @Override
    public void generateReport(List<Book> books) throws IOException {
        this.report.generateReport(books);
    }
}
