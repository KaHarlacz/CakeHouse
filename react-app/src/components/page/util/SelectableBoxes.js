import { useState } from 'react';
import './SelectableBoxes.scss';

export default function SelectableBoxes(props) {
	const { labels, onClick } = props;
	const [selected, setSelected] = useState(0);

	const handleClick = key => {
		setSelected(key);
		if (onClick) onClick(labels[key]);
	};

	return (
		<div className='selectable-boxes'>
			{labels?.map((label, index) => {
				return (
					<p className={`box ${selected === index ? 'selected' : ''}`} onClick={event => handleClick(index)} key={index}>
						{label}
					</p>
				);
			})}
		</div>
	);
}
