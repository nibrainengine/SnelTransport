DROP TABLE IF EXISTS Zone;
DROP TABLE IF EXISTS canceledCourierSchedule;

create table zone (
    id int GENERATED ALWAYS AS IDENTITY not null primary key,
    title NVARCHAR2(255)
);

create table canceledCourierSchedule (
    courierScheduleId int primary key,
    approved bit,
    reason NVARCHAR2(255)
);