package Lab04.Zad1;

public class Processor extends Thread{
    private Buffer buffer;
    private String name;

    public Processor(Buffer buffer, String string) {
        this.buffer = buffer;
        this.name = name;
    }
    // czy na message mogę sobie zrobić jakąś strukturę żeby było wiadomo który
    // proces może wziąć ją do przetwarzania?
    // np
    // String message
    // Integer processedBy
    // consumer może wziąć jeśli ostatni procesor przetworzy
}
