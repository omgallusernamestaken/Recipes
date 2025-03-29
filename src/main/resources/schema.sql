DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS ingredient_categories;

CREATE TABLE ingredient_categories (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

CREATE TABLE ingredients (
	ingredient_id SERIAL PRIMARY KEY,
	ingredient_name VARCHAR(100) UNIQUE NOT NULL,
	kcal_in_100g NUMERIC(10,2),
	category_id INT NOT NULL,
	FOREIGN KEY (category_id) REFERENCES ingredient_categories(category_id)
);