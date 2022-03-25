package com.example.springjpa.repository;

import com.example.springjpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update();

    @Query(value = "select * from book order by id desc limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
