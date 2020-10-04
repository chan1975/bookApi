print 'Tablas para userdb'

use userdb
go

print 'book_tbl'
if object_id('book_tbl') is not null
    drop table book_tbl
go

create table book_tbl (
   auth              varchar(150),
   description       varchar(300),
   name              varchar(150) primary key,
   price             numeric(6,4),
   published_date    date,
   id                int not null identity
)
