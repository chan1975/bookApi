package com.evaluation.book.service;

import com.evaluation.book.exception.BusinessLogicException;
import com.evaluation.book.exception.RecordNotFoundException;
import com.evaluation.book.model.Book;
import com.evaluation.book.repository.BookReepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService implements IBookService {

    public static final int LIMITED_YEAR_PUBLISHED = 10;

    @Autowired
    private BookReepository repository;

    @Override
    public Map<String, Object> findPaginated(int pageNo, int pageSize, String sortBy, String direction) throws BusinessLogicException{
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.fromString(direction), sortBy));

        Page<Book> pagedResult = repository.findAll(paging);
        if(pagedResult.getContent().isEmpty()){
            throw new BusinessLogicException("Sin resultados");
        }
        Map<String , Object> response = new HashMap<>();
        response.put("books", pagedResult.toList());
        response.put("currentPage",pagedResult.getNumber());
        response.put("totalItems",pagedResult.getTotalElements());
        response.put("totalPages",pagedResult.getTotalPages());
        return response;
    }

    public Book getBookById(Integer id) throws BusinessLogicException {
        Optional<Book> book = repository.findById(id);

        if (book.isPresent()) {
            return book.get();
        } else {
            throw new BusinessLogicException("No existe libro con ese identificador");
        }
    }

    public Book createOrUpdateBook(Book entity) throws BusinessLogicException {
        validatePublishedDate(entity);
        Optional<Book> employee = repository.findById(entity.getId());

        if (employee.isPresent()) {
            employee.get().setAuth(entity.getAuth());
            employee.get().setDescription(entity.getDescription());
            employee.get().setName(entity.getName());
            employee.get().setPrice(entity.getPrice());
            employee.get().setPublishedDate(entity.getPublishedDate());
            repository.save(employee.get());

            return employee.get();
        } else {
            validateExistTitle(entity);
            entity = repository.save(entity);

            return entity;
        }
    }

    private void validateExistTitle(Book entity) throws BusinessLogicException {
        Optional<Book> book = repository.findByName(entity.getName());
        if(book.isPresent()){
            throw new BusinessLogicException("Ya existe un registro con el titulo " + entity.getName());
        }
    }

    private void validatePublishedDate(Book entity) throws BusinessLogicException {
        Date reqBook = entity.getPublishedDate();
        Calendar calendarReqBook = Calendar.getInstance();
        calendarReqBook.setTime(reqBook);
        int yearReqBook = calendarReqBook.get(Calendar.YEAR);

        Date currentDate = new Date();
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(currentDate);
        int yearCurrent = calendarCurrent.get(Calendar.YEAR);

        if(yearCurrent - yearReqBook > LIMITED_YEAR_PUBLISHED ){
            throw new BusinessLogicException("Tiempo de publicacion excedido");
        }


    }

    public void deleteBookById(Integer id) throws BusinessLogicException {
        Optional<Book> employee = repository.findById(id);

        if (employee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new BusinessLogicException("No existe libro para eliminar");
        }
    }
}
