import { render } from 'react-dom';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import DiscoverPage from './components/page/discover/DiscoverPage';
import MainPage from './components/page/main/MainPage';
import LoginPage from './components/page/login/LoginPage';
import RecipePage from './components/page/recipe/RecipePage';
import WebFont from 'webfontloader';
import ProfilePage from './components/page/profile/ProfilePage';
import AddRecipePage from './components/page/recipe/AddRecipePage';
import ContactPage from './components/page/contact/ContactPage';

const rootElement = document.getElementById('root');

WebFont.load({
	google: {
		families: ['Montserrat:200,300,400,500,700', 'sans-serif'],
	},
});

render(
	<BrowserRouter>
		<Routes>
			<Route path='/' element={<MainPage />} />
			<Route path='/home' element={<MainPage />} />
			<Route path='discover' element={<DiscoverPage />} />
			<Route path='login' element={<LoginPage />} />
			<Route path='profile' element={<ProfilePage />} />
			<Route path='recipe/:id' element={<RecipePage />} />
			<Route path='recipe' element={<AddRecipePage />} />
            <Route path='contact' element={<ContactPage />} />
		</Routes>
	</BrowserRouter>,
	rootElement
);