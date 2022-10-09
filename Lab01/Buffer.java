public class Buffer {
    private String message;

    Buffer() {
        this.message = "";
    }

    public synchronized void put(String message) {
        while (!this.message.equals(new String(""))) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        this.message = message;
        notifyAll();
    }

    public synchronized String take() {
        while (this.message.equals(new String(""))) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        String tempMessage = new String(this.message);
        this.message = "";
        notifyAll();

        return tempMessage;
    }
}
