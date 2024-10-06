class Foo {
    private int cnt;
    public Foo() {
        cnt = 0;
    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized(this)  {
            while (cnt != 0)
                wait();
            cnt = 1;
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            notifyAll();
        }
        
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized(this)  {
            while (cnt != 1)
                wait();
            cnt = 2;
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            notifyAll();
        }
        
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized(this)  {
            while (cnt != 2)
                wait();
            cnt = 3;
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            notifyAll();
        }
        
    }
}