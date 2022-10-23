# Raport Lab03

## Zad 1

Przeanalizuj i przetestuj na swoich producentach i konsumentach monitor [BoundedBuffer](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html) przeznaczony dla producentow i konsumentow.

**Rozwiązanie:**
BoundedBuffer jest buforem więcej niż jednoelementowym w poprzednim rozwiązaniu problemu producentów i konsumentów. W swoim rozwiązaniu skorzystałem ze skopiowanego z [tej strony](https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html) BoundedBuffera. Klasy *Producer* i *Conusumer* (odpowiednio dla potrzeb Lab03 *ProducerV2* i *ConsumerV2*) zostały praktycznie nie zmienione. \

Przykład odpalenia programu dla 5 producentów i 4 konsumentów: \
```
Producer Producer0 produced message Message no. 0 from Producer0
Producer Producer4 produced message Message no. 0 from Producer4
Producer Producer3 produced message Message no. 0 from Producer3
Producer Producer2 produced message Message no. 0 from Producer2
Producer Producer1 produced message Message no. 0 from Producer1
Consumer Customer1 consumed message Message no. 0 from Producer1
Consumer Customer0 consumed message Message no. 0 from Producer0
Consumer Customer2 consumed message Message no. 0 from Producer2
Consumer Customer3 consumed message Message no. 0 from Producer3
Producer Producer2 produced message Message no. 1 from Producer2
Producer Producer2 produced message Message no. 2 from Producer2
Producer Producer3 produced message Message no. 1 from Producer3
Producer Producer1 produced message Message no. 1 from Producer1
Consumer Customer3 consumed message Message no. 0 from Producer4
Producer Producer4 produced message Message no. 1 from Producer4
Producer Producer1 produced message Message no. 2 from Producer1
Producer Producer0 produced message Message no. 1 from Producer0
Producer Producer4 produced message Message no. 2 from Producer4
Consumer Customer0 consumed message Message no. 1 from Producer2
Consumer Customer3 consumed message Message no. 2 from Producer2
Producer Producer2 produced message Message no. 3 from Producer2
Producer Producer2 produced message Message no. 4 from Producer2
Consumer Customer1 consumed message Message no. 1 from Producer3
Producer Producer4 produced message Message no. 3 from Producer4
Consumer Customer2 consumed message Message no. 1 from Producer1
Producer Producer3 produced message Message no. 2 from Producer3
Producer Producer4 produced message Message no. 4 from Producer4
Producer Producer4 produced message Message no. 5 from Producer4
Consumer Customer0 consumed message Message no. 1 from Producer4
Consumer Customer2 consumed message Message no. 2 from Producer1
Producer Producer4 produced message Message no. 6 from Producer4
Producer Producer1 produced message Message no. 3 from Producer1
Producer Producer2 produced message Message no. 5 from Producer2
Producer Producer0 produced message Message no. 2 from Producer0
Consumer Customer3 consumed message Message no. 1 from Producer0
Consumer Customer0 consumed message Message no. 2 from Producer4
Producer Producer2 produced message Message no. 6 from Producer2
Producer Producer4 produced message Message no. 7 from Producer4
Consumer Customer1 consumed message Message no. 3 from Producer2
Producer Producer2 produced message Message no. 7 from Producer2
Consumer Customer2 consumed message Message no. 4 from Producer2
Producer Producer3 produced message Message no. 3 from Producer3
Consumer Customer0 consumed message Message no. 3 from Producer4
Producer Producer1 produced message Message no. 4 from Producer1
Producer Producer0 produced message Message no. 3 from Producer0
Consumer Customer0 consumed message Message no. 2 from Producer3
Producer Producer1 produced message Message no. 5 from Producer1
Consumer Customer3 consumed message Message no. 4 from Producer4
Consumer Customer1 consumed message Message no. 5 from Producer4
Producer Producer3 produced message Message no. 4 from Producer3
Producer Producer0 produced message Message no. 4 from Producer0
Consumer Customer2 consumed message Message no. 6 from Producer4
Producer Producer4 produced message Message no. 8 from Producer4
Producer Producer2 produced message Message no. 8 from Producer2
Producer Producer1 produced message Message no. 6 from Producer1
Producer Producer0 produced message Message no. 5 from Producer0
Producer Producer4 produced message Message no. 9 from Producer4
Consumer Customer0 consumed message Message no. 3 from Producer1
Producer Producer4 produced message Message no. 10 from Producer4
Producer Producer3 produced message Message no. 5 from Producer3
Consumer Customer3 consumed message Message no. 5 from Producer2
Consumer Customer1 consumed message Message no. 2 from Producer0
Consumer Customer0 consumed message Message no. 6 from Producer2
Producer Producer4 produced message Message no. 11 from Producer4
Producer Producer3 produced message Message no. 6 from Producer3
Consumer Customer3 consumed message Message no. 7 from Producer4
Producer Producer2 produced message Message no. 9 from Producer2
Producer Producer1 produced message Message no. 7 from Producer1
Consumer Customer3 consumed message Message no. 7 from Producer2
Producer Producer4 produced message Message no. 12 from Producer4
Consumer Customer2 consumed message Message no. 3 from Producer3
Producer Producer1 produced message Message no. 8 from Producer1
Consumer Customer1 consumed message Message no. 4 from Producer1
Producer Producer0 produced message Message no. 6 from Producer0
Consumer Customer0 consumed message Message no. 3 from Producer0
Producer Producer3 produced message Message no. 7 from Producer3
Producer Producer2 produced message Message no. 10 from Producer2
Consumer Customer2 consumed message Message no. 5 from Producer1
Consumer Customer3 consumed message Message no. 4 from Producer3
Consumer Customer2 consumed message Message no. 4 from Producer0
Producer Producer0 produced message Message no. 7 from Producer0
Consumer Customer0 consumed message Message no. 8 from Producer4
Consumer Customer1 consumed message Message no. 8 from Producer2
Consumer Customer1 consumed message Message no. 6 from Producer1
Producer Producer1 produced message Message no. 9 from Producer1
Producer Producer4 produced message Message no. 13 from Producer4
Producer Producer3 produced message Message no. 8 from Producer3
Producer Producer2 produced message Message no. 11 from Producer2
Producer Producer3 produced message Message no. 9 from Producer3
Consumer Customer1 consumed message Message no. 5 from Producer0
Consumer Customer0 consumed message Message no. 9 from Producer4
Consumer Customer3 consumed message Message no. 10 from Producer4
Producer Producer0 produced message Message no. 8 from Producer0
Consumer Customer2 consumed message Message no. 5 from Producer3
Producer Producer0 produced message Message no. 9 from Producer0
Producer Producer4 produced message Message no. 14 from Producer4
Consumer Customer1 consumed message Message no. 11 from Producer4
Consumer Customer0 consumed message Message no. 6 from Producer3
Producer Producer4 produced message Message no. 15 from Producer4
Producer Producer3 produced message Message no. 10 from Producer3
Producer Producer2 produced message Message no. 12 from Producer2
Producer Producer2 produced message Message no. 13 from Producer2
Producer Producer1 produced message Message no. 10 from Producer1
Producer Producer1 produced message Message no. 11 from Producer1
Producer Producer0 produced message Message no. 10 from Producer0
Producer Producer3 produced message Message no. 11 from Producer3
Consumer Customer3 consumed message Message no. 9 from Producer2
Producer Producer2 produced message Message no. 14 from Producer2
Consumer Customer1 consumed message Message no. 7 from Producer1
Producer Producer4 produced message Message no. 16 from Producer4
Consumer Customer0 consumed message Message no. 12 from Producer4
Producer Producer3 produced message Message no. 12 from Producer3
Producer Producer3 produced message Message no. 13 from Producer3
Consumer Customer3 consumed message Message no. 8 from Producer1
Consumer Customer2 consumed message Message no. 6 from Producer0
Producer Producer0 produced message Message no. 11 from Producer0
Producer Producer3 produced message Message no. 14 from Producer3
Producer Producer1 produced message Message no. 12 from Producer1
Consumer Customer2 consumed message Message no. 7 from Producer3
Producer Producer1 produced message Message no. 13 from Producer1
Consumer Customer0 consumed message Message no. 10 from Producer2
Consumer Customer1 consumed message Message no. 7 from Producer0
Consumer Customer1 consumed message Message no. 9 from Producer1
Producer Producer1 produced message Message no. 14 from Producer1
Producer Producer2 produced message Message no. 15 from Producer2
Producer Producer3 produced message Message no. 15 from Producer3
Producer Producer4 produced message Message no. 17 from Producer4
Consumer Customer3 consumed message Message no. 13 from Producer4
Consumer Customer2 consumed message Message no. 8 from Producer3
Producer Producer2 produced message Message no. 16 from Producer2
Producer Producer1 produced message Message no. 15 from Producer1
Producer Producer2 produced message Message no. 17 from Producer2
Producer Producer0 produced message Message no. 12 from Producer0
Consumer Customer3 consumed message Message no. 11 from Producer2
Producer Producer4 produced message Message no. 18 from Producer4
Consumer Customer0 consumed message Message no. 9 from Producer3
Producer Producer2 produced message Message no. 18 from Producer2
Producer Producer4 produced message Message no. 19 from Producer4
Consumer Customer3 consumed message Message no. 8 from Producer0
Consumer Customer1 consumed message Message no. 9 from Producer0
Producer Producer4 produced message Message no. 20 from Producer4
Consumer Customer0 consumed message Message no. 14 from Producer4
Producer Producer2 produced message Message no. 19 from Producer2
```

## Zad 2.

Drukarki
Grupa wątków P (ilosc N) korzysta z M drukarek N>M. Dzialanie wątku

```
forever {
  Utworz_zadanie_do_druku();
  nr_drukarki=Monitor_Drukarek.zarezerwuj();
  drukuj(nr_drukarki);
  Monitor_Drukarek.zwolnij(nr_drukarki);
}
```

Napisz monitor *Monitor_Drukarek*.

**Rozwiązanie:**
W tym zadaniu (inaczej niż w poprzednim) na wejściu otrzymujemy pełny bufor z którego najpierw odbieramy (zarezerwuj drukarkę) a później oddajemy (zwolinij drukarkę). Wymaga to zmiany poprzedniego BoundedBuffera czyli zainicjowanie potrzebnej ilości drukarek przed rozpoczęciem drukowania. Dodatkowo, dla większej elastyczności mojego rozwiązania skorzystałem także z dynamicznej alokacji pamięci. \

```java
private List<Integer> printers = new ArrayList<>();    
private int M;

public PrinterMonitor(int M) {
    this.M = M;
    for (int i=0; i<M; i++) {
        printers.add(i);
    }
}
```

Trzeba było także zmienić kolejność wywołania metod dodawania i zdejmowania z bufora - musimy zaczynać od zdejmowania z bufora.

```java
int printerID = printerMonitor.reserve();   // zdejmij z bufora
this.print(printerID);
printerMonitor.release(printerID);          // dodaj spowrotem na bufor
```

Przykład dla 53 *druakrzy* oraz 18 *drukarek*:
```
Client Client44 prints on printer 17
Client Client21 prints on printer 16
Client Client24 prints on printer 15
Client Client49 prints on printer 14
Client Client34 prints on printer 13
Client Client6 prints on printer 12
Client Client25 prints on printer 11
Client Client2 prints on printer 10
Client Client15 prints on printer 9
Client Client40 prints on printer 8
Client Client9 prints on printer 7
Client Client20 prints on printer 6
Client Client38 prints on printer 5
Client Client37 prints on printer 4
Client Client7 prints on printer 3
Client Client32 prints on printer 2
Client Client28 prints on printer 2
Client Client11 prints on printer 13
Client Client41 prints on printer 10
Client Client30 prints on printer 1
Client Client27 prints on printer 4
Client Client23 prints on printer 17
Client Client43 prints on printer 0
Client Client18 prints on printer 17
Client Client39 prints on printer 9
Client Client29 prints on printer 7
Client Client3 prints on printer 2
Client Client46 prints on printer 12
Client Client13 prints on printer 11
Client Client36 prints on printer 4
Client Client4 prints on printer 15
Client Client2 prints on printer 2
Client Client10 prints on printer 5
Client Client44 prints on printer 4
Client Client5 prints on printer 12
Client Client14 prints on printer 9
Client Client45 prints on printer 16
Client Client50 prints on printer 16
Client Client19 prints on printer 6
Client Client42 prints on printer 10
Client Client37 prints on printer 3
```

**Problemy:**
Podczas rozwiązywania zadania niestety początkowo zapomniałem o dość kluczowej kwestii jaką jest tworzenie zadania do druku. \
W kodzie:

```java
sleep((int) (Math.random() * 1000));
```

Konsekwencją tego było zajmowanie wciaż jednej drukarki przez wątek "drukarza", który dopiero co tą drukarkę zwolnił. \

Przykład niepoprawnego wykonywania:

```
Client Client0 prints on printer 12
Client Client3 prints on printer 9
Client Client2 prints on printer 10
Client Client4 prints on printer 8
Client Client5 prints on printer 7
Client Client1 prints on printer 11
Client Client6 prints on printer 6
Client Client7 prints on printer 5
Client Client8 prints on printer 4
Client Client9 prints on printer 3
Client Client10 prints on printer 2
Client Client12 prints on printer 0
Client Client11 prints on printer 1
Client Client4 prints on printer 8
```

## Zad 3.


