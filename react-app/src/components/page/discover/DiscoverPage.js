import React, { useEffect, useState } from 'react';
import NavBar from '../util/NavBar';
import fetchNewestEntries, { fetchCategories } from './../FetchActions';
import { fetchCategoryEntries } from './../FetchActions';
import './DiscoverPage.scss';
import SearchBar from '../util/SearchBar';
import SelectableBoxes from '../util/SelectableBoxes';
import RecipeEntriesContainer from '../../recipe/RecipeEntriesContainer';
import HorizontalSeparator from '../../section-separator/HorizontalSeparator';
import Footer from '../util/Footer';

export default class DiscoverPage extends React.Component {
	constructor(props) {
		super(props);

		this.state = {
			categories: [],
			allEntries: [],
			visibleEntries: [],
			searchLabel: 'Search in All',
			searchInput: '',
		};
	}

	componentDidMount() {
		fetchCategories()
			.then(data => this.setState({ categories: data }))
			.catch(err => console.log(err));

		fetchNewestEntries()
			.then(data => this.setState({ visibleEntries: data, allEntries: data }))
			.catch(err => console.log(err));
	}

	render() {
		let categoriesLabels = ['All'];
		this.state.categories.forEach(cat => {
			console.log(cat);
			categoriesLabels.push(cat.name);
		});

		return (
			<div className='discover-page'>
				<NavBar />
				<div className='content-wrapper'>
					<SearchBar
						placeholder={this.state.searchLabel}
						searchInput={this.state.searchInput}
						onSearch={text => {
							if (!text) {
								this.setState({ visibleEntries: this.state.allEntries });
							}

							const filtered = this.state.allEntries.filter(entry => {
								console.log('entry');
								console.log(entry);
								return entry.name.includes(text) || entry.desc.includes(text) || entry.author.includes(text);
							});

							this.setState({ visibleEntries: filtered });
						}}
					/>
					<SelectableBoxes
						labels={categoriesLabels}
						onClick={label => {
							const newSearchLabel = 'Search in ' + label;
							this.setState({ searchLabel: newSearchLabel, searchInput: '' });

							if (label === 'All') {
								fetchNewestEntries()
									.then(data => this.setState({ visibleEntries: data, allEntries: data }))
									.catch(err => console.log(err));
							} else {
								const categoryId = this.state.categories.filter(el => el.name === label)[0].id;
								fetchCategoryEntries(categoryId)
									.then(data => this.setState({ visibleEntries: data, allEntries: data }))
									.catch(err => console.log(err));
							}
						}}
					/>
					<HorizontalSeparator />
					<div className='result-entries'>
						<RecipeEntriesContainer entries={this.state.visibleEntries} />
					</div>
				</div>
				<Footer />
			</div>
		);
	}

	filterEntries(text) {
		if (!text) {
			this.setState({ visibleEntries: this.state.allEntries });
		}

		const filtered = this.state.allEntries.filter(entry => {
			return entry.name.contains(text) || entry.desc.contains(text) || entry.author.contains(text);
		});

		this.setState({ visibleEntries: filtered });
	}
}
