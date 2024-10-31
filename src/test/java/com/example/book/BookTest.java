package com.example.book;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testGettersAndSetters() {
        Book book = new Book();

        // Test title
        book.setTitle("Test Book");
        assertEquals("Test Book", book.getTitle());

        // Test author
        book.setAuthor("Test Author");
        assertEquals("Test Author", book.getAuthor());

        // Test price
        book.setPrice(19.99);
        assertEquals(19.99, book.getPrice());

        // Test ID
        book.setId(1L);
        assertEquals(1L, book.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Test Book");
        book1.setAuthor("Test Author");
        book1.setPrice(19.99);

        Book book2 = new Book();
        book2.setId(1L);
        book2.setTitle("Test Book");
        book2.setAuthor("Test Author");
        book2.setPrice(19.99);

        // Test equality
        assertEquals(book1, book2);

        // Test hashCode
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void testNotEquals() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Test Book A");
        book1.setAuthor("Test Author A");
        book1.setPrice(9.99);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Test Book B");
        book2.setAuthor("Test Author B");
        book2.setPrice(19.99);

        // Test non-equality for different books
        assertNotEquals(book1, book2);
    }

    @Test
    void testToString() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPrice(19.99);

        String expected = "Book{id=1, title='Test Book', author='Test Author', price=19.99}";
        assertEquals(expected, book.toString());
    }

    @Test
    void testNegativeAndZeroPrice() {
        Book book = new Book();

        // Test setting zero price
        book.setPrice(0.0);
        assertEquals(0.0, book.getPrice());

        // Test setting negative price
        book.setPrice(-5.0);
        assertEquals(-5.0, book.getPrice());
    }
}
