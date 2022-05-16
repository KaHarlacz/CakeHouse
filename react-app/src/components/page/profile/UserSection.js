import UserSvg from '../../common/UserSvg';
import './UserSection.scss';

export default function UserSection(props) {
	const { username, email } = props;
	return (
		<div className='user-section'>
			<UserSvg></UserSvg>
			<div className='basic-info'>
				<p className='name'>{username}</p>
				<p className='email'>{email}</p>
			</div>
		</div>
	);
}
