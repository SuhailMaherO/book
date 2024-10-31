package com.example.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book One");
        book1.setAuthor("Author One");
        book1.setPrice(9.99);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book Two");
        book2.setAuthor("Author Two");
        book2.setPrice(19.99);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.findAll();
        assertEquals(2, books.size());
        assertEquals("Book One", books.get(0).getTitle());
        assertEquals("Book Two", books.get(1).getTitle());
    }

    @Test
    void testFindById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book One");
        book.setAuthor("Author One");
        book.setPrice(9.99);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> foundBook = bookService.findById(1L);
        assertTrue(foundBook.isPresent());
        assertEquals("Book One", foundBook.get().getTitle());
    }

    @Test
    void testFindByIdNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Book> foundBook = bookService.findById(99L);
        assertFalse(foundBook.isPresent());
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book One");
        book.setAuthor("Author One");
        book.setPrice(9.99);

        when(bookRepository.save(book)).thenReturn(book);

        Book savedBook = bookService.save(book);
        assertEquals("Book One", savedBook.getTitle());
    }

    @Test
    void testSaveNullBook() {
        assertThrows(IllegalArgumentException.class, () -> bookService.save(null));
    }

    @Test
    void testDeleteById() {
        Long bookId = 1L;
        when(bookRepository.existsById(bookId)).thenReturn(true);
        bookService.deleteById(bookId);
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
