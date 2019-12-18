### Realisation of Go Game project

##### Designed for laboratory of Programming Technology 

---

Projekt gry go pozwala na na grę w dwóch trybach:
- Multilayer
- Single Player

W trybie multiplayer serwer paruje dwóch użytkowników, aby mogli grać razem, natomiast w trybie Single Player do rozgrywki dołączany jest bot. 
Mamy możliwość wyboru planszy 9x9, 13x13 lub 19x19.

W projekcie użyto kilku wzorców projektowych:
- Abstract Factory - do budowania i przechowywania rozgrywki w pamięci.
- State - wzorzec użyty do przełączania się pomiędzy kolejnymi etapami wejścia do rozgrywki. (Mamy ekran początkowy, oczekiwania na serwer, bo czym jeden z użytkowników ma prawo wyboru planszy, gdzie drugi przechodzi tymczasem do innego ekranu oczekiwania, oraz planszy rozgrywki).
- Decorator - do realizacji sprawdzania konkretnych zasad gry
- Facade - do kolekcji wszystkich funkcji realizujących podobną funkcjonalność (przeysłanie odpowiedzi do serwera)

Ponadto użyto algorytmów takich jak DFS i BFS do zliczania punktów, czy też realizacji bota. A ponadto struktur danych takich jak stos, lista, czy Union Find.

#### Import

Aby uruchomić projekt należy zaimportować plik przy użyciu Maven'a.