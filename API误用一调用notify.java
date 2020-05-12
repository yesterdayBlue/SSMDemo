package API误用一调用notify;

/*描述
 * 缺陷类型:调用Thread.run()
 * 缺陷描述:程序调用了线程的run()方法，而不是start()方法。  */

public class API误用一调用notify implements Runnable{
	 private static final Object lock = new Object();
     private static int time = 0;
     private final int step; // Perform operations when field time
     // reaches this value
     public API误用一调用notify(int step) {
         this.step = step;
     }
     @Override public void run() {
         try {
             synchronized (lock) {
                 while (time != step) {
                     lock.wait();
                 }
                 // Perform operations
                 time++;
                 lock.notify();
             }
         } catch (InterruptedException ie) {
             Thread.currentThread().interrupt(); // Reset interrupted status
         }
     }
     public static void main(String[] args) {
         for (int i = 4; i >= 0; i--) {
             new Thread(new API误用一调用notify(i)).start();
         }
     }
}
