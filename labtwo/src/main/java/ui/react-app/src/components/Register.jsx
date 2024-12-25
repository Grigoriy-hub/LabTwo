import React, { useState } from 'react';
import axios from 'axios';


function Register({ onSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('/users', { username, password });
            if (response.status === 200) {
                setSuccess('Пользователь успешно зарегистрирован!');
                setError('');
                onSuccess();
            }
        } catch {
            setError('Пользователь с таким именем уже существует');
            setSuccess('');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2 className="text-lg font-bold mb-4">Регистрация</h2>
            {error && <div className="text-red-500 mb-2">{error}</div>}
            {success && <div className="text-green-500 mb-2">{success}</div>}
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
                className="bg-green-600 text-white py-2 px-4 rounded-md hover:bg-green-700"
            >
                Зарегистрироваться
            </button>
        </form>
    );
}

export default Register;
