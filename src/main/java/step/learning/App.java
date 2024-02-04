package step.learning;

import step.learning.async.AsyncDemo;
import step.learning.oop.OopDemo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
//        new Basics().run();
//        new FileIO().run();
//        new OopDemo().run();
        new AsyncDemo().run();
    }
}

/*
ООП: парадигма, согласно которой программа представляет собой набор объектов, взаимодействующих друг с другом
- инкапсуляция
полиморфизм
абстракция/наследование
Пример: библиотека (ТЗ: оцифровывать библиотеку)
выделить сущности: книга, газета, журнал
моделирование: книга (автор, название), газета (дата, название), журнал (номер, название)
абстракция (группировка, обобщение): литература (название)
книга (автор)
газета (дата)
журнал (номер)
*/