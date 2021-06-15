package com.example.ngovih.library.bookservice.infrastructure.mappers;

import com.example.ngovih.library.bookservice.domain.model.Book;
import com.example.ngovih.library.bookservice.infrastructure.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    Book bookEntityToBook(BookEntity bookEntity);
    BookEntity bookToBookEntity(Book book);
    List<Book> bookEntityListToBookList(List<BookEntity> bookEntityList);
    List<BookEntity> bookListToBookEntityList(List<Book> bookList);
}
