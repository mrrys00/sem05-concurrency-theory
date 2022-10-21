public class Customer extends Thread implements Runnable {
    private String name;
    private CounterSemaphore selfShop;

    Customer(String name, CounterSemaphore selfShop) {
        this.name = name;
        this.selfShop = selfShop;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            selfShop.release();
            System.out.println(this.name + " do the shopping");
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            selfShop.acquire();
        }

    }
}
