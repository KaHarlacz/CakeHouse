import { useRef, useState } from 'react';
import './ImageUploadBox.scss';

const Thumbnail = props => (
	<div className='thumb' datalabel={props.dataLabel} style={{ backgroundImage: props.bgcImageURL }}></div>
);
const Prompt = props => <span className='prompt'>Drop recipe image here</span>;

export default function ImageUploadBox(props) {
	const { onUpload } = props;
	const fileInput = useRef(null);
	const imageUploadBox = useRef(null);
	const [thumbnail, setThumbnail] = useState();
	const [prompt, setPrompt] = useState(<Prompt />);

	return (
		<div
			className='image-upload-box'
			ref={imageUploadBox}
			onDragOver={e => {
				e.preventDefault();
				imageUploadBox.current.classList.add('image-upload-box--over');
			}}
			onDragEnd={e => imageUploadBox.current.classList.remove('image-upload-box--over')}
			onDragLeave={e => imageUploadBox.current.classList.remove('image-upload-box--over')}
			onDrop={e => {
				fileInput.current.files = e.dataTransfer.files;
				const file = fileInput.current.files[0];

				if (prompt) {
					setPrompt(null);
				}

				if (file.type.startsWith('image/')) {
					const reader = new FileReader();
					reader.readAsDataURL(file);
					reader.onload = () => {
						setThumbnail(<Thumbnail dataLabel={file.name} bgcImageURL={`url('${reader.result}')`} />);
						onUpload(reader.result);
					};
				}

				e.preventDefault();
				e.target.classList.remove('image-upload-box--over');
			}}
		>
			{prompt}
			<input type='file' name='recipeImage' className='file-input' ref={fileInput} />
			{thumbnail}
		</div>
	);
}
