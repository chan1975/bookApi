package com.evaluation.book.service;

import com.evaluation.book.model.Book;

import java.util.List;
import java.util.Map;

public interface IBookService {

    Map<String, Object> findPaginated(int pageNo, int pageSize, String sortBy, String direction);
}
