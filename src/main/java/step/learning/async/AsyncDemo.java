package step.learning.async;

public class AsyncDemo {
    public void run(){
        System.out.println("Start");
        new MultiThread().demo();
//        new MultiTask().demo();
        System.out.println("End");
    }
}
