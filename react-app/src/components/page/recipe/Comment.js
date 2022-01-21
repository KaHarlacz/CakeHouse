import User from '../../common/User';
import './Comment.scss';

export default function Comment(props) {
	const { username, content } = props;
	return (
		<div className='comment'>
			<div className='user-container'>
				<User name={username} />
			</div>
			<div className='text-section-border'>
				<p className='text-section'>{content}</p>
			</div>
		</div>
	);
}
