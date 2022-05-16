import NavBar from '../util/NavBar';
import './LoginPage.scss';
import { saveLocalCreditentials, auth } from '../CreditentialsActions';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import Footer from '../util/Footer';
import { Popup } from '../util/Popup';

export default function LoginPage(props) {
	const navigate = useNavigate();
	const [login, setLogin] = useState({
		authenticated: null,
		userId: null,
	});

	const handleLogin = () => {
		const username = document.querySelector('.username').value;
		const password = document.querySelector('.password').value;
		auth(username, password).then(authData => {
			const { userId } = authData;
			const authenticated = userId !== null;
			if (authenticated) {
				saveLocalCreditentials({ username, password, id: userId });
				setTimeout(() => navigate('/'), 3000);
			}
			setLogin({ authenticated, userId });
		});
	};

	return (
		<div className='login-page'>
			<NavBar />
			<div className='logo-img'></div>
			<div className='username-input'>
				<svg
					xmlns='http://www.w3.org/2000/svg'
					class='icon icon-tabler icon-tabler-user-circle'
					viewBox='0 0 24 24'
					stroke-width='2'
					stroke='currentColor'
					fill='none'
					stroke-linecap='round'
					stroke-linejoin='round'
				>
					<path stroke='none' d='M0 0h24v24H0z' fill='none'></path>
					<circle cx='12' cy='12' r='9'></circle>
					<circle cx='12' cy='10' r='3'></circle>
					<path d='M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855'></path>
				</svg>
				<input type='text' placeholder='username' className='username' />
			</div>
			<div className='password-input'>
				<svg
					xmlns='http://www.w3.org/2000/svg'
					class='icon icon-tabler icon-tabler-lock-open'
					viewBox='0 0 24 24'
					stroke-width='2'
					stroke='currentColor'
					fill='none'
					stroke-linecap='round'
					stroke-linejoin='round'
				>
					<path stroke='none' d='M0 0h24v24H0z' fill='none'></path>
					<rect x='5' y='11' width='14' height='10' rx='2'></rect>
					<circle cx='12' cy='16' r='1'></circle>
					<path d='M8 11v-5a4 4 0 0 1 8 0'></path>
				</svg>
				<input type='password' placeholder='password' className='password'></input>
			</div>
			<button className='submit-button' onClick={() => handleLogin()}>
				log in
			</button>
			<Popup
				success={login.authenticated}
				timeVisible={3000}
				textOnSuccess={'Successfully authenticated.'}
				textOnFail={'Authentication failed.'}
			/>
			<Footer />
		</div>
	);
}
