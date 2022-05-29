import useRecipeCategories from '../recipe/useRecipeCategories';
import SelectableBoxes from '../util/SelectableBoxes';

export default function CategoryBoxes(props) {
	const { onClick, clickable } = props;
	const [categories] = useRecipeCategories();

	const handleClick = label => {
		if (label === 'any') {
			label = '';
		}
		if (clickable !== false) {
			onClick(label);
		}
	};

	const categoriesWithAny = ['any', ...categories];
	return <SelectableBoxes labels={categoriesWithAny} onClick={handleClick} />;
}
