package Lab03.Zad3;

public class RestaurantClient extends Thread {
    private WaiterMonitor waiterMonitor;
    private String name;
    private Integer id;

    public RestaurantClient(WaiterMonitor waiterMonitor, String name, Integer id) {
        this.waiterMonitor = waiterMonitor;
        this.name = name;
        this.id = id;
    }

    public Integer getID() { return this.id; }

    public String getName() { return this.name; }

    private void eat() throws InterruptedException {
        System.out.println("Client " + this.id + " eats");
        sleep((int) (Math.random() * 1000));
    }

    @Override
        public void run() {
    
            while (true) {
                try {
                    sleep((int) (Math.random() * 1000));    // prepare task to print
                    WaiterMonitor.reserve(this);
                    this.eat();
                    WaiterMonitor.release(this);
                } catch (InterruptedException e) {
                }
            }

        }
}
