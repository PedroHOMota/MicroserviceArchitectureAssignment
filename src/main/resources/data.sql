-- SERVICE A
create table COOKBOOK (
   book_id INT NOT NULL auto_increment,
   name VARCHAR(40) default NULL,
   category_id VARCHAR(40) default NULL,
   PRIMARY KEY (book_id)
   FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id)

);

create table CATEGORY (
   category_id INT NOT NULL auto_increment,
   name VARCHAR(40) default NULL,
   PRIMARY KEY (category_id),
);


-- SERVICE B

create table RECIPE (
   recipe_id INT NOT NULL auto_increment,
   title VARCHAR(400) default NULL,
   recipe VARCHAR(400) default NULL,
   ingredients VARCHAR(400) default NULL,
   author VARCHAR(20) default NULL,
   PRIMARY KEY (recipe_id)
);


create table RECIPESBYBOOK(
    recipe_by_book_id INT NOT NULL auto_increment,
    book_id INT NOT NULL,
    recipe_id INT NOT NULL,
    PRIMARY KEY (recipe_by_book_id),
    FOREIGN KEY (recipe_id) REFERENCES RECIPE(recipe_id)
);


