import './SelectableBoxes.scss';

export default function SelectableBoxes(props) {
	let boxes = [];
	props.labels.forEach((label) => {
		boxes.push(
			<p
				className='box'
				onClick={(event) => {
					handleClick(event.target);
					props.onClick(event.target.innerHTML);
				}}
			>
				{label}
			</p>
		);
	});

	const firstBox = document.querySelector(
		'.selectable-boxes .box:nth-child(1)'
	);

	const selectedBox = document.querySelector(
		'.selectable-boxes [class="box selected"]'
	);

	if (firstBox && !selectedBox) {
		firstBox.classList.add('selected');
	}

	return <div className='selectable-boxes'>{boxes}</div>;
}

function handleClick(clicked) {
	const boxes = document.querySelectorAll('.selectable-boxes .box');
	boxes.forEach((box) => box.classList.remove('selected'));
	clicked.classList.add('selected');
}
