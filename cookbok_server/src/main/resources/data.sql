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

create table COOKBOOKBYCATEGORY(
    cookbook_by_category_id INT NOT NULL,
    book_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (cookbook_by_category_id),
    FOREIGN KEY (book_id) REFERENCES COOKBOOK(book_id),
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)
);

INSERT INTO CATEGORY(category_id,name) VALUES (1,'BBQ');
INSERT INTO COOKBOOK(book_id,name,category_id) VALUES (1,'BBQ BOOK', 1);
