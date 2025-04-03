document.addEventListener("DOMContentLoaded", function () {
    const selectedIngredientsList = document.getElementById("selectedIngredients");

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
        if (document.getElementById("ingredient-" + selectedIngredientId)) {
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
        li.innerHTML = `<span class="ingredient-name">${selectedIngredientName}</span>
                        <span class="quantity"> - ilość: ${quantity}</span>
                        <button type="button" onclick="removeIngredient(${selectedIngredientId})">x</button>`;
        selectedIngredientsList.appendChild(li);

        updateIngredientMap();
        quantityInput.value = 1;
    };

    window.removeIngredient = function (ingredientId) {
        const ingredientElement = document.getElementById("ingredient-" + ingredientId);
        if (ingredientElement) {
            ingredientElement.remove();
        }

        updateIngredientMap();
    };

function updateIngredientMap() {
    const ingredientMap = {};
    const ingredientItems = selectedIngredientsList.getElementsByTagName("li");

    Array.from(ingredientItems).forEach(li => {
        const ingredientId = li.id.replace("ingredient-", "");
        const quantityText = li.innerText.match(/- ilość: (\d+)/);
        const quantity = quantityText ? parseInt(quantityText[1], 10) : 1;

        ingredientMap[ingredientId] = quantity;
    });

    const ingredientInput = document.getElementById("ingredientMap");
    if (ingredientInput) {
        ingredientInput.value = JSON.stringify(ingredientMap);
    }
}

    function init() {
        const ingredientMap = JSON.parse(document.getElementById("ingredientMap").value || "{}");
        for (let ingredientId in ingredientMap) {
            addIngredientToList(ingredientId, ingredientMap[ingredientId]);
        }
    }

function addIngredientToList(ingredientId, quantity) {
    const select = document.getElementById("ingredientSelect");
    const option = Array.from(select.options).find(opt => opt.value == ingredientId);
    const selectedIngredientName = option ? option.text : "Nieznany składnik";

    const li = document.createElement("li");
    li.id = "ingredient-" + ingredientId;
    li.innerHTML = `<span>${selectedIngredientName} - ilość: ${quantity}</span>
                    <button type="button" onclick="removeIngredient(${ingredientId})">x</button>`;
    selectedIngredientsList.appendChild(li);
}

    init();
});
