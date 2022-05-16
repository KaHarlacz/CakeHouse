import CakeSvg from '../../common/CakeSvg';
import SadFaceSvg from '../../common/SadFaceSvg';
import ActionsSection from './ActionsSection';
import './ProfileBox.scss';
import StatsSection from './StatsSection';
import UserSection from './UserSection';
import { useNavigate } from 'react-router-dom';

export default function ProfileBox(props) {
	const { username, email, bestRecipe, recipesAdded, commentsAdded, userRating } = props.userStats;
	const navigate = useNavigate();
	return (
		<div className='profile-box'>
			<UserSection username={username} email={email} />
			<StatsSection
				bestRecipe={bestRecipe}
				recipesAdded={recipesAdded}
				commentsAdded={commentsAdded}
				userRating={userRating}
			/>
			<ActionsSection
				labels={['Add recipe', 'Logout']}
				svgs={[CakeSvg, SadFaceSvg]}
				actions={[() => navigate('/recipe'), null]}
			/>
		</div>
	);
}
