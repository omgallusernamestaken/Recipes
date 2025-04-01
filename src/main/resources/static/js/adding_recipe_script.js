function addIngredient() {
    const select = document.getElementById("ingredientSelect");
    const selectedIngredientId = select.value;
    const selectedIngredientName = select.options[select.selectedIndex].text;
    const quantity = document.getElementById("ingredientQuantity").value;

    if (!document.getElementById("ingredient-" + selectedIngredientId)) {
        const li = document.createElement("li");
        li.id = "ingredient-" + selectedIngredientId;
        li.innerHTML = `<span>${selectedIngredientName} - ilość: ${quantity}</span>
                        <button type="button" onclick="removeIngredient(${selectedIngredientId})">x</button>`;
        document.getElementById("selectedIngredients").appendChild(li);

        const inputId = document.createElement("input");
        inputId.type = "hidden";
        inputId.name = "ingredientIds";
        inputId.value = selectedIngredientId;
        inputId.id = "input-ingredient-" + selectedIngredientId;

        const inputQuantity = document.createElement("input");
        inputQuantity.type = "hidden";
        inputQuantity.name = "quantities";
        inputQuantity.value = quantity;
        inputQuantity.id = "input-quantity-" + selectedIngredientId;

        document.getElementById("ingredientsContainer").appendChild(inputId);
        document.getElementById("ingredientsContainer").appendChild(inputQuantity);
    }
}

function removeIngredient(ingredientId) {
    document.getElementById("ingredient-" + ingredientId).remove();
    document.getElementById("input-ingredient-" + ingredientId).remove();
    document.getElementById("input-quantity-" + ingredientId).remove();
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