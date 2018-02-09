-- insert some fake data
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Нет повести печальнее на свете - чем повесть о заклинившем RESET'e", "fidonet");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("В этой эхе присутствуют люди, которым удалось-таки самоубийство?", "fidonet");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("C наступающим Вас, господа, Голым Hодом!", "fidonet");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Программирование на С похоже на быстрые танцы на только что отполированном полу людей с острыми бритвами в руках.", "Waldi Ravens");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Не волнуйтесь, если что-то не работает. Если бы всё работало, вас бы уволили.", "Mosher’s Law of Software Engineering");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("В теории, теория и практика неразделимы. На практике это не так.", "Yoggi Berra");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Perl — это тот язык, который одинаково выглядит как до, так и после RSA шифрования.", "Keith Bostic");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Измерять продуктивность программиста подсчетом строк кода — это так же, как оценивать постройку самолета по его весу.", "Bill Gates");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Всегда пишите код так, будто сопровождать его будет склонный к насилию психопат, который знает, где вы живете.", "Martin Golding");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Java — это C++, из которого убрали все пистолеты, ножи и дубинки.", "James Gosling");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Если бы в Java действительно работала сборка мусора, большинство программ бы удаляли сами себя при первом же запуске.", "Robert Sewell");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Тестирование не позволяет обнаружить такие ошибки, как создание не того приложения.", "Steve McConnell");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Некоторые люди во время решения некой проблемы думают: «Почему бы мне не использовать регулярные выражения?». После этого у них уже две проблемы…", "Jamie Zawinski");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Ходить по воде и разрабатывать программы, следуя спецификации, очень просто… если они заморожены.", "Edward V Berard");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("Трудность работы с программистом заключается в том, что вы не можете понять, что он делает, до тех пор, пока не стало слишком поздно.", "Seymour Cray");
INSERT INTO `aphorism` (`text`, `copyright`) VALUES ("UNIX невероятно прост, но нужно быть гением, чтобы понять эту простоту.", "Dennis Ritchie");

-- insert env data
INSERT INTO `envelopment_values` (`name`, `val`) VALUES ("title", "Блог ниочем");
INSERT INTO `envelopment_values` (`name`, `val`) VALUES ("short-description", "Because we can...");

-- insert flickr
INSERT INTO `flickr_account` (`id`, `app_key`, `app_secret`, `user_id`) VALUES (1, "6ab1c24194d653d741906c4d8113b5f6", "8324d51ded03b3f0", "122537468@N08");
INSERT INTO `flickr_photo_set` (`account_id`, `album_id`, `description`) VALUES (1, "72157690834602976", "Коммандировка в Alibaba");
INSERT INTO `flickr_photo_set` (`account_id`, `album_id`, `description`) VALUES (1, "72157683656405192", "Скотинка");
INSERT INTO `flickr_photo_set` (`account_id`, `album_id`, `description`) VALUES (1, "72157682459400482", "Муйне. Лето 2017г.");
INSERT INTO `flickr_photo_set` (`account_id`, `album_id`, `description`) VALUES (1, "72157685478718635", "Далат. Весна 2017г.");
INSERT INTO `flickr_photo_set` (`account_id`, `album_id`, `description`) VALUES (1, "72157685474331695", "Боракай. Весна 2016г.");
INSERT INTO `flickr_photo_set` (`account_id`, `album_id`, `description`) VALUES (1, "72157649073816279", "Фукуок. Осень 2014г.");

-- insert menu
INSERT INTO `menu` (`id`, `title`, `url`, `icon`) VALUES (1, "Афоризмы", "/admin/aphorism", "fa-comments");
INSERT INTO `menu` (`id`, `title`, `url`, `icon`) VALUES (2, "Галлерея", "/admin/gallery", "fa-photo");

-- insert socks5 user
INSERT INTO `user` (`login`, `first_name`, `last_name`, `password`) VALUES ("chine", "Jackie", "Chan", "chine");
