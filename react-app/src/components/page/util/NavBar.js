import './NavBar.scss';
import { Link } from 'react-router-dom';
import { getCreditentialsBasic, getUserId } from './../CreditentialsActions';

export default function NavBar(props) {
	let userLink = (
		<li>
			<Link to='/login'>Log in</Link>
		</li>
	);

	if (getCreditentialsBasic()) {
		const userId = getUserId();
		userLink = (
			<li>
				<Link to={`/profile/${userId}`}>Profile</Link>
			</li>
		);
	}

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
					<a href='#'>Contact</a>
				</li>
				{userLink}
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

/* <svg
					xmlns='http://www.w3.org/2000/svg'
					className='icon icon-tabler icon-tabler-cookie'
					viewBox='0 0 24 24'
					stroke-width='2'
					stroke='currentColor'
					fill='none'
					stroke-linecap='round'
					stroke-linejoin='round'
				>
					<path stroke='none' d='M0 0h24v24H0z' fill='none'></path>
					<path d='M8 13v.01'></path>
					<path d='M12 17v.01'></path>
					<path d='M12 12v.01'></path>
					<path d='M16 14v.01'></path>
					<path d='M11 8v.01'></path>
					<path d='M13.148 3.476l2.667 1.104a4 4 0 0 0 4.656 6.14l.053 .132a3 3 0 0 1 0 2.296c-.497 .786 -.838 1.404 -1.024 1.852c-.189 .456 -.409 1.194 -.66 2.216a3 3 0 0 1 -1.624 1.623c-1.048 .263 -1.787 .483 -2.216 .661c-.475 .197 -1.092 .538 -1.852 1.024a3 3 0 0 1 -2.296 0c-.802 -.503 -1.419 -.844 -1.852 -1.024c-.471 -.195 -1.21 -.415 -2.216 -.66a3 3 0 0 1 -1.623 -1.624c-.265 -1.052 -.485 -1.79 -.661 -2.216c-.198 -.479 -.54 -1.096 -1.024 -1.852a3 3 0 0 1 0 -2.296c.48 -.744 .82 -1.361 1.024 -1.852c.171 -.413 .391 -1.152 .66 -2.216a3 3 0 0 1 1.624 -1.623c1.032 -.256 1.77 -.476 2.216 -.661c.458 -.19 1.075 -.531 1.852 -1.024a3 3 0 0 1 2.296 0z'></path>
				</svg>*/