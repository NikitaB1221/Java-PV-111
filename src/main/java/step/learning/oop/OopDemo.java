package step.learning.oop;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class OopDemo {
    public void run(){
        System.out.println("OOP");
        List<Literature> catalog = new ArrayList<>();
/*        book.setAuthor("D. Knuth");
        book.setTitle("Art of Programming");*/
        try {
            catalog.add(new Book("D. Knuth","Art of Programming"));
            catalog.add(new Newspaper("Daily Mail", "2024-01-29"));
            catalog.add(new Journal("R&D World", "123"));
            catalog.add(new Map("Odessa's Old Map", "1:50000"));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return;
        }
        for (Literature literature : catalog){
            System.out.println(literature.getCard());
        }
    }
}
/*
* Literature[ -title +getTitle +setTitle +getCard ]
* Book[ [[Literature]] -author +getAuthor +setAuthor +getCard ]
* */