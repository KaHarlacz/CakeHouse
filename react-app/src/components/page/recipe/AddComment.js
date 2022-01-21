import './AddComment.scss';

export default function AddComment(props) {
	const { onAddComment } = props;
	return (
		<div className='add-comment'>
			<textarea type='text' id='comment' />
			<button
				onClick={() => {
					const comment = document.getElementById('comment').value;
					onAddComment(comment);
				}}
			>
				confirm
			</button>
		</div>
	);
}
