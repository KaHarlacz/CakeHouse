import './Footer.scss';
export default function Footer(props) {
	return (
		<footer>
			<div className='top-footer'>
				<div className='newsletter'>
					<div className='content'>
						<h4>SING UP FOR OUR NEWSLETTER</h4>
						<p>Type your email bellow</p>
						<input />
					</div>
				</div>
				<div className='follow-us'>
					<div className='content'>
						<h4>FOLLOW US</h4>
						<ul>
							<li>Facebook</li>
							<li>Twitter</li>
							<li>Instagram</li>
						</ul>
					</div>
				</div>
				<div className='about-us'>
					<div className='content'>
						<h4>ABOUT CAKEHOUSE</h4>
						<ul>
							<li>Team</li>
							<li>Career</li>
							<li>Support us</li>
						</ul>
					</div>
				</div>
			</div>
			<div className='copyright'>
				<p>&copy; Kamil Harłacz 2022</p>
			</div>
		</footer>
	);
}
