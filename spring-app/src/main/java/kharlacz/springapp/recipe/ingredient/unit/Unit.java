package kharlacz.springapp.recipe.ingredient.unit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Unit {
    KG("kilogram", "kg"),
    G("gram", "g"),
    PN("pinch", "pn"),
    L("litre", "l"),
    ML("millilitre", "ml");

    private String name;
    private String abbreviation;
}
