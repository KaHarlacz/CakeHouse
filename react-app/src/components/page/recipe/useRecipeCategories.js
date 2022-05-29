import axios from 'axios';
import { useEffect, useState } from 'react';

const host = process.env.REACT_APP_CAKEHOUSE_BACKEND_URL;

export default function useRecipeCategories() {
	const [categories, setCategories] = useState([]);

	useEffect(() => {
		axios({
			method: 'GET',
			url: `${host}/recipes/categories`,
		}).then(res => {
			console.log(res);
			setCategories(prevCategories => {
				return [...prevCategories, ...res.data];
			});
		});
	}, []);

	return [categories, setCategories];
}
