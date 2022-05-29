import { useEffect, useState } from 'react';
import axios from 'axios';

const host = process.env.REACT_APP_CAKEHOUSE_BACKEND_URL;

export default function useRecipesSearch(query, category, pageNumber, size, sortBy) {
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState(false);
	const [recipes, setRecipes] = useState([]);
	const [hasMore, setHasMore] = useState(false);

	useEffect(() => {
		setRecipes([]);
	}, [query, category]);

	useEffect(() => {
		setLoading(true);
		setError(false);
		let cancel;
		axios({
			method: 'GET',
			url: `${host}/recipes/search`,
			params: {
				query: query,
				category: category,
				sortBy: sortBy,
				page: pageNumber,
				size: size,
			},
			cancelToken: new axios.CancelToken(c => (cancel = c)),
		})
			.then(res => {
				setRecipes(prevRecipes => {
					return [...prevRecipes, ...res.data.content];
				});
				setHasMore(!res.data.last);
				setLoading(false);
			})
			.catch(e => {
				if (axios.isCancel(e)) return;
				setError(true);
			});
		// If function is returned it is called after this method is done
		return () => cancel();
	}, [query, category, pageNumber, size, sortBy]);

	return { loading, error, recipes, hasMore };
}
