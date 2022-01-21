import './Popup.scss';

export const Popup = props => {
	const { success, timeVisible, textOnSuccess, textOnFail } = props;
	const popupBgcColor = success === true 
    ? 'hsla(120, 73%, 47%, 0.363)' 
    : 'rgba(255, 99, 71, 0.432)';
	const popupText = success ? textOnSuccess : textOnFail;

	if (success !== null) {
		const div = document.getElementById('popup');
		const p = document.getElementById('popup-text');
		div.style.visibility = 'visible';
		p.innerHTML = popupText;
		setTimeout(() => (div.style.visibility = 'hidden'), timeVisible);
	}

	return (
		<div className='popup' id='popup' style={{ backgroundColor: popupBgcColor }}>
			<p id='popup-text'></p>
		</div>
	);
};
