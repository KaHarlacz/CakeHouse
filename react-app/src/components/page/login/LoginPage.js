import NavBar from '../util/NavBar';
import './LoginPage.scss';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import Footer from '../util/Footer';
import { Popup } from '../util/Popup';
import axios from 'axios';

const host = process.env.REACT_APP_CAKEHOUSE_BACKEND_URL;

export default function LoginPage() {
	const navigate = useNavigate();
	const [username, setUsername] = useState('');
	const [password, setPassword] = useState('');
	const [success, setSuccess] = useState(null);

	const handleLogin = () => {
		axios
			.post(`${host}/auth/login`, { username: username, password: password })
			.then(res => {
				sessionStorage.setItem('username', username);
				sessionStorage.setItem('jwtToken', res.data.jwtToken);
				sessionStorage.setItem('expTime', res.data.expTime);
				setTimeout(() => navigate('/'), 3000);
				setSuccess(true);
			})
			.catch(err => {
				console.log(err);
				setSuccess(false);
			});
	};

	return (
		<div className='login-page'>
			<NavBar />
			<div className='logo-img'></div>
			<div className='username-input'>
				<svg
					xmlns='http://www.w3.org/2000/svg'
					className='icon icon-tabler icon-tabler-user-circle'
					viewBox='0 0 24 24'
					strokeWidth='2'
					stroke='currentColor'
					fill='none'
					strokeLinecap='round'
					strokeLinejoin='round'
				>
					<path stroke='none' d='M0 0h24v24H0z' fill='none'></path>
					<circle cx='12' cy='12' r='9'></circle>
					<circle cx='12' cy='10' r='3'></circle>
					<path d='M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855'></path>
				</svg>
				<input
					type='text'
					placeholder='username'
					className='username'
					value={username}
					onChange={e => setUsername(e.target.value)}
				/>
			</div>
			<div className='password-input'>
				<svg
					xmlns='http://www.w3.org/2000/svg'
					className='icon icon-tabler icon-tabler-lock-open'
					viewBox='0 0 24 24'
					strokeWidth='2'
					stroke='currentColor'
					fill='none'
					strokeLinecap='round'
					strokeLinejoin='round'
				>
					<path stroke='none' d='M0 0h24v24H0z' fill='none'></path>
					<rect x='5' y='11' width='14' height='10' rx='2'></rect>
					<circle cx='12' cy='16' r='1'></circle>
					<path d='M8 11v-5a4 4 0 0 1 8 0'></path>
				</svg>
				<input
					type='password'
					placeholder='password'
					className='password'
					value={password}
					onChange={e => setPassword(e.target.value)}
				></input>
			</div>
			<button className='submit-button' onClick={() => handleLogin()}>
				log in
			</button>
			<Popup
				success={success}
				timeVisible={2000}
				textOnSuccess={'Successfully authenticated.'}
				textOnFail={'Authentication failed.'}
			/>
			<Footer />
		</div>
	);
}
