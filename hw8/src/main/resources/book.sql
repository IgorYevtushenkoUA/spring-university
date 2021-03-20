-- table book
create table book
(
    book_id     serial primary key,
    isbn        varchar(8)    not null,
    name        varchar(256)  not null,
    description varchar(2048) not null
);


-- table author
create table author
(
    author_id serial primary key,
    name      varchar(256) not null
);


-- table author_has_book
create table author_has_book
(
    author_id integer not null,
    book_id   integer not null,
    constraint fk_author_has_book_author_id
        foreign key (author_id)
            references author (author_id),
    constraint fk_author_has_book_book_id
        foreign key (book_id)
            references book (book_id),
    constraint author_has_book_pk
        primary key (author_id, book_id)
);


-- table role
create table role
(
    role_id serial primary key,
    name    varchar(32) not null
)


-- table client
create table client
(
    client_id    serial primary key,
    role_id      integer     not null,
    name         varchar(32) not null,
    surname      varchar(32) not null,
    password     varchar(32) not null,
    phone_number varchar(32) not null,
    constraint fk_client_role_id
        foreign key (role_id)
            references role (role_id)
)


-- table client_has_favourite_book
create table client_has_favourite_book
(
    client_id integer not null,
    book_id   integer not null,
    constraint fk_client_has_favourite_book_client_id
        foreign key (client_id)
            references client (client_id),
    constraint fk_client_has_favourite_book_book_id
        foreign key (book_id)
            references book (book_id),
    constraint client_has_favourite_book_pk
        primary key (client_id, book_id)
);


-- insert book
insert into book(isbn, name, description)
values ('622273', 'Rework',
        'Настав час відкинути традиційні уявлення про ведення бізнесу! Довідайтеся, як започаткувати справу чи вдосконалити власний бізнес. Автори крок за кроком навчать вас, як почати справу, пояснять, чому вам треба менше, ніж ви думаєте, порадять, як зробити так, щоб про вас дізналися, порекомендують, кого і коли варто взяти на роботу, а також як з усім цим упоратися. Книжка надихає та дає практичну базу для руху вперед!');
insert into book(isbn, name, description)
values ('1250625', 'Дмитрий Дубилет. Бизнес на здравом смысле. 50 идей как добиться своего',
        'У 14 років працював на заправці. У 20 років продав перший бізнес з оцінкою в $ 100 тис. У 28 - IT-директор найбільшого банку країни, а в 32 разом з партнерами запустив перший в країні онлайн-банк, у якого на момент виходу книги було 2 мільйони клієнтів. Звучить як анонс історії мільйонера з іншого континенту, втілення «американської мрії». Але мова про 34-річного українського хлопця, який, на додаток до вищепереліченого, встиг ще попрацювати міністром. Дмитро Дубілет - один з найбільш медійних, позитивних і ефективних підприємців в країні. Здається, що все за що він береться, у нього виходить. Як він домагається свого? Відповідь - в цій книзі.');
insert into book(isbn, name, description)
values ('10131517', 'My Life and My Work',
        'Генрі Форд - один з найвидатніших підприємців XX століття, талановитий винахідник і видатний менеджер. Його управлінському генію належить впровадження поточно-конвеєрного виробництва, скороченого робочого дня, мінімальної оплати праці, 5 робочого тижня і методів виробництва, які сьогодні застосовуються в багатьох концернах у всьому світі Ця книга - не просто блискуча розповідь «батька» автомобільної промисловості США про своє життя, в ній зображено практичний досвід заснування небувалого за масштабами виробництва.');
insert into book(isbn, name, description)
values ('384152', 'Шантарам',
        'Представляем читателю один из самых поразительных романов начала XXI века. Эта преломленная в художественной форме исповедь человека, который сумел выбраться из бездны и уцелеть, протаранила все списки бестселлеров и заслужила восторженные сравнения с произведениями лучших писателей Нового времени, от Мелвилла до Хемингуэя. Подобно автору, герой этого романа много лет скрывался от закона. Лишенный после развода с женой родительских прав, он пристрастился к наркотикам, совершил ряд ограблений и был приговорен австралийским судом к девятнадцати годам заключения. Бежав на второй год из тюрьмы строгого режима, он добрался до Бомбея, где был фальшивомонетчиком и контрабандистом, торговал оружием и участвовал в разборках индийской мафии, а также нашел свою настоящую любовь, чтобы вновь потерять ее, чтобы снова найти...');
insert into book(isbn, name, description)
values ('753434', 'Граф Монте Крісто',
        'Как и сто шестьдесят пять лет назад, "Граф Монте-Кристо" Александра Дюма остается одним из самых популярных романов в мировой литературе. К нему писали продолжения, его ставили на сцене, создавали мюзиклы, экранизировали, но и по сей день бесчисленные издания этой книги доставляют удовольствие новым и новым поколениям читателей. История молодого парижанина, которого приятели в шутку засадили в тюрьму, почерпнута автором в архивах парижской полиции. А из-под пера мастера выходит моряк Эдмон Дантес, мученик замка Иф. Не дождавшись правосудия, он решает сам вершить суд и жестоко мстит врагам, разрушившим его счастье. В настоящем издании роман сопровождается полным комплектом иллюстраций французских художников XIX века к первым публикациям "Графа Монте-Кристо". В издание также включена история сапожника Франсуа Пико, взятая из криминальной хроники, послужившая прообразом сюжетных перипетий романа.');
insert into book(isbn, name, description) values ('934185','Big money. Принципы первых','Big money. Принципы первых, Откровенно о бизнесе и жизни успешных предпринимателей. «Кто не хочет, ищет причины. Кто хочет, ищет возможности — фраза моего Отца, главная фраза в моем воспитании». Эта книга о людях, которые всегда ищут возможности и находят их. Всё хорошее в этом Мире придумано людьми, которые хотели заработать. Предпринимателями и Бизнесменами. Эта книга о них. О тех, кто каждый день делает этот мир лучше, развивая свой бизнес, создавая рабочие места и платя налоги. Вопреки стереотипам, это не скучные, угрюмые капиталисты, у которых в голове только деньги. Это интересные, умные, харизматичные люди, которые, как и каждый из нас, каждый день нуждаются в успехе и сожалеют о том, что времени для этого всегда не достаточно. Их отличие только в том, что все они одержимые своей идеей перфекционисты. А именно таким будет принадлежать Мир будущего. Я очень волнуюсь. В твоих руках, уважаемый читатель, моя первая книга, состоящая из интервью успешных предпринимателей в YouTube — проекте Big Money. Книга о людях и их историях побед. Эта книга поможет тебе увеличить скорость и быстрее достигнуть своей мечты. Именно для этого эта книга и создана!');
insert into book(isbn, name, description) values ('2317837','Big Money. Принципи перших Книга 2', 'Big money. Принципы первых Книга 2, Откровенно о бизнесе и жизни успешных предпринимателей. «Кто не хочет, ищет причины. Кто хочет, ищет возможности — фраза моего Отца, главная фраза в моем воспитании». Эта книга о людях, которые всегда ищут возможности и находят их. Всё хорошее в этом Мире придумано людьми, которые хотели заработать. Предпринимателями и Бизнесменами. Эта книга о них. О тех, кто каждый день делает этот мир лучше, развивая свой бизнес, создавая рабочие места и платя налоги. Вопреки стереотипам, это не скучные, угрюмые капиталисты, у которых в голове только деньги. Это интересные, умные, харизматичные люди, которые, как и каждый из нас, каждый день нуждаются в успехе и сожалеют о том, что времени для этого всегда не достаточно. Их отличие только в том, что все они одержимые своей идеей перфекционисты. А именно таким будет принадлежать Мир будущего. Я очень волнуюсь. В твоих руках, уважаемый читатель, моя первая книга, состоящая из интервью успешных предпринимателей в YouTube — проекте Big Money. Книга о людях и их историях побед. Эта книга поможет тебе увеличить скорость и быстрее достигнуть своей мечты. Именно для этого эта книга и создана!');
insert into book(isbn, name, description) values ('1457837','The 7 habits of highly effective people','Эта книга — мировой супербестселлер, работа № 1 по теме личностного роста. Она оказала большое влияние на жизни миллионов людей во всем мире, включая Билла Клинтона, Ларри Кинга и Стивена Форбса. Половина крупнейших мировых корпораций, входящих в рейтинг Fortune 500, посчитали своим долгом ознакомить своих сотрудников с философией эффективности, изложенной в «Семи навыках». О чем эта книга? Во-первых, она излагает системный подход к определению жизненных целей, приоритетов человека. Эти цели у всех разные, но книга помогает понять себя и четко сформулировать жизненные цели. Во-вторых, книга объясняет, как достигать этих целей. И в-третьих, книга показывает, как каждый человек может стать лучше. Причем речь идет не об изменении имиджа, а о настоящих изменениях, самосовершенствовании по сути.');

-- insert author
insert into author(name)
values ('Джейсон Фрайд');
insert into author(name)
values ('Девір Хейнмеєр Хансон');
insert into author(name)
values ('Тімур Ворона');
insert into author(name)
values ('Генрі Форд');
insert into author(name)
values ('Грегорі Девір Робертс');
insert into author(name)
values ('Граф Дюма');
insert into author(name) values ('Євген Черняк');
insert into author(name) values ('Стівен Кові');

--insert author_has_book
insert into author_has_book(author_id, book_id)
values (1, 1);
insert into author_has_book(author_id, book_id)
values (2, 1);
insert into author_has_book(author_id, book_id)
values (3, 2);
insert into author_has_book(author_id, book_id)
values (4, 3);
insert into author_has_book(author_id, book_id)
values (5, 4);
insert into author_has_book(author_id, book_id)
values (6, 5);
insert into author_has_book(author_id, book_id) values(7,6);
insert into author_has_book(author_id, book_id) values(7,7);
insert into author_has_book(author_id, book_id) values(8,8);

-- insert role
insert into role (name)
values ('client');
insert into role (name)
values ('admin');


-- insert client
insert into client (role_id, name, surname, password, phone_number)
values (1, 'client', 'client', 'client', '123456789');
insert into client(role_id, name, surname, password, phone_number)
values (1, 'client2', 'surname2', 'client', '671122333');
insert into client(role_id, name, surname, password, phone_number)
values (1, 'client3', 'surname3', 'client', '681122333');


-- insert client_has_favourite_book
insert into client_has_favourite_book(client_id, book_id)
values (1, 1);
insert into client_has_favourite_book(client_id, book_id)
values (1, 2);
insert into client_has_favourite_book(client_id, book_id)
values (1, 3);

