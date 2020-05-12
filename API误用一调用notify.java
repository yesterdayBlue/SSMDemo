package API����һ����notify;

/*����
 * ȱ������:����Thread.run()
 * ȱ������:����������̵߳�run()������������start()������  */

public class API����һ����notify implements Runnable{
	 private static final Object lock = new Object();
     private static int time = 0;
     private final int step; // Perform operations when field time
     // reaches this value
     public API����һ����notify(int step) {
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
             new Thread(new API����һ����notify(i)).start();
         }
     }
}
