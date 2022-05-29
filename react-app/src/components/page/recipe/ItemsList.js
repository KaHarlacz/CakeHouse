import MinusSvg from '../../common/MinusSvg';
import PlusSvg from '../../common/PlusSvg';
import './ItemsList.scss';

const Row = props => (
	<tr>
		<td onClick={() => props.minusAction(props.rowId)}>{props.minusButton ? <MinusSvg /> : null}</td>
		{props.values.map((val, idx) => (
			<td>
				<input type='text' placeholder={props.placeholder} value={val} onChange={e => props.changeAction(idx, e)} />
			</td>
		))}
		<td onClick={props.plusAction}>{props.plusButton ? <PlusSvg /> : null}</td>
	</tr>
);

export default function ItemsList(props) {
	let { labels, items, placeholder, editable, setItems } = props;
	let itemRows = [];

	if (editable && items.length == 0) {
		setItems([new Array(labels.length).fill('')]);
	}

	const addRow = () => {
		setItems([...items, new Array(labels.length).fill('')]);
	};

	const deleteRow = indexToDelete => {
		setItems(items.filter((val, idx) => idx !== indexToDelete));
	};

	for (let i = 0; i < items.length; i++) {
		itemRows.push(
			<Row
				rowId={i}
				values={items[i]}
				changeAction={(idx, event) => {
					items[i][idx] = event.target.value;
					setItems(items);
				}}
				minusButton={editable & (i !== items.length - 1)}
				plusButton={editable & (i === items.length - 1)}
				minusAction={editable & (i !== items.length - 1) ? deleteRow : () => {}}
				plusAction={editable & (i === items.length - 1) ? addRow : () => {}}
				placeholder={placeholder}
			/>
		);
	}

	return (
		<table className={props.className + ' items-list'}>
			<tr className='row'>
				<th></th>
				{labels.map(label => (
					<th>{label}</th>
				))}
				<th></th>
			</tr>
			{itemRows}
		</table>
	);
}
