import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Для перенаправления
import "./style.css";

export const Register_block = () => {
	const [username, setUsername] = useState("");
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [confirmPassword, setConfirmPassword] = useState("");
	const [message, setMessage] = useState(""); // Сообщение об ошибке или успехе
	const navigate = useNavigate(); // Для перенаправления

	const handleRegister = async (event) => {
		event.preventDefault(); // Предотвращаем перезагрузку страницы

		if (password !== confirmPassword) {
			setMessage("Пароли не совпадают");
			return;
		}

		try {
			const response = await fetch("http://localhost:8080/api/auth/register", {
				method: "POST",
				headers: {
					"Content-Type": "application/x-www-form-urlencoded",
				},
				body: new URLSearchParams({
					email,
					password,
				}),
			});

			if (response.ok) {
				const result = await response.text();
				if (result === "Регистрация прошла успешно") {
					navigate("/login"); // Перенаправляем на страницу входа
				} else {
					setMessage(result); // Показываем сообщение об ошибке
				}
			} else {
				setMessage("Ошибка при регистрации");
			}
		} catch (error) {
			setMessage("Ошибка соединения с сервером");
		}
	};

	return (
		<div className="flex flex-col items-center mt-2">
			<div className="w-[400px] bg-white text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[600px] flex flex-col items-center justify-center">
				<h1 className="text-3xl font-title font-bold mb-6">Регистрация</h1>
				<form className="w-[300px] flex flex-col gap-4" onSubmit={handleRegister}>
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
					<div className="flex flex-col">
						<label htmlFor="confirm-password" className="text-sm font-medium mb-1">
							Подтвердите пароль
						</label>
						<input
							type="password"
							id="confirm-password"
							className="w-full h-[40px] px-3 border rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500"
							placeholder="Подтвердите ваш пароль"
							value={confirmPassword}
							onChange={(e) => setConfirmPassword(e.target.value)}
						/>
					</div>
					<button
						type="submit"
						className="w-full h-[45px] bg-neutral-950 text-primary-50 rounded-md font-medium hover:bg-neutral-900"
					>
						Зарегистрироваться
					</button>
				</form>
				{message && (
					<div className="mt-4 text-center text-red-500">{message}</div>
				)}
				<div className="mt-6 text-sm">
					<span>Уже есть аккаунт? </span>
					<a href="/login" className="text-primary-500 font-medium hover:underline">
						Войти
					</a>
				</div>
			</div>
		</div>
	);
};
