DROP TABLE IF EXISTS Zone;

create table zone (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    title NVARCHAR2(255)
);
