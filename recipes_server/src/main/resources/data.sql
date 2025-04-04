create table RECIPE (
   recipe_id INT NOT NULL auto_increment,
   title VARCHAR(400) default NULL,
   recipe VARCHAR(400) default NULL,
   ingredients VARCHAR(400) default NULL,
   author VARCHAR(20) default NULL,
   PRIMARY KEY (recipe_id)
);


create table RECIPESBYBOOK(
    recipe_by_book_id INT NOT NULL,
    book_id INT NOT NULL,
    recipe_id INT NOT NULL,
    PRIMARY KEY (recipe_by_book_id),
    FOREIGN KEY (recipe_id) REFERENCES RECIPE(recipe_id)
);


