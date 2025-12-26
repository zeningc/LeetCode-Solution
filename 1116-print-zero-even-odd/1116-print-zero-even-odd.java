class ZeroEvenOdd {
    private int n;
    private boolean shouldPutZero;
    private boolean odd;
    private int cnt;
    
    public ZeroEvenOdd(int n) {
        this.n = n;
        shouldPutZero = true;
        odd = true;
        cnt = 1;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized(this)  {
                while (!shouldPutZero)
                    wait();
                printNumber.accept(0);
                shouldPutZero = !shouldPutZero;
                notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n / 2; i++) {
            synchronized(this)  {
                while (shouldPutZero || odd)
                    wait();
                printNumber.accept(cnt);
                shouldPutZero = !shouldPutZero;
                odd = !odd;
                cnt++;
                notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < (n + 1) / 2; i++) {
            synchronized(this)  {
                while (shouldPutZero || !odd)
                    wait();
                printNumber.accept(cnt);
                shouldPutZero = !shouldPutZero;
                odd = !odd;
                cnt++;
                notifyAll();
            }
        }
    }
}