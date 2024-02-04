package step.learning.async;

import java.util.Random;

/**
 * Демонстрація багатопоточності
 */
public class MultiThread {
    private static final Random random = new Random();
    private double deposit;
    private final Object depositLocker = new Object();

    public void demo() {
        Runnable task1 = new Runnable() {  // анонімна реалізація
            @Override
            public void run() {
                System.out.println("Hello from task 1");
            }
        };
        // об'єкт функціонального інтерфейсу передається до конструктора потоку
        Thread thread1 = new Thread(task1);   // на даному етапі потік не стартує
        // thread1.run();   // синхронний старт
        thread1.start();  // асинхронний старт (в іншому потоці)

        for (double i = 1; i <= 12; i++) {
            new Thread(new DepositTask(i)).start();
        }

    }

    class DepositTask implements Runnable {   // nested class - клас у середині іншого класу
        double percent;

        public DepositTask(double percent) {
            this.percent = percent;
            deposit = 100.0;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(150 /*+ random.nextInt(150)*/);
            } catch (InterruptedException e) {
                System.err.println("Sleeping interrupt");
            }
            double before, after;
            double k = (1.0 + percent / 100.0);
            synchronized (depositLocker) {
                before = deposit;
                deposit *= k;
                after = deposit;
            }
            System.out.printf("Before +%.1f: %.2f\n", percent, before);
            System.out.printf("After: +%.1f: %.2f\n", percent, after);

        }
    }
}
/*
Багатопоточність - спосіб реалізації асинхронного виконання коду
з використанням об'єктів Thread (пов'язаних з операційною системою).
З т.з. ОС існує API CreateThread(...) у який передається адреса коду
та адреса даних.
У Java традиційна ООП, яка "мінімальною" одиницею вважає об'єкт
(а не метод, як у C#). Для цих задач введені функціональні інтерфейси -
типи даних (інтерфейси), які мають лише один метод.

Start - demo - thread1.start() - Finish
                            \
                             [CreateThread] Hello from task 1

( 100 + 20% ) + 10%  =?=  ( 100 + 10% ) + 20%
100 * 1.2 * 1.1  == 100 * 1.1 * 1.2
Асинхронність найкраще підходить для задач, в яких можна переставляти
порядок оброблення даних (порядко-незалежні алгоритми).
 */