import NavBar from '../util/NavBar';
import ProfileBox from './ProfileBox';
import './ProfilePage.scss';
import { useEffect, useState } from 'react';
import Footer from '../util/Footer';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const host = process.env.REACT_APP_CAKEHOUSE_BACKEND_URL;

export default function ProfilePage() {
	const navigate = useNavigate();
	const [userStats, setUserStats] = useState({});

	const jwtToken = sessionStorage.getItem('jwtToken');
	const jwtTokenStillValid = sessionStorage.getItem('expTime') > new Date().toISOString();
	if (!jwtToken || !jwtTokenStillValid) {
		navigate('/login');
	}

	useEffect(() => {
		axios
			.get(`${host}/users/${sessionStorage.getItem('username')}/stats`, {
				headers: { Authorization: sessionStorage.getItem('jwtToken') },
			})
			.then(res => setUserStats(res.data));
	}, []);

	return (
		<div className='profile-page'>
			<NavBar />
			<ProfileBox userStats={userStats} />
			<Footer />
		</div>
	);
}
