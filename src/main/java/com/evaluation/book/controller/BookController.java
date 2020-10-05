package com.evaluation.book.controller;

import com.evaluation.book.exception.BusinessLogicException;
import com.evaluation.book.exception.RecordNotFoundException;
import com.evaluation.book.exception.BusinessLogicExceptionController;
import com.evaluation.book.model.Book;
import com.evaluation.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("/books")
    public ResponseEntity<Map<String, Object>> getBookPaginated(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) throws BusinessLogicException{
        Map<String, Object> response = service.findPaginated(pageNo, pageSize, sortBy, direction);
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getEmployeeById(@PathVariable("id") Integer id) throws BusinessLogicException {
        Book entity = service.getBookById(id);

        return new ResponseEntity<Book>(entity, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping("/books")
    public ResponseEntity<Book> createOrUpdateEmployee(@RequestBody Book book) throws BusinessLogicException {
        Book updated = service.createOrUpdateBook(book);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Integer id)
            throws BusinessLogicException {
        service.deleteBookById(id);
        return HttpStatus.FORBIDDEN;
    }

}
