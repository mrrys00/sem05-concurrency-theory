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
