import React, { useState } from 'react';
import axios from 'axios';



function Login({ onSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.get(`/users/username/${username}`);
            if (response.status === 200 && response.data.password === password) {
                localStorage.setItem('token', username);
                onSuccess();
            } else {
                setError('Неверное имя пользователя или пароль');
            }
        } catch {
            setError('Неверное имя пользователя или пароль');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2 className="text-lg font-bold mb-4">Вход</h2>
            {error && <div className="text-red-500 mb-2">{error}</div>}
            <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700">Имя пользователя</label>
                <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className="mt-1 p-2 block w-full border rounded-md"
                    required
                />
            </div>
            <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700">Пароль</label>
                <input
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="mt-1 p-2 block w-full border rounded-md"
                    required
                />
            </div>
            <button
                type="submit"
                className="bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700"
            >
                Войти
            </button>
        </form>
    );
}

export default Login;
