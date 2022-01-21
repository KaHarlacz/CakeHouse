import './SimpleRecipeEntry.scss';

import User from '../common/User';
import Vote from '../common/Vote';
import Date from '../common/Date';
import { Link } from 'react-router-dom';

function SimpleRecipeEntry(props) {
	const { id, image, name, desc, author, dateAdded, rating } = props.recipe;
	const linkTarget = `/recipe/${id}`;
	return (
		<div className='simple-recipe-entry'>
			<img className='image' src={image} width='100%' alt='pizza' />
			<div className='details'>
				<p className='recipe-name'>
					<Link to={linkTarget}>{name}</Link>
				</p>
				<p className='desc'>{desc}</p>
				<div className='bottom-panel'>
					<User name={author} />
					<Date date={dateAdded} />
					<Vote votes={rating} />
				</div>
			</div>
		</div>
	);
}

export default SimpleRecipeEntry;
