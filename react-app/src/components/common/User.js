import './User.scss';

function User(props) {
	return (
		<div className='user'>
			<svg
				xmlns='http://www.w3.org/2000/svg'
				className='icon'
				stroke-width='2'
				stroke='#6f32be'
				fill='none'
				viewBox='0 0 24 24'
				stroke-linecap='round'
				stroke-linejoin='round'
			>
				<path
					alignmentBaseline='middle'
					stroke='none'
					d='M0 0h24v24H0z'
					fill='none'
				/>
				<circle alignmentBaseline='middle' cx='12' cy='7' r='4' />
				<path
					alignmentBaseline='middle'
					d='M6 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2'
				/>
			</svg>
			<p className='username'>{props.name}</p>
		</div>
	);
}

export default User;
