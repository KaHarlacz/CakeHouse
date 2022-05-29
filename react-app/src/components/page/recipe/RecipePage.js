import { useParams } from 'react-router-dom';
import NavBar from '../util/NavBar';
import Comment from './Comment';
import AddComment from './AddComment';
import './RecipePage.scss';
import Footer from '../util/Footer';
import SelectableBoxes from '../util/SelectableBoxes';
import User from '../../common/User';
import Date from '../../common/Date';
import Vote from '../../common/Vote';
import useRecipeDetails from './useRecipeDetails';
import axios from 'axios';
import { Popup } from '../util/Popup';
import { useState } from 'react';

const host = process.env.REACT_APP_CAKEHOUSE_BACKEND_URL;

export default function RecipePage() {
	const { id: recipeId } = useParams();
	const { recipe, setRecipe } = useRecipeDetails(recipeId);
	const [commentSuccess, setCommentSuccess] = useState(null);

	function handleAddComment(comment) {
		axios
			.post(
				`${host}/recipes/${recipe.id}/comment`,
				{
					comment: comment,
					username: sessionStorage.getItem('username'),
				},
				{
					headers: {
						Authorization: sessionStorage.getItem('jwtToken'),
					},
				}
			)
			.then(res => {
				setRecipe({
					...recipe,
					comments: [...recipe.comments, { author: sessionStorage.getItem('username'), content: comment }],
				});
			})
			.catch(err => {
				console.log(err);
				setCommentSuccess(false);
			});
	}

	return (
		<div className='recipe-page'>
			<NavBar />
			<div className='content-wrapper'>
				<div className='recipe-header'>
					<h2 className='name'>{recipe?.name}</h2>
					<h3 className='description'>{recipe?.desc}</h3>
					<div className='recipe-header-bottom'>
						<User name={recipe?.author} />
						<Date date={recipe.dateAdded?.substring(0, 10)} />
						<Vote votes={recipe?.rating} />
					</div>
				</div>
				<div className='image'>
					<img src={`data:image/jpeg;charset=utf-8;base64,${recipe?.imageB64}`} alt='Dish' />
				</div>
				<div className='preparation'>
					<h3>Preparation</h3>
					<p>{recipe?.prepMethod}</p>
				</div>
				<div className='ingredients'>
					<h3>Ingredients</h3>
					<ul>
						{recipe.ingredients?.map((ingredient, index) => {
							const { name, quantity, unit } = ingredient;
							return (
								<li key={index}>
									{name}: {quantity} {unit}
								</li>
							);
						})}
					</ul>
				</div>
				<div className='categories'>
					<h3>Categories</h3>
					<SelectableBoxes labels={[recipe.categories]} />
				</div>
				<div className='comments'>
					<h3>Comments</h3>
					{recipe.comments?.map((comment, index) => {
						const { author, content } = comment;
						return <Comment username={author} content={content} key={index} />;
					})}
				</div>
				<div className='add-comment-section'>
					<h3>Leave a comment</h3>
					<AddComment onAddComment={handleAddComment} />
					<Popup
						success={commentSuccess}
						timeVisible={3000}
						textOnSuccess={'Comment successfully added'}
						textOnFail={'Some error occured'}
					/>
				</div>
			</div>
			<Footer />
		</div>
	);
}
