class FooBar {
    private int n;
    private boolean nextFoo;
    public FooBar(int n) {
        this.n = n;
        nextFoo = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            synchronized(this)  {
                while (!nextFoo)
                    wait();
                nextFoo = !nextFoo;
                // printFoo.run() outputs "foo". Do not change or remove this line.
        	    printFoo.run();
                notifyAll();
            }
        	
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            synchronized(this)  {
                while (nextFoo)
                    wait();
                nextFoo = !nextFoo;
                // printBar.run() outputs "bar". Do not change or remove this line.
        	    printBar.run();
                notifyAll();
            }
        }
    }
}