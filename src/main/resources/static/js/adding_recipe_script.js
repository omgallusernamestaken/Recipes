document.addEventListener("DOMContentLoaded", function () {
    const selectedTagsList = document.getElementById("selectedTags");
    const selectedIngredientsList = document.getElementById("selectedIngredients");

    // Dodawanie składnika
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

        // Sprawdzenie, czy składnik już istnieje
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

        // Dodanie składnika do listy
        addIngredientToList(selectedIngredientId, selectedIngredientName, quantity);
        updateIngredientMap();
        quantityInput.value = 1;
    };

    // Usuwanie składnika
    window.removeIngredient = function (ingredientId) {
        const ingredientElement = document.getElementById("ingredient-" + ingredientId);
        if (ingredientElement) {
            ingredientElement.remove();
        }
        updateIngredientMap();
    };

    // Aktualizacja ukrytego pola składników
    function updateIngredientMap() {
        const ingredientMap = {};
        const ingredientItems = selectedIngredientsList.getElementsByTagName("li");

        Array.from(ingredientItems).forEach(li => {
            const ingredientId = li.id.replace("ingredient-", "");
            const quantityText = li.innerText.match(/- ilość: (\d+)/);
            const quantity = quantityText ? parseInt(quantityText[1], 10) : 1;

            ingredientMap[ingredientId] = quantity;
        });

        console.log('Updated ingredient map:', ingredientMap);

        const ingredientInput = document.getElementById("ingredientMap");
        if (ingredientInput) {
            ingredientInput.value = JSON.stringify(ingredientMap);
        }
    }

    // Funkcja do dodawania składnika do listy (wykorzystywana także przy edycji)
    function addIngredientToList(ingredientId, ingredientName, quantity) {
        const li = document.createElement("li");
        li.id = "ingredient-" + ingredientId;
        li.innerHTML = `<span>${ingredientName} - ilość: ${quantity}</span>
                        <button type="button" onclick="removeIngredient(${ingredientId})">x</button>`;
        selectedIngredientsList.appendChild(li);
    }

    // Obsługa tagów
    window.addTag = function () {
        const select = document.getElementById("tagSelect");
        const selectedTagId = select.value;
        const selectedTagName = select.options[select.selectedIndex].text;

        if (!selectedTagId) {
            alert("Wybierz tag!");
            return;
        }

        // Sprawdzenie, czy tag już istnieje
        const tagExists = Array.from(selectedTagsList.getElementsByTagName("li"))
            .some(li => li.id === "tag-" + selectedTagId);

        if (tagExists) {
            alert("Tag jest już na liście!");
            return;
        }

        // Dodanie tagu do listy
        const li = document.createElement("li");
        li.id = "tag-" + selectedTagId;
        li.innerHTML = `<span>${selectedTagName}</span>
                        <button type="button" onclick="removeTag(${selectedTagId})">x</button>`;
        selectedTagsList.appendChild(li);

        // Ukryty input dla tagów
        const input = document.createElement("input");
        input.type = "hidden";
        input.name = "recipeTags";
        input.value = selectedTagId;
        input.id = "input-tag-" + selectedTagId;
        document.getElementById("tagsContainer").appendChild(input);
    };

    window.removeTag = function (tagId) {
        const tagElement = document.getElementById("tag-" + tagId);
        if (tagElement) {
            tagElement.remove();
        }

        const inputElement = document.getElementById("input-tag-" + tagId);
        if (inputElement) {
            inputElement.remove();
        }

        updateTagMap();
    };

    function updateTagMap() {
        const tagMap = {};
        const tagItems = selectedTagsList.getElementsByTagName("li");

        Array.from(tagItems).forEach(li => {
            const tagId = li.id.replace("tag-", "");
            tagMap[tagId] = true;
        });

        // Czyszczenie i ponowne dodanie ukrytych inputów dla tagów
        const tagsContainer = document.getElementById("tagsContainer");
        tagsContainer.innerHTML = "";

        Object.keys(tagMap).forEach(tagId => {
            const input = document.createElement("input");
            input.type = "hidden";
            input.name = "recipeTags";
            input.value = tagId;
            input.id = "input-tag-" + tagId;
            tagsContainer.appendChild(input);
        });
    }

    // Resetowanie danych przed wysłaniem formularza
    window.beforeSubmit = function () {
        updateIngredientMap();
        updateTagMap();
    };

    // Inicjalizacja składników i tagów po załadowaniu strony (np. po edycji)
function init() {
    const ingredientMap = JSON.parse(document.getElementById("ingredientMap").value || "{}");
    for (let ingredientId in ingredientMap) {
        const quantity = ingredientMap[ingredientId];

        // Pobranie nazwy składnika z dostępnych opcji
        const select = document.getElementById("ingredientSelect");
        const option = Array.from(select.options).find(opt => opt.value == ingredientId);
        const ingredientName = option ? option.text : "Nieznany składnik";

        addIngredientToList(ingredientId, ingredientName, quantity);
    }

    const tagItems = document.querySelectorAll("#tagsContainer input[name='recipeTags']");
    tagItems.forEach(input => {
        const tagId = input.value;

        // Sprawdzenie, czy tag już jest na liście, jeśli tak – pomijamy
        if (document.getElementById("tag-" + tagId)) {
            return;
        }

        const select = document.getElementById("tagSelect");
        const option = Array.from(select.options).find(opt => opt.value == tagId);
        const tagName = option ? option.text : "Nieznany tag";

        addTagToList(tagId, tagName);
    });
}

    function addTagToList(tagId, tagName) {
        const li = document.createElement("li");
        li.id = "tag-" + tagId;
        li.innerHTML = `<span>${tagName}</span>
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
