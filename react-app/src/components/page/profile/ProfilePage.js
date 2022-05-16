import NavBar from '../util/NavBar';
import ProfileBox from './ProfileBox';
import './ProfilePage.scss';
import { useEffect, useState } from 'react';
import { fetchUserStats } from '../FetchActions';

export default function ProfilePage(props) {
	let [userStats, setUserStats] = useState({});

	useEffect(() => {
		fetchUserStats()
			.then(data => setUserStats(data))
			.catch(err => console.log(err));
	}, [setUserStats]);

	return (
		<div className='profile-page'>
			<NavBar />
			<ProfileBox userStats={userStats} />
		</div>
	);
}
