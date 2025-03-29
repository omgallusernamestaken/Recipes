INSERT INTO ingredient_categories (category_name) VALUES
('Warzywa'),
('Owoce'),
('Nabiał'),
('Mięso'),
('Zboża'),
('Tłuszcze'),
('Przyprawy');

INSERT INTO ingredients (ingredient_name, kcal_in_100g, category_id) VALUES
('Marchew', 41.00, 1),
('Pomidor', 18.00, 1),
('Cebula', 40.00, 1),
('Jabłko', 52.00, 2),
('Banan', 89.00, 2),
('Truskawka', 32.00, 2),
('Mleko', 42.00, 3),
('Ser żółty', 402.00, 3),
('Kurczak', 165.00, 4),
('Ryż', 130.00, 5);

INSERT INTO recipes (recipe_name, recipe_description, recipe_category, portions_amount) VALUES
('Zupa pomidorowa', 'Kremowa zupa pomidorowa, idealna na obiad.', 'Zupy', 4),
('Sałatka owocowa', 'Świeża sałatka owocowa, pełna witamin i smaku.', 'Sałatki', 2),
('Kurczak z ryżem', 'Smażony kurczak podany z aromatycznym ryżem.', 'Mięso', 2),
('Omlet z warzywami', 'Omlet z dodatkiem warzyw, doskonały na śniadanie.', 'Śniadania', 1),
('Smoothie bananowe', 'Odżywcze smoothie z bananem i mlekiem.', 'Napoje', 2),
('Koktajl truskawkowy', 'Pyszny koktajl z truskawek, mleka i jogurtu.', 'Napoje', 2),
('Kurczak pieczony z warzywami', 'Pieczony kurczak w towarzystwie warzyw i przypraw.', 'Mięso', 4),
('Tosty z serem', 'Proste tosty z roztopionym serem żółtym.', 'Przekąski', 2),
('Ryż z warzywami', 'Lekka potrawa z ryżem i sezonowymi warzywami.', 'Wegetariańskie', 4),
('Jabłka w cieście', 'Deser z jabłkami w cieście, doskonały na słodko.', 'Desery', 6);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(1, 2, 200), -- Pomidor
(1, 3, 100), -- Cebula
(1, 6, 50);  -- Truskawka (można dodać np. jako dekorację)

-- Sałatka owocowa
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(2, 4, 150), -- Jabłko
(2, 5, 100), -- Banan
(2, 6, 50);  -- Truskawka

-- Kurczak z ryżem
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(3, 9, 300), -- Kurczak
(3, 10, 200); -- Ryż

-- Omlet z warzywami
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(4, 1, 100), -- Marchew
(4, 3, 50),  -- Cebula
(4, 5, 100); -- Banan

-- Smoothie bananowe
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(5, 5, 150), -- Banan
(5, 7, 100); -- Mleko

-- Koktajl truskawkowy
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(6, 6, 200), -- Truskawka
(6, 7, 100); -- Mleko

-- Kurczak pieczony z warzywami
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(7, 9, 400), -- Kurczak
(7, 1, 200), -- Marchew
(7, 3, 100); -- Cebula

-- Tosty z serem
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(8, 7, 50),  -- Mleko
(8, 8, 100); -- Ser żółty

-- Ryż z warzywami
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(9, 10, 200), -- Ryż
(9, 1, 150),  -- Marchew
(9, 3, 100);  -- Cebula

-- Jabłka w cieście
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES
(10, 4, 200), -- Jabłko
(10, 8, 100); -- Ser żółty