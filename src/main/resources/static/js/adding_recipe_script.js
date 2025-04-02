document.addEventListener("DOMContentLoaded", function () {
    const selectedTagsList = document.getElementById("selectedTags");
    const selectedIngredientsList = document.getElementById("selectedIngredients");

    // Funkcja do dodawania składnika
    window.addIngredient = function () {
        const select = document.getElementById("ingredientSelect");
        const selectedIngredientId = select.value;
        const selectedIngredientName = select.options[select.selectedIndex].text;
        const quantityInput = document.getElementById("ingredientQuantity");
        const quantity = parseInt(quantityInput.value, 10);

        if (!selectedIngredientId) {
            alert("Wybierz składnik!");
            return;
        }

        // Sprawdzenie, czy składnik jest już na liście
        const ingredientExists = Array.from(selectedIngredientsList.getElementsByTagName("li"))
            .some(li => li.id === "ingredient-" + selectedIngredientId);

        if (ingredientExists) {
            alert("Ten składnik jest już na liście!");
            return;
        }

        if (quantity <= 0 || isNaN(quantity)) {
            alert("Ilość składnika musi być większa niż 0!");
            return;
        }

        // Dodaj składnik do listy
        const li = document.createElement("li");
        li.id = "ingredient-" + selectedIngredientId;
        li.innerHTML = `<span>${selectedIngredientName} - ilość: ${quantity}</span>
                        <button type="button" onclick="removeIngredient(${selectedIngredientId})">x</button>`;
        selectedIngredientsList.appendChild(li);

        // Zaktualizuj wartość ukrytego pola z mapą składników
        updateIngredientMap();
        quantityInput.value = 1;
    };

    // Funkcja do usuwania składnika
    window.removeIngredient = function (ingredientId) {
        const ingredientElement = document.getElementById("ingredient-" + ingredientId);
        if (ingredientElement) {
            ingredientElement.remove();
        }

        // Zaktualizuj wartość ukrytego pola z mapą składników
        updateIngredientMap();
    };

    // Funkcja do dodawania tagu
    window.addTag = function () {
        const select = document.getElementById("tagSelect");
        const selectedTagId = select.value;
        const selectedTagName = select.options[select.selectedIndex].text;

        if (!selectedTagId) {
            alert("Wybierz tag!");
            return;
        }

        // Sprawdzenie, czy tag jest już na liście
        const tagExists = Array.from(selectedTagsList.getElementsByTagName("li"))
            .some(li => li.id === "tag-" + selectedTagId);

        if (tagExists) {
            alert("Tag jest już na liście!");
            return;
        }

        // Dodaj tag do listy
        const li = document.createElement("li");
        li.id = "tag-" + selectedTagId;
        li.innerHTML = `<span>${selectedTagName}</span>
                        <button type="button" onclick="removeTag(${selectedTagId})">x</button>`;
        selectedTagsList.appendChild(li);

        // Dodaj tag do ukrytego inputu
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = "recipeTags";
        input.value = selectedTagId;
        input.id = "input-tag-" + selectedTagId;
        document.getElementById("tagsContainer").appendChild(input);
    };

    // Funkcja do usuwania tagu
    window.removeTag = function (tagId) {
        const tagElement = document.getElementById("tag-" + tagId);
        if (tagElement) {
            tagElement.remove();
        }

        const inputElement = document.getElementById("input-tag-" + tagId);
        if (inputElement) {
            inputElement.remove();
        }

        // Zaktualizuj wartość ukrytego pola z mapą tagów
        updateTagMap();
    };

    // Funkcja do aktualizacji ukrytego pola z mapą składników
    function updateIngredientMap() {
        const ingredientMap = {};
        const ingredientItems = selectedIngredientsList.getElementsByTagName("li");

        Array.from(ingredientItems).forEach(li => {
            const ingredientId = li.id.replace("ingredient-", "");
            const quantityText = li.querySelector("span").textContent;
            const quantityMatch = quantityText.match(/- ilość: (\d+)/);
            const quantity = quantityMatch ? parseInt(quantityMatch[1], 10) : 1;

            ingredientMap[ingredientId] = quantity;
        });

        // Zaktualizuj ukryte pole dla składników
        const ingredientInput = document.getElementById("ingredientMap");
        if (ingredientInput) {
            ingredientInput.value = JSON.stringify(ingredientMap);
        }
    }

    // Funkcja do aktualizacji mapy tagów (jeśli usunięto tagi)
    function updateTagMap() {
        const tagMap = {};
        const tagItems = selectedTagsList.getElementsByTagName("li");

        Array.from(tagItems).forEach(li => {
            const tagId = li.id.replace("tag-", "");
            tagMap[tagId] = true;  // Możemy przechować po prostu tagId
        });

        // Usuwamy wszystkie ukryte inputy tagów i tworzymy nowe
        const tagsContainer = document.getElementById("tagsContainer");
        tagsContainer.innerHTML = "";  // Usuwamy poprzednie tagi

        Object.keys(tagMap).forEach(tagId => {
            const input = document.createElement("input");
            input.type = "hidden";
            input.name = "recipeTags";
            input.value = tagId;
            input.id = "input-tag-" + tagId;
            tagsContainer.appendChild(input);
        });
    }

    // Funkcja do resetowania ukrytych danych przy wysyłaniu formularza
    window.beforeSubmit = function () {
        // Zaktualizuj dane składników i tagów przed wysyłką
        updateIngredientMap();
        updateTagMap();
    };

    // Zainicjuj wyświetlanie składników i tagów w formularzu po załadowaniu strony
    function init() {
        // Przekopiowanie mapy składników i tagów z ukrytych danych na stronie
        const ingredientMap = JSON.parse(document.getElementById("ingredientMap").value || "{}");
        for (let ingredientId in ingredientMap) {
            const quantity = ingredientMap[ingredientId];
            addIngredientToList(ingredientId, quantity);
        }

        const tagMap = JSON.parse(document.getElementById("tagsContainer").innerHTML || "{}");
        for (let tagId in tagMap) {
            addTagToList(tagId);
        }
    }

    // Funkcja pomocnicza do dodawania składnika do listy (używana w inicjalizacji)
    function addIngredientToList(ingredientId, quantity) {
        const select = document.getElementById("ingredientSelect");
        const selectedIngredientName = select.options[select.selectedIndex].text;

        const li = document.createElement("li");
        li.id = "ingredient-" + ingredientId;
        li.innerHTML = `<span>${selectedIngredientName} - ilość: ${quantity}</span>
                        <button type="button" onclick="removeIngredient(${ingredientId})">x</button>`;
        selectedIngredientsList.appendChild(li);
    }

    // Funkcja pomocnicza do dodawania tagu do listy (używana w inicjalizacji)
    function addTagToList(tagId) {
        const select = document.getElementById("tagSelect");
        const selectedTagName = select.options[select.selectedIndex].text;

        const li = document.createElement("li");
        li.id = "tag-" + tagId;
        li.innerHTML = `<span>${selectedTagName}</span>
                        <button type="button" onclick="removeTag(${tagId})">x</button>`;
        selectedTagsList.appendChild(li);

        const input = document.createElement("input");
        input.type = "hidden";
        input.name = "recipeTags";
        input.value = tagId;
        input.id = "input-tag-" + tagId;
        document.getElementById("tagsContainer").appendChild(input);
    }

    init();
});
