import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./style.css";

export const Recovering_block = () => {
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");
    const navigate = useNavigate();

    const RecoverEmail = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/api/auth/recovering", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: new URLSearchParams({ email }),
            });

            if (response.ok) {
                const result = await response.text();
                if (result === "Ваш пароль успешно отправлен на почту") {
                    navigate("/login");
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
                className="w-[400px] bg-white/75 text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[300px] flex flex-col items-center justify-center backdrop-blur-lg"
            >
                <h1 className="text-3xl font-title mb-6">Забыли пароль?</h1>
                <form
                    className="w-[300px] flex flex-col gap-4 justify-center"
                    onSubmit={RecoverEmail}
                >
                    <div className="flex flex-col">
                        <label htmlFor="email" className="text-sm font-medium mb-1">
                            Email
                        </label>
                        <input
                            type="email"
                            id="email"
                            className="w-full h-[40px] px-3 border rounded-md focus:outline-none focus:ring-2 focus:ring-primary-500 transition-all duration-300 ease-out transform hover:scale-105"
                            placeholder="Введите ваш email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </div>
                    <button
                        type="submit"
                        className="w-full h-[45px] bg-neutral-950 text-primary-50 rounded-md font-medium hover:bg-neutral-900 transition-all duration-300 ease-out transform hover:scale-105"
                    >
                        Отправить
                    </button>
                    {message && (
                        <div className="mt-4 text-center text-red-500">{message}</div>
                    )}
                </form>
                <a
                    href="/login"
                    className="text-sm text-black hover:text-primary-500 text-center mt-2 hover:underline transition-all duration-300 ease-out transform hover:scale-105"
                >
                    Вернуться на вход
                </a>
            </div>
        </div>
    );
};
