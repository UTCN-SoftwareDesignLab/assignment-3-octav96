package bookStore;

import bookStore.report.Report;
import bookStore.repository.BookRepository;
import bookStore.repository.ConsultationRepository;
import bookStore.repository.PatientRepository;
import bookStore.repository.UserRepository;
import bookStore.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean(name = "BookService")
    public BookService bookService(BookRepository repository) {
        return new CachingBookService(new BookServiceImpl(repository));
    }

    @Bean(name = "UserService")
    public UserService userService(UserRepository repository) {
        return new CachingUserService(new UserServiceImpl(repository));
    }

    @Bean(name = "PatientService")
    public PatientService patientService(PatientRepository repository){
        return new CachingPatientService(new PatientServiceImpl(repository));
    }

    @Bean(name = "ConsultationService")
    public ConsultationService consultationService(ConsultationRepository repository,
                                                   PatientRepository patientRepository,
                                                   UserRepository userRepository){
        return new CachingConsultationService(new ConsultationServiceImpl(repository, patientRepository, userRepository));
    }
    /*@Bean(name = "ReportService")
    public ReportService reportService(Report report) {
        ReportService reportService = new ReportServiceImpl();
        reportService.setReport(report);
        return new CachingReportService(reportService);
    }*/
}
