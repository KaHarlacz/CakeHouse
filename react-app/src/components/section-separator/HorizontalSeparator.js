import './HorizontalSeparator.scss';

function HorizontalSeparator(props) {
	const { text } = props;
	if (text) {
		return (
			<div className='horizontal-separator'>
				<div className='text-separator'>{text}</div>
				<div className='line-separator'></div>
			</div>
		);
	} else {
		return <div className='line-separator'></div>;
	}
}

export default HorizontalSeparator;
