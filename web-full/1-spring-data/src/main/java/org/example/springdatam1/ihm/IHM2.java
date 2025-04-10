package org.example.springdatam1.ihm;

import org.example.springdatam1.entity.Author;
import org.example.springdatam1.entity.Book;
import org.example.springdatam1.entity.Genre;
import org.example.springdatam1.services.AuthorService;
import org.example.springdatam1.services.BookService;
import org.example.springdatam1.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Set;

@Component
public class IHM2 implements CommandLineRunner {


    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;


    @Override
    public void run(String... args) throws Exception {
        menu();
    }


    private void menu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        do{
            displayMemu();
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    menuAuthor();
                    break;
                case 2:
                    menuBook();
                    break;
                case 0:
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Wrong choice");
            }

        }while(choice!=0);

    }


    private void displayMemu(){
        System.out.println("Menu principal:");
        System.out.println("1. Gestion Author");
        System.out.println("2. Gestion Genre");
        System.out.println("3. Gestion Publication");
        System.out.println("4. Gestion Book");
        System.out.println("0. Exit");
        System.out.println("Choisissez une option : ");

    }

    private  void menuAuthor(){
        System.out.println("Author:");
    }

    private  void menuBook(){
        System.out.println("Book:");
    }

}
