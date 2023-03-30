--liquibase formatted sql
--changeset kruglov:data

INSERT INTO roles (name) VALUES ('CLIENT');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO users (first_name, last_name, middle_name, login, password, role_id) VALUES ('Evgeniy', 'Kruglov', 'Alekseeivch', 'zheka', '$2a$10$TXPXIpSewmMCstGfu9KW8.Qn28UiYIwkQkInwI6xxYTeZLODmuwM2', 2);
INSERT INTO users (first_name, last_name, middle_name, login, password, role_id) VALUES ('Konstantin', 'Kruglov', 'Alekseeivch', 'kostya', '$2a$10$TXPXIpSewmMCstGfu9KW8.Qn28UiYIwkQkInwI6xxYTeZLODmuwM2', 1);
INSERT INTO users (first_name, last_name, middle_name, login, password, role_id) VALUES ('Timofey', 'Kruglov', 'Dmitrievich', 'tima', '$2a$10$TXPXIpSewmMCstGfu9KW8.Qn28UiYIwkQkInwI6xxYTeZLODmuwM2', 1);

INSERT INTO administration (user_id, position) VALUES (1, 'admin');
INSERT INTO clients (user_id, number_phone) VALUES (2, 'client');
INSERT INTO clients (user_id, number_phone) VALUES (3, 'client');

INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Aleksandr', 'Pushkin', 'Sergeevich', 'Alexander Sergeyevich Pushkin was a Russian poet, born on 6th of July, 1799. in Moscow. He is known as one of the best Russian poets and a father of Russian literature in general. At his early age, he is left in the care of peasants Nikita Kozlov and Arina Yakovleva.
Pushkin was a descendant of the old noble family by his fathers side while his mother was a granddaughter of Gannibal, famous Ethiopian prince who had a lot of influence on the emperor Peter the Great.
As a child, he was sent away to Lyceum near St. Petersburg. He begins writing his poems during his schooling, and after graduating, he was well known in poetic circles. At first, he was influenced with traditional classicism, but later he started writing on manners of romanticism. He was spending his time in the company of many intellectuals who made his creativity even stronger.
His most famous works are Mozart and Salieri, Weeding in the Time of Plague, Captains Daughter, Dubrovski, Gypsies, and his greatest accomplishment is a novel Evgenij Onegin.');

INSERT INTO books (description, genre, title, year_of_production) VALUES ('Pyotr Andreyich Grinyov (the narrative is conducted on his behalf) is the only surviving child of a retired army officer. When Pyotr turns 17, his father sends him into military service in Orenburg. En route Pyotr gets lost in a blizzard, but is rescued by a mysterious man. As a token of his gratitude, Pyotr gives the guide his hareskin coat.', 'Novel', 'The Captains Daughter', 1836);
INSERT INTO books_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (4, 'good', 1, 2);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (1, 'bad', 1, 3);


INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Nikolay', 'Gogol', 'Vasilevich', 'Nikolay Gogol, in full Nikolay Vasilyevich Gogol, (born March 19 [March 31, New Style], 1809, Sorochintsy, near Poltava, Ukraine, Russian Empire [now in Ukraine]—died February 21 [March 4], 1852, Moscow, Russia), Ukrainian-born humorist, dramatist, and novelist whose works, written in Russian, significantly influenced the direction of Russian literature.
 His novel Myortvye dushi (1842; Dead Souls) and his short story “Shinel” (1842; “The Overcoat”) are considered the foundations of the great 19th-century tradition of Russian realism.');
INSERT INTO books (description, genre, title, year_of_production) VALUES ('Dead Souls (Russian: «Мёртвые души», Mjórtvyje dúshi) is a novel by Nikolai Gogol, first published in 1842, and widely regarded as an exemplar of 19th-century Russian literature. The novel chronicles the travels and adventures of Pavel Ivanovich Chichikov (Russian: Павел Иванович Чичиков) and the people whom he encounters. These people typify the Russian middle aristocracy of the time. Gogol himself saw his work as an "epic poem in prose", and within the book characterised it as a "novel in verse". Gogol intended the novel to be the first part of a three-volume work, but burned the manuscript of the second part shortly before his death.[1][2] Although the novel ends in mid-sentence (like Sternes Sentimental Journey), it is regarded by some as complete in the extant form', 'Novel', 'Dead Souls', 1842);
INSERT INTO books (description, genre, title, year_of_production) VALUES ('"The Nose" is divided into three parts and tells the story of Collegiate Assessor (Major) Kovalyov, who wakes up one morning without his nose. He later finds out that his nose has developed a life of its own, and has apparently surpassed him by attaining the rank of State Councillor. The short story showcases the obsession with social rank that plagued Russia after Peter the Great introduced the Table of Ranks.[3] By allowing commoners to gain hereditary nobility through service to the state, a huge population was given the chance to move up in social status. This opportunity, however, also gave way to large bureaucracies, in which many of Gogols characters worked.', 'Short story', 'The Nose', 1836);
INSERT INTO books (description, genre, title, year_of_production) VALUES ('"The Portrait" is the story of a young and penniless artist, Andrey Petrovich Chartkov, who stumbles upon a terrifyingly lifelike portrait in an art shop and is compelled to buy it. The painting is magical and offers him a dilemma — to struggle to make his own way in the world on the basis of his own talents or to accept the assistance of the magic painting to guaranteed riches and fame. He chooses to become rich and famous, but when he comes upon a portrait from another artist which is "pure, faultless, beautiful as a bride" [1] he comes to realize that he has made the wrong choice. Eventually, he falls ill and dies from a fever.', 'Short story', 'The Portrait', 1835);
INSERT INTO books_authors (book_id, author_id) VALUES (2, 2);
INSERT INTO books_authors (book_id, author_id) VALUES (3, 2);
INSERT INTO books_authors (book_id, author_id) VALUES (4, 2);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (4, 'good', 2, 2);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (1, 'bad', 3, 2);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (2, 'I like', 4, 2);


INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Ivan', 'Krilov', 'Andreevich', 'Aleksei Krylov was a Russian applied mathematician who worked in many areas including shipbuilding, magnetism, artillery, mathematics, astronomy, and geodesy.');
INSERT INTO books (description, genre, title, year_of_production) VALUES ('Moral of the fable The Monkey and the Glasses The monkey in the fable acts as an ignoramus, and the glasses are knowledge. But you need to be able to apply the knowledge correctly, then they will be useful. The fable mocks short-sightedness and ignorance. Here it is shown that the "ignoramuses of knowledge" are especially dangerous for society, who, by their influence, can harm the progress in science.', 'fable', 'monkey and glasses', 1815);
INSERT INTO books_authors (book_id, author_id) VALUES (5, 3);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (5, 'I like', 5, 2);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (2, 'very bad', 5, 3);


INSERT INTO books (description, genre, title, year_of_production) VALUES ('The authors use concrete examples to show what is good and what is bad in informational, advertising, journalistic and journalistic texts. How to write letters that will be answered and mailing lists that will not be unsubscribed. How to create effective and not vulgar advertisements. How to express thoughts briefly, clearly and convincingly: without language garbage, falsehood and clichés. By following the guidelines in the book, you will learn how to write clearly, engage readers, and build trust. This is a must-have book for copywriters, authors, and editors, as well as designers, programmers, managers, entrepreneurs, executives, employees, and anyone who uses text in their work.', 'marketing', 'pishi, sokrashay', 2016);
INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Maksim', 'Ilyahov', 'Sergeevich', 'Maxim Ilyakhov is a contemporary Russian writer, designer, editor, candidate of pedagogical sciences, rector of the Gorbunov Design Bureau School of Editors, developer of the Glavred text verification service.');
INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Ludmila', 'Saricheva', 'Anatolevna', 'Lyudmila Sarycheva is a contemporary Russian writer, editor and teacher at the Artem Gorbunov Design Bureau School of Editors.');
INSERT INTO books_authors (book_id, author_id) VALUES (6, 4);
INSERT INTO books_authors (book_id, author_id) VALUES (6, 5);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (5, 'Cool', 6, 2);


INSERT INTO books (description, genre, title, year_of_production) VALUES ('The Gambler (Russian: Игрокъ, romanized: Igrok; modern spelling Игрок) is a short novel by Fyodor Dostoevsky about a young tutor in the employment of a formerly wealthy Russian general. The novella reflects Dostoevskys own addiction to roulette, which was in more ways than one the inspiration for the book: Dostoevsky completed the novella in 1866 under a strict deadline to pay off gambling debts', 'Novel', 'The Gambler', 1886);
INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Fedor', 'Dostoevskiy', 'Mihaylovich', 'Fyodor Dostoyevsky, in full Fyodor Mikhaylovich Dostoyevsky, Dostoyevsky also spelled Dostoevsky, (born November 11 [October 30, Old Style], 1821, Moscow, Russia—died February 9 [January 28, Old Style], 1881, St. Petersburg), Russian novelist and short-story writer whose psychological penetration into the darkest recesses of the human heart, together with his unsurpassed moments of illumination, had an immense influence on 20th-century fiction.');
INSERT INTO books_authors (book_id, author_id) VALUES (7, 6);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (5, 'Cool', 7, 3);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (5, 'Super', 7, 2);

INSERT INTO books (description, genre, title, year_of_production) VALUES ('Dark Avenues (or Dark Alleys, Russian: Тёмные аллеи, romanized: Tyomnyie alleyi) is a collection of short stories by Nobel Prize-winning Russian author Ivan Bunin. Written in 1937–1944, mostly in Grasse, France, the first eleven stories were published in New York City, United States, in 1943. The books full version (27 stories added to the first 11) came out in 1946 in Paris. Dark Alleys, "the only book in the history of Russian literature devoted entirely to the concept of love," is regarded in Russia as Bunins masterpiece.[1] These stories are characterised by dark, erotic liaisons and love affairs that are, according to James B. Woodward, marked by a contradiction that emerges from the interaction of a love that is enamoured in sensory experiences and physicality, with a love that is a supreme, if ephemeral, "dissolution of the self.', 'Novel', 'Dark Avenues', 1946);
INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Bunin', 'Ivan', 'Alekeseevich', 'Ivan Bunin, in full Ivan Alekseyevich Bunin, (born October 10 [October 22, New Style], 1870, Voronezh, Russia—died November 8, 1953, Paris, France), poet and novelist, the first Russian to receive the Nobel Prize for Literature (1933), and one of the finest of Russian stylists.');
INSERT INTO books_authors (book_id, author_id) VALUES (8, 7);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (5, 'The best', 8, 3);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (1, 'crap', 8, 2);

INSERT INTO books (description, genre, title, year_of_production) VALUES ('The exact date of the poems completion, according to the autograph, was August 5, 1839. According to the same manuscript, Mtsyri was called originally Beri (A Monk, in Georgian) and featured an epigraph (On na quune seule patria, There is only one fatherland) which later had been crossed out by the author. It was this hand-written document that allowed researchers decades later to return into the poem two fragments cut out by censors. Those were the two lines from Verse 8 (...To learn if it if for freedom or for prison that we are born into this world) and seven lines from the penultimate Verse 25 (...But whats in it for me? Should my spirit finds itself in Paradise, this saintly heavenly place, Id throw away both Paradise and the Eternity // For just one chance to spend several minutes among those dark steep rock where I used to play as a child', 'Novel', 'Mciri', 1840);
INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Mihail', 'Lermontov', 'Urevich', 'Mikhail Lermontov, in full Mikhail Yuryevich Lermontov, (born October 15 [October 3, Old Style], 1814, Moscow, Russia—died July 27 [July 15], 1841, Pyatigorsk), the leading Russian Romantic poet and author of the novel Geroy nashego vremeni (1840; A Hero of Our Time), which was to have a profound influence on later Russian writers.');
INSERT INTO books_authors (book_id, author_id) VALUES (9, 8);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (5, 'easy', 9, 3);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (1, 'crap', 9, 2);

INSERT INTO books (description, genre, title, year_of_production) VALUES ('Zheltkov, a little man, is possessed by a consuming love for Princess Vera Sheina, a rich society lady hardly aware of his existence. After his wife receives for her birthday a particularly expensive gift, Prince Vasily Shein visits Zheltkov, accompanied by Nikolai Nikolayevich, Vera s brother, and tells him to stop pestering the Princess. Being aware that the last thread connecting him with his beloved one is gone, Zheltkov commits suicide, in the final letter asking Vera to listen to Beethovens Second Piano Sonata. The power of music brings about the spiritual rebirth of a hitherto detached aristocrat who recognizes that she s just been brushed by this rarest thing, a true love that happens once in a thousand years.', 'story', '
Garnet bracelet', 1911);
INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Aleksandr', 'Kuprin', 'Ivanovich', 'Aleksandr Ivanovich Kuprin, (born Sept. 7 [Aug. 26, old style], 1870, Narovchat, Russia—died Aug. 25, 1938, Leningrad), Russian novelist and short-story writer, one of the last exponents of the great tradition of Russian critical realism.');
INSERT INTO books_authors (book_id, author_id) VALUES (10, 9);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (4, 'good', 10, 3);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (3, 'good', 10, 2);

INSERT INTO books (description, genre, title, year_of_production) VALUES ('Written with the same overwhelming simplicity and directness that made All Quiet on the Western Front a classic, Three Comrades portrays the greatness of the human spirit, manifested through characters who must find the inner resources to live in a world they did not make, but must endure.', 'novel', '
Three comrades', 1936);
INSERT INTO authors (first_name, last_name, middle_name, short_biography) VALUES ('Erich', 'Remarque', 'Maria', 'Erich Maria Remarque was born Erich Paul Remark on June 22, 1898 in Osnabrück, Germany. He received an education in private Catholic schools and subsequently enrolled in a training school for teachers, which he attended until he was conscripted into the German army. At this time, he also began writing fiction');
INSERT INTO books_authors (book_id, author_id) VALUES (11, 10);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (5, 'excellent', 11, 3);
INSERT INTO reviews (mark, text, book_id, user_id) VALUES (5, 'excellent', 11, 2);
