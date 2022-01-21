import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { fetchRecipe } from '../FetchActions';
import NavBar from '../util/NavBar';
import Comment from './Comment';
import AddComment from './AddComment';
import './RecipePage.scss';
import { getCreditentialsBasic, getUsername, getBanForCurUser, isUserLoggedIn } from '../CreditentialsActions';
import Footer from '../util/Footer';
import SelectableBoxes from '../util/SelectableBoxes';
import User from '../../common/User';
import Date from '../../common/Date';
import Vote from '../../common/Vote';

export default function RecipePage() {
	const { id: recipeId } = useParams();
	let [recipe, setRecipe] = useState({
		id: -1,
		name: '',
		desc: '',
		author: '',
		prepMethod: '',
		rating: 0,
		dateAdded: '',
		imageB64: '',
		ingredients: [],
		categories: [],
		comments: [],
	});
	let [user, setUser] = useState({
		isBanned: false,
		addCommentPopup: null,
	});

	useEffect(() => {
		fetchRecipe(recipeId).then(recipe => setRecipe(recipe));
	}, [setRecipe]);

	useEffect(() => {
		getBanForCurUser().then(ban => setUser({ isBanned: ban.banned }));
	}, [setUser]);

	const handleAddComment = commentContent => {
		fetch('http://localhost:8080/recipe/comment', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				'Accept': '*/*',
				'Authorization': getCreditentialsBasic(),
			},
			body: JSON.stringify({
				recipeId: recipe.id,
				content: commentContent,
			}),
		})
			.then(res => res.json())
			.then(resJson => {
				let popupBgcColor = resJson.success ? 'hsla(120, 73%, 47%, 0.363)' : 'rgba(255, 99, 71, 0.432)';
				if (resJson.success) {
					const comment = {
						author: getUsername(),
						content: commentContent,
					};
					const updatedComments = recipe.comments.concat([comment]);
					const updatedRecipe = { ...recipe, comments: updatedComments };
					setRecipe(updatedRecipe);
				}
				const popup = document.querySelector('.add-comment-popup');
				const status = document.getElementById('add-comment-status');
				popup.setAttribute('style', `background-color:${popupBgcColor};visibility:visible`);
				status.innerHTML = resJson.message;
				setTimeout(() => {
					popup.setAttribute('style', 'visibility:none');
					if (!resJson.success) {
						window.location.reload();
					}
				}, 5000);
			})
			.catch(err => console.log(err));
	};

	let ingredients = [];
	recipe.ingredients.forEach(ingredient => {
		const { name, quantity, unit } = ingredient;
		ingredients.push(
			<li>
				{name}: {quantity} {unit}
			</li>
		);
	});

	let comments = [];
	recipe.comments.map(comment => {
		const { author, content } = comment;
		comments.push(<Comment username={author} content={content} />);
	});

	let categoryNames = [];
	recipe.categories.forEach(cat => {
		categoryNames.push(cat.name);
	});

	let addCommentSection = null;
	if (isUserLoggedIn() && !user.isBanned) {
		addCommentSection = (
			<div className='add-comment-section'>
				<h3>Leave a comment</h3>
				<AddComment onAddComment={handleAddComment} />
			</div>
		);
	}

	return (
		<div className='recipe-page'>
			<NavBar />
			<div className='content-wrapper'>
				<div className='recipe-header'>
					<h2 className='name'>{recipe.name}</h2>
					<h3 className='description'>{recipe.desc}</h3>
					<div className='recipe-header-bottom'>
						<User name={recipe.author} />
						<Date date={recipe.dateAdded} />
						<Vote votes={recipe.rating} />
					</div>
				</div>
				<div className='image'>
					<img src={recipe.imageB64} />
				</div>
				<div className='preparation'>
					<h3>Preparation</h3>
					<p>{recipe.prepMethod}</p>
				</div>
				<div className='ingredients'>
					<h3>Ingredients</h3>
					<ul>{ingredients}</ul>
				</div>
				<div className='categories'>
					<h3>Categories</h3>
					<SelectableBoxes labels={categoryNames} />
				</div>
				<div className='comments'>
					<h3>Comments</h3>
					{comments}
				</div>
				{addCommentSection}
				<div className='add-comment-popup'>
					<p id='add-comment-status'></p>
				</div>
			</div>
			<Footer />
		</div>
	);
}
