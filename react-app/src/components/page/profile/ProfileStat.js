import './ProfileStat.scss';

export default function ProfileStat(props) {
	const { name, value, uom } = props;
	return (
		<div className='profile-stat'>
			<p className='stat-name'>{name}</p>
			<p>
				<span className='stat-value'>{value}</span>&nbsp;
				<span className='stat-uom'>{uom}</span>
			</p>
		</div>
	);
}
