import './RecipePreview.scss';

import User from '../common/User';
import Vote from '../common/Vote';
import Date from '../common/Date';
import { Link } from 'react-router-dom';

function RecipePreview(props) {
	const { imageB64, name, desc, author, dateAdded, rating, id} = props.recipe;
	const linkTarget = `/recipe/${id}`;
	return (
		<div className={'recipe-preview recipe-preview-' + (props.layout ? props.layout : 'top-bottom')}>
			<img src={`data:image/jpeg;charset=utf-8;base64,${imageB64}`} alt='Dish' />
			<h3 className='recipe-name'>
				<Link to={linkTarget}>{name}</Link>
			</h3>
			<p className='desc'>{desc}</p>
			<User name={author} />
			<Date date={dateAdded.substring(0, 10)} />
			<Vote votes={rating} />
		</div>
	);
}

export default RecipePreview;
