class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Thread is running: " + Thread.currentThread().getName());
    }
}

class MyTask implements Runnable {
    private String taskName;
    private String symbol; // 🟢 or 🔴

    public MyTask(String taskName, String symbol) {
        this.taskName = taskName;
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println(symbol + " " + taskName + " - Execution " + i + " on thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(50); // Simulating work with sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread extends Thread {
    public MyThread(String taskName) {
        super(taskName); // Setting the thread name
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Execution " + i + " on thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//class Resource {
//    public synchronized void method1(Resource other) {
//        System.out.println(Thread.currentThread().getName() + " acquired lock on " + this);
//        try {
//            Thread.sleep(100); // Simulate some work
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(Thread.currentThread().getName() + " trying to acquire lock on " + other);
//        other.method2();
//    }
//
//    public synchronized void method2() {
//        System.out.println(Thread.currentThread().getName() + " acquired lock on " + this + " inside method2");
//    }
//}

class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    public synchronized void method1(Resource other) {
        System.out.println(Thread.currentThread().getName() + " acquired lock on " + this.name);
        try {
            Thread.sleep(100); // Simulate some work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " trying to acquire lock on " + other.name);
        other.method2();
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + " acquired lock on " + this.name + " inside method2");
    }

    @Override
    public String toString() {
        return name;
    }
}

class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Main Thread: " + Thread.currentThread().getName());

//        Thread thread1 = new Thread(new MyRunnable(), "Thread-1");
//        Thread thread2 = new Thread(new MyRunnable(), "Thread-2");
//
//        thread1.start();
//        thread2.start();

        /**
         * Another Example
         */

//        Thread thread1 = new Thread(new MyTask("Task 1", "🟢"), "Thread-1");
//        Thread thread2 = new Thread(new MyTask("Task 2", "🔴"), "Thread-2");
//
//        // Starting both threads
//        thread1.start();
//        thread2.start();

        /**
         * Implementing thread by extending thread class
         */
//        MyThread thread1 = new MyThread("🟢 Thread-1");
//        MyThread thread2 = new MyThread("🔴 Thread-2");
//
//        thread1.start();
//        thread2.start();

        /**
         * Dead lock implementation
         */

//        Resource resource1 = new Resource();
//        Resource resource2 = new Resource();
//
//        // Thread 1 tries to acquire locks on resource1, then resource2
//        Thread thread1 = new Thread(() -> {
//            resource1.method1(resource2);
//        }, "Thread-1");
//
//        // Thread 2 tries to acquire locks on resource2, then resource1
//        Thread thread2 = new Thread(() -> {
//            resource2.method1(resource1);
//        }, "Thread-2");
//
//        // Start both threads
//        thread1.start();
//        thread2.start();

        /**
         * Avoid deadlock with strict order
         */

//        Resource resource1 = new Resource("Resource 1");
//        Resource resource2 = new Resource("Resource 2");
//
//        // Thread 1 acquires locks in the order of resource1, then resource2
//        Thread thread1 = new Thread(() -> {
//            acquireResourcesInOrder(resource1, resource2);
//        }, "Thread-1");
//
//        // Thread 2 also acquires locks in the order of resource1, then resource2
//        Thread thread2 = new Thread(() -> {
//            acquireResourcesInOrder(resource1, resource2);
//        }, "Thread-2");
//
//        // Start both threads
//        thread1.start();
//        thread2.start();

        /**
         * race condition
         */
//        Counter counter = new Counter();
//
//        Thread thread1 = new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                counter.increment();
//                System.out.println("Thread 1 " + counter.getCount());
//            }
//        });
//
//        Thread thread2 = new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                counter.increment();
//                System.out.println("Thread 2 " + counter.getCount());
//            }
//        });
//
//        thread1.start();
//        thread2.start();
//
//        try {
//            thread1.join();
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Now the final counter value will always be 2000
//        System.out.println("Final counter value: " + counter.getCount());

    }

    public static void acquireResourcesInOrder(Resource res1, Resource res2) {
        synchronized (res1) {
            System.out.println(Thread.currentThread().getName() + " acquired lock on " + res1);
            try {
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (res2) {
                System.out.println(Thread.currentThread().getName() + " acquired lock on " + res2);
                res1.method1(res2);
            }
        }
    }
}