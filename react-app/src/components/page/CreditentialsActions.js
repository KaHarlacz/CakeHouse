export function saveLocalCreditentials(creditentials) {
	const storage = window.sessionStorage;
	storage.setItem('username', creditentials.username);
	storage.setItem('password', creditentials.password);
	storage.setItem('id', creditentials.id);
}

export function getCreditentialsBasic() {
	const storage = window.sessionStorage;
	const username = storage.getItem('username');
	const password = storage.getItem('password');
	if (!username || !password) {
		return null;
	}
	// TODO: use modern api
	return btoa(username + ':' + password);
}

export function isUserLoggedIn() {
	return !!getUserId();
}

export function getUserId() {
	const storage = window.sessionStorage;
	return storage.getItem('id');
}

export function getUsername() {
	const storage = window.sessionStorage;
	return storage.getItem('username');
}

export function getBanForCurUser() {
	return new Promise((resolve, reject) => {
		fetch(`${process.env.REACT_APP_CAKEHOUSE_BACKEND_URL}/user/ban`, {
			method: 'GET',
			headers: {
				Accept: '*/*',
				Authorization: getCreditentialsBasic(),
			},
		})
			.then(data => data.json())
			.then(resolve)
			.catch(reject);
	});
}

export async function auth(username, password) {
	return new Promise((resolve, reject) => {
		fetch(`${process.env.REACT_APP_CAKEHOUSE_BACKEND_URL}/user`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				'Accept': '*/*',
			},
			body: JSON.stringify({ username, password }),
		})
			.then(data => data.json())
			.then(data => resolve(data))
			.catch(reject);
	});
}
