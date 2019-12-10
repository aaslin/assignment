create table movie (
    id identity primary key,
    title varchar(255) not null,
    duration int not null
);

alter table movie
add constraint title_unique
unique (title)