import { useState, useRef, useCallback } from 'react';
import NavBar from '../util/NavBar';
import './DiscoverPage.scss';
import SearchBar from '../util/SearchBar';
import Footer from '../util/Footer';
import 'bootstrap/dist/css/bootstrap.min.css';
import useRecipesSearch from './useRecipesSearch';
import RecipePreview from '../../recipe/RecipePreview';
import { Spinner } from 'react-bootstrap';
import CategoryBoxes from './CategoryBoxes';

export default function DiscoverPage() {
	const [query, setQuery] = useState('');
	const [category, setCategory] = useState('');
	const [pageNumber, setPageNumber] = useState(0);
	const sortBy = 'rating';
	const size = 5;

	const { recipes, hasMore, loading, error } = useRecipesSearch(query, category, pageNumber, size, sortBy);

	const observer = useRef();
	const afterLastRecipe = useCallback(
		node => {
			if (loading) return;
			if (observer.current) observer.current.disconnect();
			observer.current = new IntersectionObserver(entries => {
				if (entries[0].isIntersecting && hasMore) {
					setPageNumber(prevPageNumber => prevPageNumber + 1);
				}
			});
			if (node) observer.current.observe(node);
		},
		[loading, hasMore]
	);

	function handleSearch(e) {
		setQuery(e.target.value);
		setPageNumber(0);
	}

	function handleChangeCategory(categoryName) {
		setCategory(categoryName);
		setPageNumber(0);
	}

	return (
		<div className='discover-page'>
			<NavBar />
			<div className='content-wrapper'>
				<SearchBar onChange={handleSearch} />
				<CategoryBoxes includeAny={true} clickable={true} onClick={handleChangeCategory} />
				{recipes.map((recipe, index) => {
					if (recipes.length === index + 1) {
						return [
							<RecipePreview recipe={recipe} key={index} />,
							<div ref={afterLastRecipe} key={index + recipes.length}></div>,
						];
					} else {
						return <RecipePreview recipe={recipe} key={index} />;
					}
				})}
				{loading && <Spinner animation='border' />}
				{error && <p>Error occured :(</p>}
			</div>
			<Footer />
		</div>
	);
}
