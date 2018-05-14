package bookStore.service;

import bookStore.entity.Book;
import bookStore.report.Report;

import java.io.IOException;
import java.util.List;

public class CachingReportService implements ReportService{
    ReportService origin;
    public CachingReportService(ReportService origin){
        this.origin = origin;
    }

    @Override
    public void setReport(Report report) {
        origin.setReport(report);
    }

    @Override
    public void generateReport(List<Book> books) throws IOException {
        origin.generateReport(books);
    }
}
