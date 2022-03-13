package com.example.restapi.repository;

import com.example.restapi.model.Book;
import com.example.restapi.model.Lend;
import com.example.restapi.model.LendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
