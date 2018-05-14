package bookStore;

import bookStore.dto.BookDTO;
import bookStore.dto.UserDTO;
import bookStore.entity.Book;
import bookStore.entity.User;
import bookStore.report.ReportCSV;
import bookStore.report.ReportPDF;
import bookStore.service.BookService;
import bookStore.service.ReportService;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;
    @Autowired
    ReportService reportService;

    @GetMapping(path = "/admin")
    public String admin(){
        return "/admin";
    }

    //CREATE BOOK
    @GetMapping("/book")
    public String greetingForm(Model model) {
        model.addAttribute("book", new BookDTO());

        return "book";
    }

    @PostMapping("/book")
    public String greetingSubmit(@ModelAttribute("book") @Valid BookDTO bookDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "book";
        }
        bookService.create(bookDTO);


        return "result";
    }

    //DELETE BOOK
    @GetMapping("/delete_book")
    public String deleteBookForm(Model model){
        model.addAttribute("del",new BookDTO());
        return "delete_book";
    }

    @PostMapping("/delete_book")
    public String deleteBookSubmit(@ModelAttribute("book") @Valid BookDTO bookDTO,
                                   BindingResult bindingResult){
        if (bindingResult.hasFieldErrors("isbn")) {
            // List<Book> bookDTOList = bookService.findAllByGenre(bookDTO.getGenre());
            //ModelAndView mav1 = new ModelAndView("books_by_title");
            return "delete_book";
        }
        bookService.deleteByIsbn(bookDTO.getIsbn());
        return "delete_book_result";
    }

    //UPDATE BOOK
    @GetMapping("/update_book")
    public String updateBookForm(Model model){
        model.addAttribute("upd",new BookDTO());
        return "update_book";
    }

    @PostMapping("/update_book")
    public String updateBookSubmit(@ModelAttribute("upd") @Valid BookDTO bookDTO,
                                   BindingResult bindingResult){
        // bookService.deleteByIsbn(bookDTO.getIsbn());
        if (bindingResult.hasFieldErrors("isbn") && bindingResult.hasFieldErrors("quantity")) {
            // List<Book> bookDTOList = bookService.findAllByGenre(bookDTO.getGenre());
            //ModelAndView mav1 = new ModelAndView("books_by_title");
            return "update_book";
        }
        bookService.updateBook(bookDTO.getQuantity(),bookDTO.getIsbn());
        return "update_book_result";
    }

    //LIST ALL BOOKS
    @GetMapping("/list_all_books")
    public ModelAndView listAllBooks(Model model){
        List<Book> books = bookService.getAll();

        ModelAndView mav = new ModelAndView("list_all_books");

        mav.addObject("books", books);

        return mav;
    }


    /*  @GetMapping(path = "/list_all_users")
    public ModelAndView ListAllUsers(){
        List<User> users =  userService.getAll();

        ModelAndView mav = new ModelAndView("list_all_users");
        mav.addObject(users);
        return mav;
    }
*/
    //CREATE USER
    @GetMapping(path = "/add_user")
    public String addUserForm(Model model){
        model.addAttribute("user", new UserDTO());
        return "add_user";

    }

    @PostMapping(path = "/add_user")
    public String addUserSubmit(@ModelAttribute("user") @Valid UserDTO userDTO,
                                BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "add_user";
        }
      //  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userDTO.setPassword(userDTO.getPassword());
        userService.create(userDTO);
        return "add_user_result";
    }

    //DELETE USER
    @GetMapping(path = "/delete_user")
    public String deleteUserForm(Model model){
        model.addAttribute("user", new UserDTO());
        return "delete_user";
    }

    @PostMapping(path = "/delete_user")
    public String deleteUserSubmit(@ModelAttribute("user") @Valid UserDTO userDTO,
                                   BindingResult bindingResult){
        if (bindingResult.hasFieldErrors("username")) {
            return "delete_user";
        }
        userService.deleteByUsername(userDTO.getUsername());
        return "delete_user_result";
    }

    //UPDATE USER
    @GetMapping(path = "/update_user")
    public String updateUserForm(Model model){
        model.addAttribute("user",new UserDTO());
        return "/update_user";
    }

    @PostMapping(path = "/update_user")
    public String updateUserSubmit(@ModelAttribute("user") @Valid UserDTO userDTO,
                                   BindingResult bindingResult){
        if (bindingResult.hasFieldErrors("address") && bindingResult.hasFieldErrors("username")) {
            return "update_user";
        }
        userService.updateUser(userDTO.getAddress(), userDTO.getUsername());
        return "/update_user_result";
    }

    //LIST ALL USERS
    @GetMapping(path = "/list_all_users")
    public ModelAndView ListAllUsers(){
        List<User> users =  userService.getAll();

        ModelAndView mav = new ModelAndView("list_all_users");
        mav.addObject("users",users);
        return mav;
    }

    //REPORTS
    @GetMapping("/reportPDF")
    public String generatePDFReport() throws IOException {
        reportService.setReport(new ReportPDF());
        reportService.generateReport(bookService.findAllByQuantity(0l));
        return "/report_generated_successfully";
    }

    @GetMapping("/reportCSV")
    public String generateCSVReport() throws IOException {
        reportService.setReport(new ReportCSV());
        reportService.generateReport(bookService.findAllByQuantity(0l));
        return "/report_generated_successfully";
    }
}
