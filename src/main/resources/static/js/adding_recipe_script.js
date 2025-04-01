function addIngredient() {
    const select = document.getElementById("ingredientSelect");
    const selectedIngredientId = select.value;
    const selectedIngredientName = select.options[select.selectedIndex].text;
    const quantity = document.getElementById("ingredientQuantity").value;

    // Pobierz obecny stan mapy z ukrytego pola (jeśli istnieje)
    let ingredientMap = JSON.parse(document.getElementById("ingredientMap").value || '{}');

    // Jeśli składnik jeszcze nie jest w mapie, dodaj go
    if (!ingredientMap[selectedIngredientId]) {
        ingredientMap[selectedIngredientId] = quantity;

        // Dodaj składnik do listy na stronie
        const li = document.createElement("li");
        li.id = "ingredient-" + selectedIngredientId;
        li.innerHTML = `<span>${selectedIngredientName} - ilość: ${quantity}</span>
                        <button type="button" onclick="removeIngredient(${selectedIngredientId})">x</button>`;
        document.getElementById("selectedIngredients").appendChild(li);

        // Zaktualizuj wartość ukrytego pola z mapą składników
        document.getElementById("ingredientMap").value = JSON.stringify(ingredientMap);
    }
}

function removeIngredient(ingredientId) {
    // Pobierz obecny stan mapy z ukrytego pola (jeśli istnieje)
    let ingredientMap = JSON.parse(document.getElementById("ingredientMap").value || '{}');

    // Usuń składnik z mapy
    delete ingredientMap[ingredientId];

    // Usuń element z listy na stronie
    document.getElementById("ingredient-" + ingredientId).remove();

    // Zaktualizuj wartość ukrytego pola z mapą składników
    document.getElementById("ingredientMap").value = JSON.stringify(ingredientMap);
}

function addTag() {
    const select = document.getElementById("tagSelect");
    const selectedTagId = select.value;
    const selectedTagName = select.options[select.selectedIndex].text;

    if (!document.getElementById("tag-" + selectedTagId)) {
        const li = document.createElement("li");
        li.id = "tag-" + selectedTagId;
        li.innerHTML = `<span>${selectedTagName}</span>
                        <button type="button" onclick="removeTag(${selectedTagId})">x</button>`;
        document.getElementById("selectedTags").appendChild(li);

        const input = document.createElement("input");
        input.type = "hidden";
        input.name = "recipeTags";
        input.value = selectedTagId;
        input.id = "input-tag-" + selectedTagId;
        document.getElementById("tagsContainer").appendChild(input);
    }
}

function removeTag(tagId) {
    document.getElementById("tag-" + tagId).remove();
    document.getElementById("input-tag-" + tagId).remove();
}