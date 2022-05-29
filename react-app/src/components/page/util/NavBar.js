import './NavBar.scss';
import { Link } from 'react-router-dom';

export default function NavBar() {
	return (
		<nav>
			<div className='logo'>
				<h4>CakeHouse.com</h4>
			</div>
			<ul className='nav-links'>
				<li>
					<Link to='/'>Home</Link>
				</li>
				<li>
					<Link to='/discover'>Discover</Link>
				</li>
				<li>
					<Link to='/contact'>Contact</Link>
				</li>
				<li>
					{sessionStorage.getItem('jwtToken') && sessionStorage.getItem('expTime') > new Date().toISOString() ? (
						<Link to={`/profile`}>Profile</Link>
					) : (
						<Link to='/login'>Log in</Link>
					)}
				</li>
			</ul>
			<div className='burger' onClick={navSlide}>
				<div className='line1'></div>
				<div className='line2'></div>
				<div className='line3'></div>
			</div>
		</nav>
	);
}

function navSlide() {
	const burger = document.querySelector('.burger');
	const nav = document.querySelector('.nav-links');
	const navLinks = document.querySelectorAll('.nav-links li');

	nav.classList.toggle('nav-active');
	navLinks.forEach((link, idx) => {
		if (link.style.animation) {
			link.style.animation = '';
		} else {
			link.style.animation = `navLinkFade 0.5s ease forwards ${idx / 7 + 0.6}s`;
		}
	});
	burger.classList.toggle('toggle');
}
