package bookStore;

import bookStore.dto.BookDTO;
import bookStore.entity.Book;
import bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    BookService bookService;

    @GetMapping("/employee")
    public String employee(){
        return "/employee";
    }

    @GetMapping("/books_by_genre")
    public String getAllBooksByGenreForm(Model model){
        model.addAttribute("book",new BookDTO());
        return "books_by_genre";
    }

    @PostMapping("/books_by_genre")
    public ModelAndView geetAllBooksByGenreSubmit(@ModelAttribute("book") @Valid BookDTO bookDTO,
                                                  BindingResult bindingResult){
        if (bindingResult.hasFieldErrors("genre")) {
            // List<Book> bookDTOList = bookService.findAllByGenre(bookDTO.getGenre());
            ModelAndView mav1 = new ModelAndView("books_by_genre");
            return mav1;
        }
        List<Book> bookDTOList = bookService.findAllByGenre(bookDTO.getGenre());
        ModelAndView mav = new ModelAndView("books_list");
        mav.addObject("bookDTOList", bookDTOList);
        return mav;
    }

    @GetMapping("/books_by_author")
    public String getAllBooksByAuthorForm(Model model){
        model.addAttribute("book",new BookDTO());
        return "books_by_author";
    }

    @PostMapping("/books_by_author")
    public ModelAndView geetAllBooksByAuthorSubmit(@ModelAttribute("book") @Valid BookDTO bookDTO,
                                                   BindingResult bindingResult){
        if (bindingResult.hasFieldErrors("author")) {
            // List<Book> bookDTOList = bookService.findAllByGenre(bookDTO.getGenre());
            ModelAndView mav1 = new ModelAndView("books_by_author");
            return mav1;
        }
        List<Book> bookDTOList = bookService.findAllByAuthor(bookDTO.getAuthor());
        ModelAndView mav = new ModelAndView("books_list");
        mav.addObject("bookDTOList", bookDTOList);
        return mav;
    }

    @GetMapping("/books_by_title")
    public String getAllBooksByTitleForm(Model model){
        model.addAttribute("book",new BookDTO());
        return "books_by_title";
    }

    @PostMapping("/books_by_title")
    public ModelAndView geetAllBooksByTitleSubmit(@ModelAttribute("book") @Valid BookDTO bookDTO,
                                                  BindingResult bindingResult){
        if (bindingResult.hasFieldErrors("title")) {
            // List<Book> bookDTOList = bookService.findAllByGenre(bookDTO.getGenre());
            ModelAndView mav1 = new ModelAndView("books_by_title");
            return mav1;
        }
        List<Book> bookDTOList = bookService.findAllByTitle(bookDTO.getTitle());
        ModelAndView mav = new ModelAndView("books_list");
        mav.addObject("bookDTOList", bookDTOList);
        return mav;
    }

    @GetMapping("/sell_book")
    public String sellBook(Model model){
        model.addAttribute("book", new BookDTO());
        return "/sell_book";
    }

    @PostMapping("/sell_book")
    public String sellBookResult(@ModelAttribute("book") @Valid BookDTO bookDTO,
                                 BindingResult bindingResult){

        if(bindingResult.hasFieldErrors("isbn") && bindingResult.hasFieldErrors("quantity")){
            return "sell_book";
        }

        Book b = bookService.findByIsbn(bookDTO.getIsbn());
        if(b.getQuantity() - bookDTO.getQuantity() < 0){
            return "book_selling_fail";
        }else{

            bookService.updateBook(b.getQuantity()-bookDTO.getQuantity(),bookDTO.getIsbn());
            return "book_selling_success";
        }

    }

    @GetMapping("/list_all_books_employee")
    public ModelAndView listAllBooks(Model model){
        List<Book> books = bookService.getAll();

        ModelAndView mav = new ModelAndView("list_all_books_employee");

        mav.addObject("books", books);

        return mav;
    }



}
