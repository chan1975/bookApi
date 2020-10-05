package com.evaluation.book.repository;

import com.evaluation.book.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookReepository extends PagingAndSortingRepository<Book, Integer> {

    Optional<Book> findByName(@Param("name") String name);

}
