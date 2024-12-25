import React, { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Auth_block } from "./auth_block";
import { Header_block } from "./header_block";
import { Register_block } from "./register_block";
import { Recovering_block } from "./recovering_block";
import { Home_block } from "./home_block";
import {Account_block} from "./account_block";
import { Templates_block } from "./templates_block";
import { History_block } from "./history_block";

export const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [userEmail, setUserEmail] = useState("");

    useEffect(() => {
        const authStatus = localStorage.getItem("isAuthenticated") === "true";
        const email = localStorage.getItem("userEmail");
        setIsAuthenticated(authStatus);
        setUserEmail(email || "");
    }, []);

    const handleLogin = (authStatus, email) => {
        setIsAuthenticated(authStatus);
        setUserEmail(email);
    };

    return (
        <BrowserRouter>
            <Header_block isAuthenticated={isAuthenticated} userEmail={userEmail} />
            <Routes>
                <Route
                    path="/login"
                    element={<Auth_block onLogin={handleLogin} />}
                />
                <Route path="/home" element={<Home_block />} />
                <Route path="/register" element={<Register_block />} />
                <Route path="/recovering" element={<Recovering_block />} />
                <Route path="/account" element={<Account_block isAuthenticated={isAuthenticated} userEmail={userEmail} />} />
                <Route path="/templates" element={<Templates_block isAuthenticated={isAuthenticated} userEmail={userEmail} />} />
                <Route path="/history" element={<History_block isAuthenticated={isAuthenticated} userEmail={userEmail} />} />
            </Routes>
        </BrowserRouter>
    );
};
