package online.ragavan.hiding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;

/**
 * The purpose of this code is to demonstrate how hiding, one of the
 * important concepts in Object Oriented programming, could cause
 * hard-to-find bugs that are caused due to mutable shared state the object
 * maintains across concurrent threads.
 *
 * author: Ragavan Natarajan
 * email: n.ragav@gmail.com
 */
class Hiding {

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Date parseDate(String date) {
        try {
            return df.parse(date);
        } catch (Exception exc) {
            System.out.println("Input date: " + date + 
                    "\nMessage: " + exc.getMessage());
            return null;
        }
    }

    public static void main(String[] argv) {

        Hiding hiding = new Hiding();

        // For some randomized number generation
        Random rand = new Random();

        /*
         * Create and start 1000 threads to simulate a real world concurrent
         * application.
         */
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runnable() { 
                public void run() {
                    /*
                     * Introduce a random delay.
                     */
                    try { 
                        Thread.sleep(rand.nextInt(5));
                    } catch (InterruptedException exc) { 
                        exc.printStackTrace(); 
                    }
                    
                    /*
                     * Creating some random but valid Year, Month and Day for
                     * input.
                     */
                    int year = rand.nextInt(50) + 2000;
                    int month = rand.nextInt(12) + 1;
                    int day = rand.nextInt(27) + 1;
                    String date = year + "-" + month + "-" + day;
                    System.out.println("Input : " + date + 
                            "; Output: " + hiding.parseDate(date));
                }
            });
            thread.start();
        }
    }
}
