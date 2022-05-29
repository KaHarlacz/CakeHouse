package kharlacz.springapp.recipe.category;

public record RecipeCategoryDto(String name) {
    
    public static RecipeCategoryDto from(RecipeCategory category) {
        return new RecipeCategoryDto(category.name());
    }
}
