import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo {

    public static void main(String[] args){

        ExecutorService executor = Executors.newFixedThreadPool(3);


        for (int i = 1; i <=5; i++){

            final int orderNumber = i;

            executor.submit(() -> {

                System.out.println("Processing Order " + orderNumber + " using " + Thread.currentThread().getName());


                try{

                    Thread.sleep(1000);

                }
                catch(InterruptedException e){

                    Thread.currentThread().interrupt();


                }

            });


        }

        executor.shutdown();

    }
    }
    

