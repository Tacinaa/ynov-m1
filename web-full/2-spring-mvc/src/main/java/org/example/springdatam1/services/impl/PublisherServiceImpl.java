package org.example.springdatam1.services.impl;

import org.example.springdatam1.entity.Book;
import org.example.springdatam1.entity.Publisher;
import org.example.springdatam1.repository.BookRepository;
import org.example.springdatam1.repository.PublisherRepository;
import org.example.springdatam1.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher update(Long id, Publisher publisherDetails) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
        publisher.setName(publisherDetails.getName());
        publisher.setBook(publisherDetails.getBook());
        return publisherRepository.save(publisher);
    }


    @Override
    public void delete(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
        if (publisher.getBook() != null) {
            Book book = bookRepository.findById(publisher.getBook().getId()).orElseThrow(()
                    -> new RuntimeException("Book not found with id " + publisher.getBook().getId()));
            book.setPublisher(null);
            bookRepository.save(book);
        }
        publisherRepository.delete(publisher);
    }
}
