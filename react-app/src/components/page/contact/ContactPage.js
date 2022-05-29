import { Accordion, ListGroup, Badge } from 'react-bootstrap';
import Footer from '../util/Footer';
import NavBar from '../util/NavBar';

import 'bootstrap/dist/css/bootstrap.min.css';
import './ContactPage.scss';
import PhoneSvg from './PhoneSvg';
import MailSvg from './MailSvg';
import parallaxbg from './parallax-bg.png';
import parallaxfg from './parallax-fg.png';

export default function ContactPage() {
	return (
		<div className='contact-page'>
			<NavBar />
			<header>
				<img src={parallaxbg} className='parallax-bg' alt='mountains in the background' />
				<img src={parallaxfg} className='parallax-fg' alt='person with lifted hands' />
				<h1 className='title'>Reach the top with us!</h1>
			</header>
			<div className='content-wrapper'>
				<section>
					<h2 className='section-header'>Join us today!</h2>
					<p className='join-us-paragraph'>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
						dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
						ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
						nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit
						anim id est laborum.
					</p>
					<h2 className='section-header'>Job offers</h2>
					<JobOffers />
					<h2 className='section-header'>Benefits of joining us</h2>
					<BenefitsAccordion />
					<h2 className='section-header'>Contact us</h2>
					<table className='contact-table'>
						<tbody>
							<tr>
								<td>
									<PhoneSvg />
								</td>
								<td>+48 790 736 213</td>
							</tr>
							<tr>
								<td>
									<MailSvg />
								</td>
								<td>cakehouse@gmail.com</td>
							</tr>
						</tbody>
					</table>
				</section>
				<Footer />
			</div>
		</div>
	);
}

const BenefitsAccordion = () => {
	return (
		<Accordion className='col-sm-8' defaultActiveKey='0'>
			<Accordion.Item eventKey='0'>
				<Accordion.Header>Salary</Accordion.Header>
				<Accordion.Body>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
					magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
					pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
					est laborum.
				</Accordion.Body>
			</Accordion.Item>
			<Accordion.Item eventKey='1'>
				<Accordion.Header>Satisfaction</Accordion.Header>
				<Accordion.Body>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
					magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
					pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
					est laborum.
				</Accordion.Body>
			</Accordion.Item>
			<Accordion.Item eventKey='2'>
				<Accordion.Header>Health</Accordion.Header>
				<Accordion.Body>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
					magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
					pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id
					est laborum.
				</Accordion.Body>
			</Accordion.Item>
		</Accordion>
	);
};

const JobOffers = () => {
	return (
		<ListGroup className='col-sm-6' as='ol' numbered>
			<ListGroup.Item as='li' className='d-flex justify-content-between align-items-start'>
				<div className='ms-2 me-auto'>
					<div className='fw-bold'>Team Leader</div>
					Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
				</div>
				<Badge bg='primary' pill>
					1
				</Badge>
			</ListGroup.Item>
			<ListGroup.Item as='li' className='d-flex justify-content-between align-items-start'>
				<div className='ms-2 me-auto'>
					<div className='fw-bold'>Senior React Developer</div>
					Cupidatat non proident, sunt in culpa qui officia deserunt
				</div>
				<Badge bg='primary' pill>
					1
				</Badge>
			</ListGroup.Item>
			<ListGroup.Item as='li' className='d-flex justify-content-between align-items-start'>
				<div className='ms-2 me-auto'>
					<div className='fw-bold'>Junior DevOps</div>
					Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
				</div>
				<Badge bg='primary' pill>
					5
				</Badge>
			</ListGroup.Item>
		</ListGroup>
	);
};
