package Lab03.Zad3;

public class RestaurantClient extends Thread {
    private WaiterMonitor waiterMonitor;
    private String name;
    private int id;

    // https://www.baeldung.com/java-dining-philoshophers
    
    public RestaurantClient(WaiterMonitor waiterMonitor, String name, int id) {
        this.waiterMonitor = waiterMonitor;
        this.name = name;
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public String getClientName() {
        return this.name;
    }

    private void eat() throws InterruptedException {
        System.out.println("Client " + this.id + " eats");
        sleep((int) (Math.random() * 1000));
    }

    @Override
    public void run() {

        while (true) {
            try {
                sleep((int) (Math.random() * 1000)); // wlasne sprawy
                this.waiterMonitor.reserve(this);
                this.eat();
                this.waiterMonitor.release(this);
            } catch (InterruptedException e) {
            }
        }

    }
}
