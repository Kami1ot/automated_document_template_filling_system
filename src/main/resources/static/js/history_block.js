import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export const History_block = ({ isAuthenticated, userEmail }) => {
    const [invoices, setInvoices] = useState([]);
    const navigate = useNavigate();
    const [expandedIndex, setExpandedIndex] = useState(null); // Для отслеживания открытого счета

    const toggleExpand = (index) => {
        setExpandedIndex(index === expandedIndex ? null : index); // Закрыть, если нажали повторно
    };

    useEffect(() => {
        if (isAuthenticated) {
            fetch(`/api/invoices/history?email=${encodeURIComponent(userEmail)}`)
                .then((response) => {
                    if (!response.ok) {
                        throw new Error("Failed to fetch history");
                    }
                    return response.json();
                })
                .then((data) => setInvoices(data))
                .catch((error) => console.error("Error fetching history:", error));
        }
    }, [isAuthenticated, userEmail]);

    return (
        <div className="flex items-center justify-center mt-2">
            {isAuthenticated ? (
                <div className="flex flex-col items-center mt-2">
                    <div className="w-[1100px] bg-white/75 text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[500px] flex flex-col items-center justify-center px-8 backdrop-blur-lg">
                        <h2 className="text-2xl font-bold mb-6">История</h2>
                        <h3 className="mb-4">Ваш аккаунт: {userEmail}</h3>

                        {invoices.length > 0 ? (
                            <ul className="w-full max-w-4xl bg-white shadow-md rounded-md divide-y divide-neutral-200">
                                {invoices.map((invoice, index) => (
                                    <li key={index} className="p-4 hover:bg-neutral-100 transition">
                                        {/* Сжатая информация */}
                                        <div
                                            className="cursor-pointer flex items-center justify-between"
                                            onClick={() => toggleExpand(index)}
                                        >
                                            <strong>{index + 1}. Счет фактура</strong>
                                            <span>{expandedIndex === index ? "▲" : "▼"}</span>
                                        </div>
                                        <button
                                            className="text-red-500 hover:underline"
                                            onClick={(e) => {
                                                e.stopPropagation(); // Остановка раскрытия при клике
                                            }}
                                        >
                                            Удалить
                                        </button>


                                        {/* Полная информация, отображается только если индекс совпадает */}
                                        {expandedIndex === index && (
                                            <div className="mt-4">
                                                <div className="mb-2">
                                                    <strong>Номер счета: {invoice.invoiceNumber}</strong>
                                                    <p>Дата: {new Date(invoice.invoiceDate).toLocaleDateString()}</p>
                                                    <p>Номер типового счета: {invoice.invoiceTypoNumber}</p>
                                                    <p>Дата типового
                                                        счета: {new Date(invoice.invoiceTypoDate).toLocaleDateString()}</p>
                                                </div>
                                                <div className="mb-2">
                                                    <strong>Продавец:</strong>
                                                    <p>Имя: {invoice.sellerName}</p>
                                                    <p>Адрес: {invoice.sellerAddress}</p>
                                                    <p>ИНН: {invoice.sellerIIN}</p>
                                                </div>
                                                <div className="mb-2">
                                                    <strong>Покупатель:</strong>
                                                    <p>Имя: {invoice.buyerName}</p>
                                                    <p>Адрес: {invoice.buyerAddress}</p>
                                                    <p>ИНН: {invoice.buyerIIN}</p>
                                                </div>
                                                <div className="mb-2">
                                                    <strong>Оплата:</strong>
                                                    <p>Номер платежа: {invoice.payNumber}</p>
                                                    <p>Дата
                                                        платежа: {new Date(invoice.payDate).toLocaleDateString()}</p>
                                                </div>
                                                <div className="mb-2">
                                                    <strong>Дополнительно:</strong>
                                                    <p>Откуда: {invoice.fromField}</p>
                                                    <p>Куда: {invoice.whereField}</p>
                                                    <p>Валюта: {invoice.currency}</p>
                                                </div>
                                            </div>
                                        )}
                                    </li>
                                ))}
                            </ul>
                        ) : (
                            <p className="text-neutral-600">У вас пока нет заполненных шаблонов.</p>


                        )
                        };

                    </div>
                </div>
            ) : (
                <div
                    className="w-[400px] bg-white/75 text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[200px] flex flex-col items-center justify-center backdrop-blur-lg p-6">
                    <h1 className="text-xl text-red-500 mb-4">
                        Вы не вошли в аккаунт
                    </h1>
                    <div className="flex flex-col gap-2 w-full">
                        <button
                            onClick={() => navigate("/login")}
                            className="w-full bg-neutral-950 text-primary-50 py-2 rounded-md hover:bg-neutral-900 transition-all duration-300 ease-out transform hover:scale-105"
                        >
                            Войти
                        </button>
                        <button
                            onClick={() => navigate("/register")}
                            className="w-full bg-neutral-950 text-primary-50 py-2 rounded-md hover:bg-neutral-900 transition-all duration-300 ease-out transform hover:scale-105"
                        >
                            Зарегистрироваться
                        </button>
                    </div>
                </div>
            )}
        </div>
    );
};
