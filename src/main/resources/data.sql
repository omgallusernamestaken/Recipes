-- INSERT dla ingredient_categories (kategorie składników)
INSERT INTO ingredient_categories (category_name) VALUES 
('Warzywa'),
('Owoce'),
('Mięso'),
('Nabiał'),
('Zboża');

-- INSERT dla ingredients (składniki)
INSERT INTO ingredients (ingredient_name, kcal_in_100g, category_id, unit) VALUES 
('Marchew', 41.00, 1, 'GRAM'),
('Pomidor', 18.00, 1, 'GRAM'),
('Jabłko', 52.00, 2, 'GRAM'),
('Banan', 89.00, 2, 'GRAM'),
('Kurczak', 165.00, 3, 'GRAM'),
('Wołowina', 250.00, 3, 'GRAM'),
('Mleko', 42.00, 4, 'MILLILITER'),
('Jogurt', 59.00, 4, 'GRAM'),
('Makaron', 365.00, 5, 'GRAM'),
('Ryż', 130.00, 5, 'GRAM');

-- INSERT dla tags (tagi)
INSERT INTO tags (tag_name) VALUES 
('Wegetariańskie'),
('Bezglutenowe'),
('Szybkie'),
('Obiad'),
('Kolacja');

-- INSERT dla recipes (przepisy)
INSERT INTO recipes (recipe_name, recipe_description, portions_amount, preparation_time, difficulty) VALUES
('Sałatka warzywna', 'Zdrowa sałatka z marchewką i pomidorem', 2, 15, 'EASY'),
('Kurczak pieczony', 'Soczysty kurczak z piekarnika', 4, 60, 'EASY'),
('Makaron z jogurtem', 'Makaron z sosem jogurtowym i bananem', 2, 10, 'HARD'),
('Owsianka z jabłkiem', 'Pyszna owsianka z jabłkiem i mlekiem', 1, 5, 'HARD'),
('Stek wołowy', 'Klasyczny stek wołowy smażony na patelni', 1, 20, 'HARD');

-- INSERT dla recipe_ingredients (składniki w przepisach)
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity) VALUES 
(1, 1, 100), -- Marchew do sałatki
(1, 2, 150), -- Pomidor do sałatki
(2, 5, 250), -- Kurczak do kurczaka pieczonego
(3, 9, 200), -- Makaron do makaronu z jogurtem
(3, 8, 100), -- Jogurt do makaronu z jogurtem
(3, 4, 50),  -- Banan do makaronu z jogurtem
(4, 3, 150), -- Jabłko do owsianki
(4, 7, 200), -- Mleko do owsianki
(5, 6, 300); -- Wołowina do steka

-- INSERT dla recipe_tags (przypisanie tagów do przepisów)
INSERT INTO recipe_tags (recipe_id, tag_id) VALUES 
(1, 1), -- Sałatka warzywna - wegetariańska
(1, 2), -- Sałatka warzywna - bezglutenowa
(2, 4), -- Kurczak pieczony - obiad
(3, 1), -- Makaron z jogurtem - wegetariańskie
(3, 3), -- Makaron z jogurtem - szybkie
(4, 1), -- Owsianka z jabłkiem - wegetariańskie
(4, 3), -- Owsianka z jabłkiem - szybkie
(5, 4); -- Stek wołowy - obiad

-- Opinie dla przepisu o ID 1
INSERT INTO opinions (opinion_rate, opinion_author, opinion_description, recipe_id)
VALUES
(5, 'Anna Kowalska', 'Bardzo smaczny przepis! Wszyscy w rodzinie go pokochali.', 1),
(4, 'Marek Nowak', 'Dobrze smakuje, ale można by dodać więcej przypraw.', 1),
(3, 'Katarzyna Wójcik', 'Trochę za mało aromatyczny, ale ok.', 1),
(4, 'Janusz Kwiatkowski', 'Świetne danie, jednak czas gotowania mógłby być krótszy.', 2),
(5, 'Ewa Zielińska', 'Pyszne i szybkie w przygotowaniu! Polecam.', 2),
(2, 'Piotr Szymański', 'Niestety, nie przypadło mi do gustu. Zbyt mało smaku.', 2),
(3, 'Magdalena Bąk', 'Danie nieźle smakuje, ale trochę za tłuste.', 3),
(5, 'Paweł Mazur', 'Rewelacyjne! To moje nowe ulubione danie.', 3),
(4, 'Lidia Król', 'Dobre, ale można by dodać więcej warzyw.', 3),
(2, 'Tomasz Dąbrowski', 'Niestety, nie udało mi się zrobić tego dania poprawnie. Trochę zbyt trudne.', 4),
(4, 'Zofia Pawlak', 'Pyszne, ale wymaga odrobiny wprawy.', 4),
(5, 'Kamil Kaczmarek', 'Doskonałe! Idealne na szybki obiad.', 4),
(3, 'Anna Lewandowska', 'Danie smaczne, ale spodziewałam się czegoś bardziej wyrazistego.', 5),
(5, 'Krzysztof Nowak', 'Idealne! Moje dzieci je uwielbiają.', 5),
(4, 'Agata Wiśniewska', 'Bardzo smaczne, choć trochę za słodkie dla mnie.', 5);