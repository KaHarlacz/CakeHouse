import './ActionsSection.scss';

const ActionOption = props => {
	const { label, Svg, action } = props;
	let className = label.toLowerCase().replace(' ', '-');
	className += ' action-option';
	return (
		<div className={className} onClick={action}>
			<Svg></Svg>
			<p>{label}</p>
		</div>
	);
};

export default function ActionsSection(props) {
	const { labels, svgs, actions } = props;
	const options = [];
	for (let i = 0; i < labels.length; i++) {
		options.push(<ActionOption label={labels[i]} Svg={svgs[i]} action={actions[i]} key={i} />);
	}
	return <div className='actions-section'>{options}</div>;
}
