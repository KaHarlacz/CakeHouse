import './SideBySideRecipeEntry.scss';

import User from '../common/User';
import Vote from '../common/Vote';
import Date from '../common/Date';
import { Link } from 'react-router-dom';

function SideBySideRecipeEntry(props) {
	const { id, image, name, desc, author, dateAdded, rating } = props.recipe;
	const linkTarget = `/recipe/${id}`;
	return (
		<div className='side-by-side-recipe-entry'>
			<div className='image-container'>
				<img className='image' src={image} width='100%' alt={name} />
			</div>
			<div className='details'>
				<p className='name'>
					<Link to={linkTarget}>{name}</Link>
				</p>
				<p className='desc'>{desc}</p>
				<div className='bottom-panel'>
					<div className='author-date-panel'>
						<User name={author} />
						<Date date={dateAdded} />
					</div>
					<div className='vote-panel'>
						<Vote votes={rating} />
					</div>
				</div>
			</div>
		</div>
	);
}

export default SideBySideRecipeEntry;
