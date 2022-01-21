import './Vote.scss';

function Vote(props) {
	return (
		<div className='vote'>
			<p>
				<svg
					xmlns='http://www.w3.org/2000/svg'
					class='icon icon-tabler icon-tabler-circle-minus'
					viewBox='0 0 24 24'
					stroke-width='1.5'
					stroke='#ff4500'
					fill='none'
					stroke-linecap='round'
					stroke-linejoin='round'
				>
					<path stroke='none' d='M0 0h24v24H0z' fill='none' />
					<circle cx='12' cy='12' r='9' />
					<line x1='9' y1='12' x2='15' y2='12' />
				</svg>
			</p>
			<p className='votes-counter'>{props.votes}</p>
			<p>
				<svg
					xmlns='http://www.w3.org/2000/svg'
					class='icon icon-tabler icon-tabler-circle-plus'
					viewBox='0 0 24 24'
					stroke-width='1.5'
					stroke='#00b341'
					fill='none'
					stroke-linecap='round'
					stroke-linejoin='round'
				>
					<path stroke='none' d='M0 0h24v24H0z' fill='none' />
					<circle cx='12' cy='12' r='9' />
					<line x1='9' y1='12' x2='15' y2='12' />
					<line x1='12' y1='9' x2='12' y2='15' />
				</svg>
			</p>
		</div>
	);
}

export default Vote;