package src.main.java.org.JavaLearning;

public class DeadlockExample {
    static final Object resource1 = new Object(); // resource1 lock
    static final Object resource2 = new Object(); // resource2 lock

    public static void main(String[] args) {
        // Thread 1 tries to lock resource1 then resource2
        Thread thread1 = new Thread() {
            public void run() {
                synchronized (resource1) {
                    System.out.println("Thread 1: Locked resource 1");

                    try { Thread.sleep(50); } catch (InterruptedException e) {}

                    System.out.println("Thread 1: Waiting for resource 2");
                    synchronized (resource2) {
                        System.out.println("Thread 1: Locked resource 2");
                    }
                }
            }
        };

        // Thread 2 tries to lock resource2 then resource1
        Thread thread2 = new Thread() {
            public void run() {
                synchronized (resource2) {
                    System.out.println("Thread 2: Locked resource 2");

                    try { Thread.sleep(50); } catch (InterruptedException e) {}

                    System.out.println("Thread 2: Waiting for resource 1");
                    synchronized (resource1) {
                        System.out.println("Thread 2: Locked resource 1");
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}
