import './User.scss';
import UserSvg from './UserSvg';

function User(props) {
	return (
		<div className='user'>
            <UserSvg></UserSvg>
			<p className='username'>{props.name}</p>
		</div>
	);
}

export default User;
