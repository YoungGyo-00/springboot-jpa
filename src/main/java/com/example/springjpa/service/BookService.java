package com.example.springjpa.service;

import com.example.springjpa.domain.Author;
import com.example.springjpa.domain.Book;
import com.example.springjpa.repository.AuthorRepository;
import com.example.springjpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putBookAndAuthor() {
        Book book = new Book();

        book.setName("JPA 시작하기");
        bookRepository.save(book);

        try{
            authorService.putAuthor();
        } catch (RuntimeException e) {
        }

        /*Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        throw new RuntimeException("오류가 나서 DB 저장 x");*/
//        throw new RuntimeException("error -> Transaction?");
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void get(Long id) {
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();
    }
}
