import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./style.css";

export const Auth_block = ({ onLogin }) => {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [message, setMessage] = useState("");
	const navigate = useNavigate();

	const handleLogin = async (event) => {
		event.preventDefault();

		try {
			const response = await fetch("http://localhost:8080/api/auth/login", {
				method: "POST",
				headers: {
					"Content-Type": "application/x-www-form-urlencoded",
				},
				body: new URLSearchParams({ email, password }),
			});

			if (response.ok) {
				const result = await response.text();
				if (result === "Успешная аутентификация") {
					// Сохраняем данные в localStorage
					localStorage.setItem("isAuthenticated", "true");
					localStorage.setItem("userEmail", email);

					// Обновляем состояние авторизации в App
					onLogin(true, email);

					// Переход на главную страницу
					navigate("/home");
				} else {
					setMessage(result);
				}
			} else {
				setMessage("Ошибка при аутентификации");
			}
		} catch (error) {
			setMessage("Ошибка соединения с сервером");
		}
	};

	return (
		<div className="flex flex-col items-center mt-2">
			<div
				className="w-[400px] bg-white/75 text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[500px] flex flex-col items-center justify-center backdrop-blur-lg">
				<h1 className="text-3xl font-title mb-6">Вход</h1>
				<form
					className="w-[300px] flex flex-col gap-4 justify-center"
					onSubmit={handleLogin}
				>
					<div className="flex flex-col">
						<label htmlFor="email" className="text-sm font-medium mb-1">
							Email
						</label>
						<input
							type="email"
							id="email"
							className="w-full h-[40px] px-3 border rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500"
							placeholder="Введите ваш email"
							value={email}
							onChange={(e) => setEmail(e.target.value)}
						/>
					</div>
					<div className="flex flex-col">
						<label htmlFor="password" className="text-sm font-medium mb-1">
							Пароль
						</label>
						<input
							type="password"
							id="password"
							className="w-full h-[40px] px-3 border rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500"
							placeholder="Введите ваш пароль"
							value={password}
							onChange={(e) => setPassword(e.target.value)}
						/>
					</div>
					<button
						type="submit"
						className="w-full h-[45px] bg-neutral-950 text-primary-50 rounded-md font-medium hover:bg-neutral-900"
					>
						Войти
					</button>
					{message && (
						<div className="mt-4 text-center text-red-500">{message}</div>
					)}
					<a
						href="/recovering"
						className="text-sm text-black hover:text-primary-500 text-center mt-2 hover:underline"
					>
						Забыли пароль?
					</a>
				</form>
				<div className="mt-6 text-sm">
					<span>Нет аккаунта? </span>
					<a href="/register" className="text-primary-500 font-medium hover:underline">
						Зарегистрироваться
					</a>
				</div>
			</div>
		</div>
	);
};
