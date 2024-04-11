package com.api.book.bootrestbook.service;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks()
    {
        List<Book> list = (List<Book>)bookRepository.findAll();
        return list;
    }
    public Book getBookById(int id)
    {
        Book book =null;
        try
        {
            book = bookRepository.findById(id);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return book;
    }
    public Book addBook(Book b)
    {
        Book book = this.bookRepository.save(b);
        return book;
    }
    public void deleteBook(int id)
    {
        bookRepository.deleteById(id);
    }
    public void updateBook(Book book, int id) {
        book.setId(id);
        bookRepository.save(book);
    }
}
