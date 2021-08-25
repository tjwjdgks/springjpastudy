package me.seo.studyjpa.repository;

import me.seo.studyjpa.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    public void curd(){
        Book book = new Book();
        book.setTitle("test");

        bookRepository.save(book);

        assertEquals(1,bookRepository.findAll().size());

        //bookRepository.findOne(Qbook)
    }
}