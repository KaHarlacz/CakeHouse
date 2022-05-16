import { useState } from 'react';
import Footer from '../util/Footer';
import NavBar from '../util/NavBar';
import './AddRecipePage.scss';
import AutosizedTextArea from './AutosizedTextArea';
import ImageUploadBox from './ImageUploadBox';
import ItemsList from './ItemsList';

export default function AddRecipePage(props) {
	let [recipe, setRecipe] = useState({
		name: '',
		desc: '',
		prep: '',
		imageB64: '',
		ingredients: [],
		categories: [],
	});

	const handleInput = e =>
		setRecipe({
			...recipe,
			[e.currentTarget.name]: e.currentTarget.value,
		});

	const submitRecipe = () => {
		const requestOptions = {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({ ...recipe, ingredients: recipe.ingredients.map(i => i[0]) }),
		};
		fetch(`${process.env.REACT_APP_CAKEHOUSE_BACKEND_URL}/recipes`, requestOptions)
			.then(data => data.json())
			.then(body => console.log(body));
	};

	return (
		<div className='add-recipe-page'>
			<NavBar />
			<div className='content-wrapper'>
				<div className='recipe-header'>
					<input
						className='name-input'
						type='text'
						name='name'
						placeholder='Type recipe name here'
						value={recipe.name}
						onChange={handleInput}
					/>
					<input
						className='desc-input'
						type='text'
						name='desc'
						placeholder='Type recipe short description here'
						value={recipe.desc}
						onChange={handleInput}
					/>
				</div>
				<ImageUploadBox />
				<AutosizedTextArea />
				<ItemsList
					className='ingredients-list'
					labels={['Ingredients']}
					items={recipe.ingredients}
					setItems={ingredients => {
						console.log('Setting ingredients:');
						console.log(ingredients);
						setRecipe({ ...recipe, ingredients });
					}}
					placeholder='ingredient & quantity here'
					editable={true}
				/>
				{/* TODO: Categories should be fetched from backend */}
				<div className='category-select-wrapper'>
					<select className='category-select' id='category-select'>
						<option value=''>-- Choose a recipe category --</option>
						<option value='DINNER'>Dinner</option>
						<option value='THANKSGIVING'>Thanksgiving</option>
						<option value='CHRISTMAA'>Christmas</option>
					</select>
				</div>
			</div>
			<button className='confirm-button' onClick={submitRecipe}>
				Confirm
			</button>
			<Footer />
		</div>
	);
}
