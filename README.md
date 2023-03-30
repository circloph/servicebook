Евгений Круглов

circloph@gmail.com

На иллюстрациях будет представлено два пользователя zheka(ROLE_ADMIN) и kostya(ROLE_ADMIN).

Начнем с домашней страницы http://localhost:3000/
На ней представлена обычная карусель, взятая из Vuetify
![homePage](https://user-images.githubusercontent.com/90142137/204250290-9dacb19a-c3c1-4e27-ac29-d126502f2c67.png)

Представляется два возможных действия: 

Регистрация http://localhost:3000/register

![register](https://user-images.githubusercontent.com/90142137/204250727-ff727698-2d9c-4e11-96e0-b170356e376a.png)

Логин http://localhost:3000/login

![login](https://user-images.githubusercontent.com/90142137/204250806-2385398d-ebb3-48d1-a229-6560f14fcddd.png)

На вкладку books перейти не выйдет, она перебрасывает на login.

Далее после логина, можно зайти на страницу с книгами 
http://localhost:3000/books

![listOfBooks](https://user-images.githubusercontent.com/90142137/204251153-6e8b5a85-5688-4d15-9f99-992af70c40f0.png)

На ней возможно искать книги путем добавление различных чекбоксов. 
Представляю пример с поиском книг по имени автора "Gogol":
![SearchByNameOfAuthor](https://user-images.githubusercontent.com/90142137/204251394-c8e379d9-75fb-4327-92b3-59e0754dc9ff.png)

Также, имеется возможность зайти на страницу автора, книги

http://localhost:3000/authors/1

![author](https://user-images.githubusercontent.com/90142137/204251523-06e2c660-6eb7-441c-8240-f9921ef0d99c.png)

http://localhost:3000/books/1

![profileBook](https://user-images.githubusercontent.com/90142137/204252015-98db94fd-531e-4012-8d48-50db163f9e18.png)

На странице книги, пользователи могут оставлять свои отзывы. Текст + оценка в виде звезд(1-5)

![addReview](https://user-images.githubusercontent.com/90142137/204252874-4b346234-6550-42a2-a5b8-9e0f0983bf7c.png)

Возможность зайти в свой профиль 
http://localhost:3000/users/2

![profileOfUser](https://user-images.githubusercontent.com/90142137/204253258-28e6b39a-bbad-4772-bd3b-00994807e75e.png)

Отличие админа от клиента в том, что админ способен добавлять книги и авторов.
Для него появляется в навбаре кнопка Constructor

http://localhost:3000/constructor

![constructor](https://user-images.githubusercontent.com/90142137/204252641-4126cf60-f8e8-49c8-a364-1ee9c2c518a5.png)










