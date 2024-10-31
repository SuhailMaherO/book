package com.example.book;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "bookList"; // Make sure this template exists
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook"; // Make sure this template exists
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "addBook"; // Return to the add form if there are errors
        }
        bookService.save(book);
        return "redirect:/books"; // Redirect to list after saving
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "updateBook"; // Make sure this template exists
        } else {
            return "redirect:/books?error=BookNotFound"; // Redirect if the book is not found
        }
    }

    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "updateBook"; // Return to the update form if there are errors
        }
        if (bookService.findById(id).isPresent()) {
            book.setId(id); // Set the ID for the book being updated
            bookService.save(book);
            return "redirect:/books"; // Redirect to the list after updating
        } else {
            return "redirect:/books?error=BookNotFound"; // Redirect if the book is not found
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return "redirect:/books"; // Redirect after deletion
        } else {
            return "redirect:/books?error=BookNotFound"; // Redirect if the book is not found
        }
    }
}
