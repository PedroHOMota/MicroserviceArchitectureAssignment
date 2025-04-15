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


--INSERT INTO RECIPE(title,ingredients,author,recipe) VALUES (1,"BBQ Sauce","Some ingredients","Myself","Sequence of Steps to follow");
--INSERT INTO RECIPE(title,ingredients,author,recipe) VALUES (2,"Argentinian BBQ","Some ingredients","Myself","Sequence of Steps to follow");
--INSERT INTO RECIPE(title,ingredients,author,recipe) VALUES (3,"Cake","Some ingredients","Another Author","Sequence of Steps to follow");
--
--INSERT INTO RECIPESBYBOOK(book_id,recipe_id) VALUES (1,1);
--INSERT INTO RECIPESBYBOOK(book_id,recipe_id) VALUES (1,2);