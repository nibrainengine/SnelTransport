DROP TABLE IF EXISTS Zone;
DROP TABLE IF EXISTS ZonePoint;

create table zone (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    title NVARCHAR2(255)
);

create table zonePoint(
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    zoneId int,
    index int,
    latitude decimal(18,15),
    longitude decimal(18,15)
);
