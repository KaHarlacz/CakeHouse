import './SearchBar.scss';

export default function SearchBar(props) {
	const { onChange, placeholder } = props;
	return (
		<div
			className='search-bar'
			onKeyDown={e => {
				if (onChange) onChange(e);
			}}
		>
			<table className='elements-container'>
				<thead>
					<tr>
						<td>
							<input
								type='text'
								placeholder={placeholder ? placeholder : 'Type search phrase here'}
								className='search-input'
								id='search-input'
							/>
						</td>
						<td className='search-confirm'>
							<svg
								xmlns='http://www.w3.org/2000/svg'
								className='icon icon-tabler icon-tabler-search'
								viewBox='0 0 24 24'
								strokeWidth='2'
								stroke='currentColor'
								fill='none'
								strokeLinecap='round'
								strokeLinejoin='round'
							>
								<path stroke='none' d='M0 0h24v24H0z' fill='none'></path>
								<circle cx='10' cy='10' r='7'></circle>
								<line x1='21' y1='21' x2='15' y2='15'></line>
							</svg>
						</td>
					</tr>
				</thead>
			</table>
		</div>
	);
}
