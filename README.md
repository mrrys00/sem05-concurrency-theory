# Concurrency theory

## Zadania

### Lab01

[Strona laborek](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab1/)

Zadanie

1. Napisac program BEZ SYNCHRONIZACJI, w ktorym mamy obiekt klasy Counter przechowywujący pewną zmienną całkowitą oraz dwie metody inkrementującą i dekrementującą.
2. Nastepnie jeden watek wywoluje na tym obiekcie metode inkrementująca 100000000 razy, drugi dekrementująca 100000000 razy. Czy wynik zawsze jest zero? Sprawdzić działanie na różnych systemach. (1 pkt)

3. Wprowadzić synchronizację do programu wykorzystujac slowo kluczowe "synchronized" (1 pkt)
4. Mamy klika procesów produkujacych wiadomosci (szkielet kodu) i kilka konsumujacych wiadomosci (szkielet kodu) do/z jednoelementowego bufora. Zadaniem jest napisanie klasy Buffer z metodami put i take, tak, aby dostep byl synchronizowany uzywajac monitora Javy dla obiektu klasy Buffer. Kazda wiadomosc jest produkowana przez jednego producenta i konsumowana przez jednego, dowolnego konsumenta. (1 pkt)
5. Wyjasnij, dlaczego przy sprawdzaniu warunku czy bufor jest pusty/pelny nalezy uzyc instrukcji while , a nie wystarczy instrukcja if .

### Lab02

[Strona laborek](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab2/)

Zadanie

1. zaimplementowac semafor binarny za pomoca metod wait i notify/notifyall, uzyc go do synchronizacji wyscigu z poprzedniego laboratorium.
2. zaimplementowac semafor licznikowy (ogolny) za pomoca metod wait i notify/notifyall. Przetestowac semafor na prostej symulacji sklepu samoobsługowego z ograniczoną ilością koszyków.

### Lab03

[Strona laborek](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab3/)

Zadanie

1. Przeanalizuj i przetestuj na swoich producentach i konsumentach monitor BoundedBuffer przeznaczony dla producentow i konsumentow.

2. Drukarki \
Grupa wątków P (ilosc N) korzysta z M drukarek N>M. Dzialanie wątku

    ```
    forever{
      Utworz_zadanie_do_druku();
      nr_drukarki=Monitor_Drukarek.zarezerwuj();
      drukuj(nr_drukarki);
      Monitor_Drukarek.zwolnij(nr_drukarki);
    }
    ```

    Napisz monitor Monitor_Drukarek.

3. Stolik dwuosobowy \
Napisz monitor Kelner sterujacy dostepem do stolika dwuosobowego. Ze stolika korzysta N par osob. Algorytm osoby z pary o numerze j:

    ```
    forever{
      wlasne sprawy;
      Kelner.chce_stolik(j);
      jedzenie;
      Kelner.zwalniam();
    }
    ```

    Stolik jest przydzielany parze w momencie gdy obydwie osoby tego zazadaja, zwalnianie nie musi byc jednoczesne.

### Lab04

[Strona laborek](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab4/)

*Założenia* \
Należy rozwiązać oba zadania za pomocą semaforów lub monitorów (do wyboru) dostępnych w języku Java. Można korzystać z:

* mechanizmu monitorów synchronized, wait, notify
* mechanizmu lock, await, signal
* mechanizmu semaforow własnych bądz z pakietu java.util concurrent
* W implementacji nie jest dozwolone korzystanie/implementowanie własnych kolejek FIFO, należy używac tylko mechanizmu monitorow lub semaforow !


Zadania

1. Przetwarzanie potokowe z buforem
    * Bufor o rozmiarze N - wspólny dla wszystkich procesow!
    * Proces A będacy producentem.
    * Proces Z będacy konsumentem.
    * Procesy B, C, ..., Y będace procesami przetwarzajacymi. Każdy proces otrzymuje daną wejściową od procesu poprzedniego, jego wyjscie zas jest konsumowane przez proces następny.
    * Procesy przetwarzają dane w miejscu, po czym przechodzą do kolejnej komórki bufora i znowu przetwarzają ją w miejscu.
    * Procesy dzialają z różnymi prędkościami.

    Zaimplementować rozwiązanie przetwarzania potokowego (Przykładowe załozenia: bufor rozmiaru 100, 1 producent, 1 konsument, 5 uszeregowanych procesów przetwarzających.) Od czego zależy prędkość obróbki w tym systemie ? Rozwiązanie za pomocą semaforów lub monitorów (dowolnie)

2. Producenci i konsumenci z losową iloscią pobieranych i wstawianych porcji
    * Bufor moze pomiescic 2M nierozróżnialnych elementow (kolejnosc nie istotna)
    * Jest wielu producentów i konsumentów
    * Producent wstawia do bufora losową liczbę elementów (nie wiecej niz M)
    * Konsument pobiera losową liczbę elementów (nie wiecej niz M)
    Wyjasnić po co zalozenie o M.

    Zaimplementować rozwiązanie z losową liczbą porcji w dwóch wariantach:

    * Wariant naiwny: producent / konsument jest wstrzymywany aż w buforze nie będzie wystarczająco dużo miejsca / elementów.
    * Wariant sprawiedliwy: zapobiega zagłodzeniu procesów produkujących lub konsumujących duże porcje. Wymyślić własne (NIE używać prorytetów!), bądź użyć rozwiązania według [1]
    Proszę uruchomić obydwa warianty algorytmu dla wielu producentów i konsumentów, a następnie zmierzyć i przedstawić na wykresie porównawczym (osobno dla producentów i konsumentów) średni czas oczekiwania na dostęp do bufora w zależności od wielkości porcji (czas metod put() i get()).

*Uwagi*

* Producenci i konsumenci losują wielkość porcji przed każdą operacją.
* Każda wielkość porcji jest losowana z równym prawdopodobieństwem;
* Do pomiaru czasu proszę używać System.nanoTime()
* Przykladowy zestaw testow: M rowne 1000, 10k, 100k Konfiguracji P-K: 10P+10K, 100P+100K, 1000P+1000K

### Lab05

[Strona laborek](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab5/)

Zadania

1. Proszę zaimplementować przy użyciu Executor i Future program wykonujący obliczanie zbioru Mandelbrota w puli wątków. Jako podstawę implementacji proszę wykorzystać [kod w Javie](https://rosettacode.org/wiki/Mandelbrot_set#Java).

    ```java
    import java.awt.Graphics;
    import java.awt.image.BufferedImage;
    import javax.swing.JFrame;
    
    public class Mandelbrot extends JFrame {
    
        private final int MAX_ITER = 570;
        private final double ZOOM = 150;
        private BufferedImage I;
        private double zx, zy, cX, cY, tmp;
    
        public Mandelbrot() {
            super("Mandelbrot Set");
            setBounds(100, 100, 800, 600);
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < getHeight(); y++) {
                for (int x = 0; x < getWidth(); x++) {
                    zx = zy = 0;
                    cX = (x - 400) / ZOOM;
                    cY = (y - 300) / ZOOM;
                    int iter = MAX_ITER;
                    while (zx * zx + zy * zy < 4 && iter > 0) {
                        tmp = zx * zx - zy * zy + cX;
                        zy = 2.0 * zx * zy + cY;
                        zx = tmp;
                        iter--;
                    }
                    I.setRGB(x, y, iter | (iter << 8));
                }
            }
        }
    
        @Override
        public void paint(Graphics g) {
            g.drawImage(I, 0, 0, this);
        }
    
        public static void main(String[] args) {
            new Mandelbrot().setVisible(true);
        }
    }
    ```

2. Proszę przetestować szybkość działania programu w zależności od liczby wątków w pul:
    * jeden wątek
    * tyle samo watkow co rdzeni
    * dwa razy wiecej wątkow niz rdzeni
    oraz stosunku liczby zadań do liczby watkow w puli:
    * tyle samo zadan co wątków,
    * 10x wiecej zadan co wątków,
    * każde zadanie to jeden piksel.

    Czas obliczeń można zwiększać manipulując parametrami problemu, np. liczbą iteracji (MAX_ITER). Czas należy zmierzyć 10 razy dla tego samego przypadku, policzyć średnią i odchylenie standardowe. Wyniki przedstawic w tabelce, znaleźć najszybszą konfigurację.

### Lab06

[Strona laborek](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/)

Zadania

1. Uruchom kilkakrotnie następujący [program](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/node1.js).
2. Wymuszanie sekwencyjnego wykonania:
    * [Rozwiazanie 1](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/node1a.js)
    * [Rozwiazanie 2](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/node1b.js)
3. Zadanie 1a: Zaimplementuj funkcje loop, wg instrukcji w pliku z Rozwiazaniem 2.
4. Zadanie 1b: wykorzystaj funkcję [waterfall](https://caolan.github.io/async/v3/docs.html#waterfall) biblioteki [async](https://github.com/caolan/async).
5. Zadanie 2 Zaimplementuj funkcję inparallel wg instrukcji z pliku [node2.js](https://home.agh.edu.pl/~kzajac/dydakt/tw/lab6/node2.js)
