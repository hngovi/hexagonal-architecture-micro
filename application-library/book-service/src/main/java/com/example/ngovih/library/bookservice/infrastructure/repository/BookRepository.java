package com.example.ngovih.library.bookservice.infrastructure.repository;

import com.example.ngovih.library.bookservice.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <BookEntity, Long> {
}
