package com.example.restapi.controller;

import com.example.restapi.model.Author;
import com.example.restapi.model.Book;
import com.example.restapi.model.Member;
import com.example.restapi.model.request.AuthorCreationRequest;
import com.example.restapi.model.request.BookCreationRequest;
import com.example.restapi.model.request.BookLendRequest;
import com.example.restapi.model.request.MemberCreationRequest;
import com.example.restapi.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    /* 테스트 순서
    1. 저자 등록/리스트조회/1명조회(Id)/삭제(Id)
    2. 도서 등록/리스트조회/1권조회(Id)/1권조회(Id+Isbn)/수정(Id)/삭제(Id)
    3. 회원 등록/리스트조회/1명조회(Id)/수정(Id)/삭제(Id)
    FINAL 도서 대출/대출연장/반납
     */
    // 1-1 저자 등록
    @PostMapping("/author/add")
    public ResponseEntity<Author> createAuthor (@RequestBody AuthorCreationRequest request) {
        return ResponseEntity.ok(libraryService.createAuthor(request));
    }

    // 1-2 저자 리스트조회
    @PostMapping("/author/list")
    public ResponseEntity<List<Author>> readAuthors () {
        return null;
    }

    // 1-3 저자 1명 조회 (Id)

    // 1-4 저자 삭제 (Id)

    // 2-1 도서 등록
    @PostMapping("/book/add")
    public ResponseEntity<Book> createBook (@RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(libraryService.createBook(request));
    }

    // 2-2 도서 리스트 조회
    @PostMapping("/book/list")
    public ResponseEntity readBooks(@RequestParam(required = false) String isbn) {
        if (isbn == null) {
            return ResponseEntity.ok(libraryService.readBooks());
        }
        return ResponseEntity.ok(libraryService.readBook(isbn));
    }

    // 2-3 도서 1권 조회 (Id)
    @PostMapping("/book/{bookId}")
    public ResponseEntity<Book> readBook (@PathVariable Long bookId) {
        return ResponseEntity.ok(libraryService.readBook(bookId));
    }

    // 2-4 도서 1권 조회 (Id+Isbn)

    // 2-5 도서 수정 (Id)

    // 2-6 도서 삭제 (Id)
    @PostMapping("/book/delete/{bookId}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long bookId) {
        libraryService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }

    // 3-1 회원 등록
    @PostMapping("/member/add")
    public ResponseEntity<Member> createMember (@RequestBody MemberCreationRequest request) {
        return ResponseEntity.ok(libraryService.createMember(request));
    }

    // 3-2 회원 리스트 조회

    // 3-3 회원 1명 조회 (Id)

    // 3-4 회원 수정 (Id)
    @PostMapping("/member/edit/{memberId}")
    public ResponseEntity<Member> updateMember (@RequestBody MemberCreationRequest request, @PathVariable Long memberId) {
        return ResponseEntity.ok(libraryService.updateMember(memberId, request));
    }

    // 3-5 회원 삭제 (Id)

    // FINAL-1 도서 대출
    @PostMapping("/book/lend")
    public ResponseEntity<List<String>> lendABook(@RequestBody BookLendRequest bookLendRequests) {
        return ResponseEntity.ok(libraryService.lendABook((List<BookLendRequest>) bookLendRequests));
    }

    // FINAL-2 도서 대출연장

    // FINAL-3 도서 반납

}
