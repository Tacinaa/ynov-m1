package org.example.springdatam1.ihm;

import org.example.springdatam1.entity.Author;
import org.example.springdatam1.entity.Book;
import org.example.springdatam1.entity.Genre;
import org.example.springdatam1.repository.AuthorRepository;
import org.example.springdatam1.repository.BookRepository;
import org.example.springdatam1.repository.GenreRepository;
import org.example.springdatam1.services.AuthorService;
import org.example.springdatam1.services.BookService;
import org.example.springdatam1.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Set;

@Component
public class IHM  {


    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;




  //  @Override
    public void run(String... args) throws Exception {
       // create();
        // displayAuthorAndBooks(2L);
        // displaySuite(2L);
       // delete();
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Donne moi ton prenom : ");
        String name = scanner.next();
        System.out.println("Mon prenom est : " + name);*/

        //System.out.println(bookService.findById(2L));

    }


    private void create() {
      //  Author author = new Author();
      //  author.setName("Victor Hugo");

      //  Author author1Saved = authorService.save(author);

      //  System.out.println("Author created : " + author1Saved);

      // Author author1 = new Author();
      // author1.setName("Maho");
      // Author author2Saved = authorService.save(author1);

       // System.out.println("Author created : " + author2Saved);

       // Book book1 = new Book();
       // book1.setTitle("Les miseroides");
       // Book book2 = new Book();
       // book2.setTitle("Les fleurs me font pas du bien");

       // book1.setAuthor(author2Saved);

       // Book bookSaved = bookRepository.save(book1);

       // System.out.println("Book created : " + bookSaved);


       // System.out.println(authorService.findById(6L));

       // System.out.println(authorService.findAll());


          Author author = new Author();
          author.setName("Lucas");

          Author author2 = authorService.save(author);

         Genre genre1 = new Genre();
         Genre genre2 = new Genre();

         genre1.setName("Fiction");
         genre2.setName("Romance");

        Genre genreSaved = genreService.save(genre1);
        Genre genreSaved2 = genreService.save(genre2);
        Book book1 = new Book();
        book1.setTitle("Ma vie à Monaco");

        book1.setAuthor(author);
        book1.getGenres().add(genreSaved);
        book1.getGenres().add(genreSaved2);

        Book booksaved = bookService.save(book1);

        System.out.println("Book saved: " + booksaved);




    }

    public void displayAuthorAndBooks(Long id){

        Author author = authorService.findById(id);

        System.out.println("Author : " + author.getName());

      //  Set<Book> books = author.getBooks();

       // System.out.println("Books : " + books);





    }

    public void displaySuite(Long id){

        Author author = authorService.findById(id);

        System.out.println("Author : " + author.getName());

        Set<Book> books = author.getBooks();

        System.out.println("Books : " + books);

    }



   public void delete(){

      //  authorService.delete(2L);
        bookService.delete(3L);



    }

}
