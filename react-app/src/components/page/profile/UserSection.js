import User from '../../common/User';
import './UserSection.scss';

export default function UserSection(props) {
	const { username, email } = props;
	return (
		<div className='user-section'>
			<div className='basic-info'>
				<User name={username} />
				<p className='email'>{email}</p>
			</div>
		</div>
	);
}
