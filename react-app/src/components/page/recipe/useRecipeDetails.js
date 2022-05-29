import axios from 'axios';
import { useEffect, useState } from 'react';

const host = process.env.REACT_APP_CAKEHOUSE_BACKEND_URL;

export default function useRecipeDetails(recipeId) {
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(false);
	const [recipe, setRecipe] = useState({});

	useEffect(() => {
		setLoading(true);
		setError(false);
		axios({
			method: 'GET',
			url: `${host}/recipes/${recipeId}`,
		})
			.then(res => {
				console.log(res);

				setRecipe(res.data);
				setLoading(false);
			})
			.catch(err => {
				setError(err);
			});
	}, [recipeId]);

	return { loading, error, recipe, setRecipe };
}
