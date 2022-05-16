import { useRef } from 'react';
import './AutosizedTextArea.scss';

export default function AutosizedTextArea() {
	const textArea = useRef();
	return (
		<textarea
			className='autosized-text-area'
			placeholder='Type preparation here'
			rows={1}
			onKeyDown={() => autosize(textArea.current)}
			ref={textArea}
		></textarea>
	);
}

function autosize(el) {
	setTimeout(function () {
		el.style.cssText = 'height:auto; padding:0';
		el.style.cssText = 'height:' + (el.scrollHeight + 30) + 'px';
	}, 10);
}
