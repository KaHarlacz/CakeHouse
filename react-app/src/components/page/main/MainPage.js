import RecipeEntriesContainer from '../../recipe/RecipeEntriesContainer';
import HeroImage from '../util/HeroImage';
import NavBar from '../util/NavBar';
import HorizontalSeparator from '../../section-separator/HorizontalSeparator';
import fetchNewestEntries from '../FetchActions';
import { useEffect, useState } from 'react';
import './MainPage.scss';
import SimpleRecipeEntry from '../../recipe/SimpleRecipeEntry';
import Footer from '../util/Footer';

function MainPage(props) {
	let [newest, setNewest] = useState([]);

	useEffect(() => {
		fetchNewestEntries()
			.then(data => setNewest(data))
			.catch(err => console.log(err));
	}, [setNewest]);

	return (
		<div className='main-page'>
			<NavBar />
			<HeroImage />
			<div className='content-wrapper'>
				<HorizontalSeparator text='recently added' />
				<div className='newest-container'>
					<div className='newest-left'>
						<RecipeEntriesContainer entries={newest.slice(0, 1)} entryType='simple' />
					</div>
					<div className='newest-right'>
						<RecipeEntriesContainer entries={newest.slice(1, 4)} />
					</div>
				</div>
				<HorizontalSeparator text='top rated' />
				<div className='top-rated'>
					<RecipeEntriesContainer entries={newest.slice(4, 8)} />
					<RecipeEntriesContainer entries={newest.slice(8, 12)} />
				</div>
			</div>
			<Footer />
		</div>
	);
}

export default MainPage;
