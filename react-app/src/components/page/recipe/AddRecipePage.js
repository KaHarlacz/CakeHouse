import axios from 'axios';
import { useEffect, useState } from 'react';
import MinusSvg from '../../common/MinusSvg';
import PlusSvg from '../../common/PlusSvg';
import Footer from '../util/Footer';
import NavBar from '../util/NavBar';
import { Popup } from '../util/Popup';
import './AddRecipePage.scss';
import AutosizedTextArea from './AutosizedTextArea';
import ImageUploadBox from './ImageUploadBox';
import useRecipeCategories from './useRecipeCategories';

const host = process.env.REACT_APP_CAKEHOUSE_BACKEND_URL;

export default function AddRecipePage() {
	const [categoryNames] = useRecipeCategories();
	const [ingredientNames, setIngredientNames] = useState([]);
	const [unitNames, setUnitNames] = useState([]);
	const [success, setSuccess] = useState(null);

	const [name, setName] = useState('');
	const [desc, setDesc] = useState('');
	const [prep, setPrep] = useState('');
	const [imageB64, setImageB64] = useState('');
	const [category, setCategory] = useState('');
	const [ingredients, setIngredients] = useState([]);

	useEffect(() => {
		axios.get(`${host}/recipes/ingredients`).then(res => {
			setIngredientNames(res.data.map(ingr => ingr['name']));
			setUnitNames(res.data.map(ingr => ingr['unit']));
			setIngredients([
				{
					name: res.data[0].name,
					unit: res.data[0].unit,
					quantity: 0,
				},
			]);
		});
	}, []);

	const handleSubmit = () => {
		axios
			.post(
				`${host}/recipes/upload`,
				{
					name: name,
					desc: desc,
					imageB64: imageB64.substring(23),
					prepMethod: prep,
					category: category ? category : categoryNames[0],
					ingredients: ingredients,
				},
				{
					headers: {
						Authorization: sessionStorage.getItem('jwtToken'),
					},
				}
			)
			.then(() => {
				setSuccess(true);
			})
			.catch(err => setSuccess(false));
	};

	return (
		<div className='add-recipe-page'>
			<NavBar />
			<div className='content-wrapper'>
				<div className='recipe-header'>
					<input
						className='name-input'
						type='text'
						placeholder='Type recipe name here'
						value={name}
						onChange={e => setName(e.target.value)}
					/>
					<input
						className='desc-input'
						type='text'
						placeholder='Type recipe short description here'
						value={desc}
						onChange={e => setDesc(e.target.value)}
					/>
				</div>
				<ImageUploadBox value={imageB64} onUpload={setImageB64} />
				<AutosizedTextArea value={prep} onChange={newPrep => setPrep(newPrep)} />
				<table className='ingredient-list'>
					<thead>
						<tr>
							<th></th>
							<th>Ingredient</th>
							<th>Unit</th>
							<th>Quantity</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						{ingredients?.map((ingredient, index) => {
							return (
								<Row
									key={index}
									ingredientNames={ingredientNames}
									unitNames={unitNames}
									ingredient={ingredient}
									id={index}
									onChange={updatedIngredient => {
										let ingredientsCopy = [...ingredients];
										ingredientsCopy[index] = updatedIngredient;
										setIngredients(ingredientsCopy);
									}}
									onAdd={() => {
										const left = ingredients.slice(0, index + 1);
										const right = ingredients.slice(index + 1, ingredients.length);
										const newIngredient = {
											name: ingredientNames[0],
											unit: unitNames[0],
											quantity: 0,
										};
										setIngredients([...left, newIngredient, ...right]);
									}}
									onDelete={() => {
										if (ingredients.length === 1) return;
										const left = ingredients.slice(0, index);
										const right = ingredients.slice(index + 1, ingredients.length);
										setIngredients([...left, ...right]);
									}}
								/>
							);
						})}
					</tbody>
				</table>
				<div className='category-select-wrapper'>
					<select
						className='category-select'
						id='category-select'
						value={category}
						onChange={e => {
							setCategory(e.target.value);
						}}
					>
						{categoryNames.map((category, index) => {
							return (
								<option value={category} key={index}>
									{category}
								</option>
							);
						})}
					</select>
				</div>
			</div>
			<button className='confirm-button' onClick={handleSubmit}>
				Confirm
			</button>
			<Popup
				success={success}
				timeVisible={2000}
				textOnSuccess={'Recipe successfully created'}
				textOnFail={'Error occured'}
			/>
			<Footer />
		</div>
	);
}

const Row = props => {
	const { id, ingredient, onDelete, onAdd, onChange, ingredientNames, unitNames } = props;
	return (
		<tr key={id}>
			<td onClick={onDelete}>
				<MinusSvg />
			</td>
			<td>
				<Select
					key={'name' + id}
					labels={ingredientNames}
					selected={ingredient.name}
					onSelect={newName => onChange({ ...ingredient, name: newName })}
				/>
			</td>
			<td>
				<Select
					key={'unit' + id}
					labels={unitNames}
					selected={ingredient.unit}
					onSelect={newUnit => onChange({ ...ingredient, unit: newUnit })}
				/>
			</td>
			<td>
				<input
					placeholder='Quantity'
					value={ingredient.quantity}
					onChange={e => onChange({ ...ingredient, quantity: e.target.value })}
					type='number'
					step={0.1}
					min={0}
				></input>
			</td>
			<td onClick={onAdd}>
				<PlusSvg />
			</td>
		</tr>
	);
};

const Select = props => {
	const { labels, selected, onSelect } = props;
	return (
		<select value={selected} onChange={e => onSelect(e.target.value)}>
			{labels.map(label => {
				return <option key={label}>{label}</option>;
			})}
		</select>
	);
};
