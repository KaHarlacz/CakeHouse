import ProfileStat from './ProfileStat';
import './StatsSection.scss';

export default function StatsSection(props) {
	const { bestRecipe, recipesAdded, commentsAdded, userRating } = props;
	return (
		<div className='stats-section'>
			<div className='left'>
				<ProfileStat name='recipes' value={recipesAdded} uom='uploads'></ProfileStat>
				<ProfileStat name='comments' value={commentsAdded} uom='posts'></ProfileStat>
			</div>
			<div className='right'>
				<ProfileStat name='rating' value={userRating} uom='likes'></ProfileStat>
				<ProfileStat name='best recipe' value={bestRecipe}></ProfileStat>
			</div>
		</div>
	);
}
