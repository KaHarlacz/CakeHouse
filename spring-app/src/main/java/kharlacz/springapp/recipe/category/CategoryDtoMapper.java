package kharlacz.springapp.recipe.category;

public class CategoryDtoMapper {
    public static RecipeCategoryDto map(RecipeCategory category) {
        return RecipeCategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
