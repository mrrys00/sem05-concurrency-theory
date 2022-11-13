# Raport Lab03

## Zad 1

**Przetwarzanie potokowe z buforem**

* Bufor o rozmiarze N - wspólny dla wszystkich procesow!
* Proces A będacy producentem.
* Proces Z będacy konsumentem.
* Procesy B, C, ..., Y będace procesami przetwarzajacymi. Każdy proces otrzymuje daną wejściową od procesu poprzedniego, jego wyjscie zas jest konsumowane przez proces następny.
* Procesy przetwarzają dane w miejscu, po czym przechodzą do kolejnej komórki bufora i znowu przetwarzają ją w miejscu.
* Procesy dzialają z różnymi prędkościami.

Zaimplementować rozwiązanie przetwarzania potokowego (Przykładowe załozenia: bufor rozmiaru 100, 1 producent, 1 konsument, 5 uszeregowanych procesów przetwarzających.) Od czego zależy prędkość obróbki w tym systemie ? Rozwiązanie za pomocą semaforów lub monitorów (dowolnie)

**Rozwiązanie** \
W rozwiązaniu skorzystam z mechanizmu monitorów. \
Dla wygody rozwiązania ustalmy, że brak wiadomości to `-1` a wiadomości są odpowiednio liczbami naturalnymi `0..n-1` gdzie `n - ilość uczestników łańcucha przetwarzania`

1. producent : -1 -> 0
2. proces 1 : 0 -> 1
3. proces 2 : 1 -> 2 \
…
4. konsument : n-1 -> -1

Dodatkowo scenariusz zakłada, że mamy jednego producenta, n-2 procesorów i jednego konsumenta, jednak budowa mechanizmu monitorów i możliwośś zainicjowania producentów, procesorów i konsumentów z różnymi oczekiwanymi wiadomościami wejściowymi i wyjściowymi nie zabrania użycia dowolnej większej liczby każdego z nich. Procesory także mogą się powielać, np. używamy dwóch procesorów zmieniających wiadomość 1->2 a pięciu procesorów 4->5.

W buforze przechowuję tyle warunków `Condition` ile jest uczestników procesu produkcyjnego. Dzięki temu mogę wywołać następny proces do działania (produkcji, przetwarzania lub konsumpcji wiadomości).

**Mechanizm wywoływania następnego procesu v1**
Pierwotna wersja wywoływania następnego procesu zakładała obudzenie tylko jednego procesu - kolejnego w tablicy procesów. Niestety okazało się, że lubi się zakleszczyć.

```java
conditions[nextIdx].signal();
```

**Mechanizm wywoływania następnego procesu v2**
W kolejnej wersji użyłem bardziej inteligentnego rozwiązania - spróbuj zawołać wszystkie procesy w kolejności - następny, następny itd … jednak okazało się to dość mało sprawiedliwe bo proces większość czasu był bezczynny i czekał na swoją kolej a później miał bardzo dużo pracy.

```java
    private void signaller(int nextIdx) {
        for ( int i = 0; i < this.M; i++) {
            conditions[(i+nextIdx)%this.M].signal();
        }
    }
```

**Mechanizm wywoływania następnego procesu v3**
W ostatniej - najbardziej sprawiedliwej wersji - odwróciłem kolejność wywoływania następnych procesów do działania dzięki czemu ich wywoływanie jest bardziej równomierne.

```java
    private void signaller(int nextIdx) {
        for ( int i = this.M-1; i > -1; i--) {
            conditions[(i+nextIdx)%this.M].signal();
        }
    }
```

*Uwagi*
Każdy z procesów działa tak samo - zmienia liczbę (wiadomość) w buforze na odpowiedni numer - dlatego wewnątrz bufora publiczne metody `put`, `process` i `take` są implementowane przez prywatną metodę `stateChanger`. \
Analogiczna sytuacja jest z klasami `Producer`, `Processor` oraz `Customer` jednak tam problemem technologicznym w Java okazało się dziedziczenie więc zrezygnowałem z implementowania tego przez klasę-rodzica.

## Zad 2