package com.example.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        if (book == null || book.getTitle() == null || book.getAuthor() == null || book.getPrice() < 0) {
            throw new IllegalArgumentException("Invalid book data");
        }
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Book with ID " + id + " does not exist.");
        }
    }
}
