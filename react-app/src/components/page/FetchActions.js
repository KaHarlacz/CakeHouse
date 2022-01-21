export default function fetchNewestEntries() {
	return fetchEntries('http://localhost:8080/recipes/newest');
}

export function fetchTopRatedEntries() {
	// TODO
	return fetchEntries('');
}

export function fetchCategoryEntries(categoryId) {
	return fetchEntries(`http://localhost:8080/recipes/category/${categoryId}`);
}

export function fetchCategories() {
	return new Promise((resolve, reject) => {
		fetch('http://localhost:8080/categories')
			.then(data => data.json())
			.then(resolve)
			.catch(reject);
	});
}

// TODO: Fetch comments for recipe

export function fetchRecipe(id) {
	return new Promise((resolve, reject) => {
		fetch(`http://localhost:8080/recipe/${id}`)
			.then(res => res.json())
			.then(data => transformRecipe(data))
			.then(resolve)
			.catch(reject);
	});
}

function transformRecipe(data) {
	return {
		id: data.id,
		name: data.name,
		desc: data.desc,
		author: data.author,
		prepMethod: data.prepMethod,
		rating: data.rating,
		dateAdded: data.dateAdded.substring(0, 10),
		imageB64: base64ToImage(data.imageB64),
		ingredients: data.ingredients,
		categories: data.categories,
		comments: data.comments,
	};
}

function fetchEntries(url) {
	const encodedCreditentials = btoa('James123:1234');

	console.log(encodedCreditentials);
	return new Promise((resolve, reject) => {
		fetch(url, {
			method: 'GET',
			headers: {
				Authorization: 'Basic ' + encodedCreditentials,
			},
		})
			.then(res => res.json())
			.then(entries => transformEntries(entries))
			.then(resolve)
			.catch(reject);
	});
}

function transformEntries(data) {
	let result = [];
	data.forEach(entry => {
		result.push({
			id: entry.id,
			image: base64ToImage(entry.image),
			name: entry.name,
			desc: entry.desc,
			author: entry.author,
			dateAdded: entry.dateAdded.substring(0, 10),
			rating: entry.rating,
		});
	});
	return result;
}

function base64ToImage(b64String) {
	return `data:image/png;base64,${b64String}`;
}
