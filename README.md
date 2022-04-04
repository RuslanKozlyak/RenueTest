# RenueTest
Приложение, позволяющее искать введённую с клавиатуры строку в CSV файле.

Чтобы запустить приложение необходимо ввести команду:

java -jar Renue.jar <необязательный номер колонки для поиска>

В основе приложения лежит AVL дерево, в которое читается содержимое CSV файла.

Файл читается не полностью, а по батчам. Размер батча можно установить в методе main класса App.

Плюсы использования AVL дерева:
  - быстрый поиск за O(log(N))
  
Минусы:
  - затраты времени на построение дерева

Avl дерево было выбрано как оптимальный относительно других испробованных вариантов,
хотя вероятно не наилучший из всех возможных.
В других способах поиск проиходит быстрее так или иначе, но все они имеют минусы относительно Avl дерева

Други испробованные варианты:
  - барьерный метод для наивного поиска
      - не дает выигрыша при многих совпадениях с запросом
  - считывание в массив с последующей сортировкой и бинарным поиском
      - очень долгая сортировка перед поиском
   - использование бора вместо дерева
      - большие затраты памяти, особенно учитывая что в файле используется не только латинский алфавит

P.S. К сожалению я мало знаком со Spring и я не смог подключить application.yml как полагет.
Поэтому базовые настройки приложения содержатся в классе App.
   

