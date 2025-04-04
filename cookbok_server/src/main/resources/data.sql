create table CATEGORY (
   category_id INT NOT NULL auto_increment,
   name VARCHAR(40) default NULL,
   PRIMARY KEY (category_id)
);

create table COOKBOOK (
   book_id INT NOT NULL auto_increment,
   name VARCHAR(40) default NULL,
   category_id VARCHAR(40) default NULL,
   PRIMARY KEY (book_id),
   FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)
);


