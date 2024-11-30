import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Auth_block } from "./auth_block";
import { Header_block } from "./header_block";
import { Register_block } from "./register_block";
import { Recovering_block } from "./recovering_block";

const Home = () => <h1>Добро пожаловать на главную страницу!</h1>; // Страница Home

export const App = () => {
    return (
        <BrowserRouter>
            <Header_block /> {/* Шапка доступна на всех страницах */}
            <Routes>
                <Route path="/login" element={<Auth_block />} /> {/* Страница авторизации */}
                <Route path="/home" element={<Home />} /> {/* Главная страница */}
                <Route path="/register" element={<Register_block />} /> {/* Главная страница */}
                <Route path="/recovering" element={<Recovering_block />} />
            </Routes>
        </BrowserRouter>
    );
};
