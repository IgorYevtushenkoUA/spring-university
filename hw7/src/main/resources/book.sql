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
    role_id integer primary key,
    name    varchar(32) not null
)
-- table user
create table user
(
    user_id      integer primary key,
    role_id      integer     not null,
    name         varchar(32) not null,
    surname      varchar(32) not null,
    password     varchar(32) not null,
    phone_number varchar(32) not null,
    constraint fk_user_role_id
        foreign key (role_id)
            references role (role_id)
)
-- table user_has_favourite_book
create table user_has_favourite_book
(
    user_id integer not null,
    book_id integer not null,
    constraint fk_user_has_favourite_book_user_id
        foreign key (user_id)
            references user (user_id),
    constraint fk_user_has_favourite_book_book_id
        foreign key (book_id)
            references book (book_id),
    constraint user_has_favourite_book_pk
        primary key (user_id, book_id)
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
-- insert role
insert into role(name) value ('user')
insert into role (name) value ('admin')
-- insert user
insert into user (role_id, name, surname, password, phone_number)
values (1, 'user', 'user', 'user', '111-11-11');
insert into user(name, surname, password, phone_number)
values (1, 'user2', 'surname2', 'user', '222-22-22');
insert into user(name, surname, password, phone_number)
values (1, 'user3', 'surname3', 'user', '333-33-33');
-- insert user_has_favourite_book
insert into user_has_favourite_book(user_id, book_id)
values (1, 1);

