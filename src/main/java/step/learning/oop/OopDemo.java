package step.learning.oop;

import java.util.ArrayList;
import java.util.List;

public class OopDemo {
    private List<Literature> catalog = new ArrayList<>();
    public void run(){
        System.out.println("OOP");


        try {
            catalog.add(new Book("D. Knuth", "Art of Programming"));
            catalog.add(new OldBook("M. Twen", "Tom Soyer"));
            catalog.add(new Newspaper("Daily Mail", "2024-01-29"));
            catalog.add(new Journal("R&D World", "123"));
            catalog.add(new Map("Odessa", "1:50000"));
            catalog.add(new OldMap("OldOdessa", "1:250000"));
        }
        catch (Exception ex) {
            System.err.println("Literature add error: " + ex.getMessage() ) ;
            return ;
        }
        for(Literature literature : catalog){
            System.out.println(literature.getCard());
        }
        System.out.println("COPYABLE:");
        printCopyable();
        System.out.println("NOT COPYABLE:");
        printNotCopyable();
        System.out.println("OLD:");
        printOld();
        System.out.println("NOT OLD:");
        printNotOld();
    }

//    Перед дз скопируй все файлы из репозитория преподавателя!!!

    private void printNotOld(){
        for (Literature literature : catalog){
            //  Reflection
            Class<?> type = literature.getClass();
            if(!(type.isAnnotationPresent(Used.class))){
                System.out.println(literature.getCard());
            }
        }
    }
    private void printOld(){
        for (Literature literature : catalog){
            //  Reflection
            Class<?> type = literature.getClass();
            if(type.isAnnotationPresent(Used.class)){
                System.out.println(literature.getCard());
            }
        }
    }

    private void printCopyable() {
        for(Literature literature : catalog){
            if(literature instanceof Copyable){
                System.out.println(literature.getCard());
            }

        }
    }
    private void printNotCopyable() {
        for(Literature literature : catalog){
            if(!(literature instanceof Copyable)){
                System.out.println(literature.getCard());
            }

        }
    }
}