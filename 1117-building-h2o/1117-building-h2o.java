class H2O {
    Semaphore oSlot = new Semaphore(1);
    Semaphore hSlot = new Semaphore(2);
    Semaphore hGo = new Semaphore(0);
    Semaphore oGo = new Semaphore(0);
    Semaphore hReady = new Semaphore(0);
    public H2O() {
        
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		hSlot.acquire();
        hReady.release();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hGo.acquire();
        releaseHydrogen.run();
        oGo.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSlot.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        hReady.acquire(2);
        hGo.release(2);
        oGo.acquire(2);
        releaseOxygen.run();
        oSlot.release(1);
        hSlot.release(2);
    }
}