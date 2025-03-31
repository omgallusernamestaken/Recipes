DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS recipe_tags;
DROP TABLE IF EXISTS recipes;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS tags;
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
	unit VARCHAR(10) NOT NULL,
	FOREIGN KEY (category_id) REFERENCES ingredient_categories(category_id)
);

CREATE TABLE tags (
    tag_id SERIAL PRIMARY KEY,
    tag_name VARCHAR(100) NOT NULL
);

CREATE TABLE recipes (
	recipe_id SERIAL PRIMARY KEY,
	recipe_name VARCHAR(100) NOT NULL,
	recipe_description TEXT,
	portions_amount INTEGER,
	preparation_time INTEGER,
	difficulty VARCHAR(10)
);

CREATE TABLE recipe_tags (
    id SERIAL PRIMARY KEY,
    recipe_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id),
    FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
);

CREATE TABLE recipe_ingredients (
    id SERIAL PRIMARY KEY,
    recipe_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    quantity DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id) ON DELETE CASCADE
);