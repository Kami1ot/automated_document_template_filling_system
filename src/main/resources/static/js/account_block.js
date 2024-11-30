import React, { useState } from "react";
import "./style.css";

export const Account_block = ({ isAuthenticated, userEmail }) => {
	const [newPassword, setNewPassword] = useState("");
	const [message, setMessage] = useState("");

	const handleUpdatePassword = async () => {
		try {
			const response = await fetch("http://localhost:8080/api/auth/update-password", {
				method: "POST",
				headers: {
					"Content-Type": "application/x-www-form-urlencoded",
				},
				body: new URLSearchParams({
					email: userEmail,
					newPassword: newPassword,
				}),
			});

			if (response.ok) {
				const result = await response.text();
				setMessage(result); // Сообщение от сервера
			} else {
				setMessage("Ошибка при обновлении пароля");
			}
		} catch (error) {
			setMessage("Ошибка соединения с сервером");
		}
	};

	return (
		<div className="flex flex-col items-center mt-2">
			{isAuthenticated ? (
				<div className="w-[400px] bg-white/75 text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[500px] flex flex-col items-center justify-center backdrop-blur-lg">
					<h1 className="text-2xl font-title mb-6">Account Settings</h1>
					<div className="flex flex-col gap-4 w-full px-6">
						<div className="flex flex-col gap-2">
							<label htmlFor="email" className="text-neutral-950 font-medium">
								Your Email
							</label>
							<input
								type="email"
								id="email"
								value={userEmail}
								className="w-full px-4 py-2 border border-neutral-300 rounded-md bg-neutral-200 cursor-not-allowed transition-all duration-300 ease-out transform hover:scale-105"
								readOnly
							/>
						</div>
						<div className="flex flex-col gap-2">
							<label htmlFor="password" className="text-neutral-950 font-medium">
								New Password
							</label>
							<input
								type="password"
								id="password"
								placeholder="Enter new password"
								className="w-full px-4 py-2 border border-neutral-300 rounded-md focus:outline-none focus:ring focus:ring-primary-500 transition-all duration-300 ease-out transform hover:scale-105"
								value={newPassword}
								onChange={(e) => setNewPassword(e.target.value)}
							/>
						</div>
						<button
							onClick={handleUpdatePassword}
							className="w-full bg-primary-500 text-primary-50 py-2 rounded-md hover:bg-primary-600"
						>
							Update Password
						</button>
						{message && (
							<div className="mt-4 text-center text-red-500">{message}</div>
						)}
					</div>
				</div>
			) : (
				<div className="w-[400px] bg-white/75 text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[200px] flex flex-col items-center justify-center backdrop-blur-lg p-6">
					<h1 className="text-xl text-red-500 font-bold mb-4">
						Вы не вошли в аккаунт
					</h1>
					<div className="flex flex-col gap-2 w-full">
						<button
							onClick={() => navigate("/login")}
							className="w-full bg-primary-500 text-primary-50 py-2 rounded-md hover:bg-primary-600"
						>
							Войти
						</button>
						<button
							onClick={() => navigate("/register")}
							className="w-full bg-neutral-950 text-primary-50 py-2 rounded-md hover:bg-neutral-900"
						>
							Зарегистрироваться
						</button>
					</div>
				</div>
			)}
		</div>
	);
};
