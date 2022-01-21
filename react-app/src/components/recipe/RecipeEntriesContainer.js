import { useEffect, useState } from 'react';
import SideBySideRecipeEntry from './SideBySideRecipeEntry';
import SimpleRecipeEntry from './SimpleRecipeEntry';
import './RecipeEntriesContainer.scss';

export default function RecipeEntriesContainer(props) {
	let { entries, entryType } = props;
	return (
		<div className='recipe-entries-container'>
			{entries.map(entry => {
				if (entryType === 'simple') {
					return <SimpleRecipeEntry recipe={entry} />;
				} else {
					return <SideBySideRecipeEntry recipe={entry} />;
				}
			})}
		</div>
	);
}
