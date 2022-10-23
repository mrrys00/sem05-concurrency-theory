package Lab03.Zad2;

public class Client extends Thread {
        private String name;
        private PrinterMonitor printerMonitor;
    
        public Client(String name, PrinterMonitor printerMonitor) {
            this.name = name;
            this.printerMonitor = printerMonitor;
        }
    
        private void print(int printerID) throws InterruptedException {
            System.out.println("Client " + name + " prints on printer " + String.valueOf(printerID));
            sleep((int) (Math.random() * 1000));
        }

        @Override
        public void run() {
    
            while (true) {
                try {
                    sleep((int) (Math.random() * 1000));    // prepare task to print
                    int printerID = printerMonitor.reserve();
                    this.print(printerID);
                    printerMonitor.release(printerID);
                } catch (InterruptedException e) {
                }
            }

        }
}
