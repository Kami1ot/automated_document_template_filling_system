import React from "react";
import ReactDOM from "react-dom/client";
import { App } from "./App"; // Убедитесь, что путь к App.js верный

// Ищем элемент с id="root" в вашем index.html
const root = ReactDOM.createRoot(document.getElementById("root"));

// Рендерим приложение
root.render(<App />);
