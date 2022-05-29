import HeroImage from '../util/HeroImage';
import NavBar from '../util/NavBar';
import HorizontalSeparator from '../../section-separator/HorizontalSeparator';
import './MainPage.scss';
import Footer from '../util/Footer';
import useRecipesSearch from '../discover/useRecipesSearch';
import RecipePreview from '../../recipe/RecipePreview';

require('dotenv').config();

function MainPage() {
	const { recipes, loading } = useRecipesSearch('', '', 0, 15, 'dateAdded');

	return (
		<div className='main-page'>
			<NavBar />
			<HeroImage />
            <p>{process.env.REACT_APP_CAKEHOUSE_BACKEND_URL}</p>
			<div className='content-wrapper'>
				<HorizontalSeparator text='recently added' />
				<div className='newest-top'>{!loading && <RecipePreview recipe={recipes[0]} layout='top-bottom' />}</div>
				<div className='newest-bottom'>
					{recipes.slice(1, 5).map(recipe => {
						return <RecipePreview recipe={recipe} layout='side-by-side' key={recipe.id} />;
					})}
				</div>
				<HorizontalSeparator text='top rated' />
				<div className='top-rated'>
					{recipes.slice(6, recipes.length).map(recipe => {
						return <RecipePreview recipe={recipe} layout='top-bottom' key={recipe.id} />;
					})}
				</div>
			</div>
			<Footer />
		</div>
	);
}

export default MainPage;
