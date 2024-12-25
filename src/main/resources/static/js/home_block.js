import React from "react";
import "./style.css";
import { useNavigate } from "react-router-dom";

export const Home_block = () => {
	const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center mt-2">
                	<div className="w-[400px] bg-white/75 text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[500px] flex flex-col items-center justify-center px-8 backdrop-blur-lg">
    	  <h2 className="text-2xl font-title mb-6">О сайте</h2>
    	  <p className="text-center text-sm mb-6">
    	    Сайт для автоматического заполнения шаблонов документов. Упростите свою работу с помощью нашего сервиса.
    	  </p>
						<div className="w-full mb-6">
							<h3 className="text-lg font-bold mb-2">Создатель:</h3>
							<ul className="list-disc pl-6 text-sm">
								<li className="transition-all duration-300 ease-out transform hover:scale-105">Петр Овчинников</li>
							</ul>
							<h3 className="text-lg font-bold mb-2"> Ссылки:</h3>
							<ul className="list-disc pl-6 text-sm">
								<li className="hover:underline transition-all duration-300 ease-out transform hover:scale-105"><a href="https://github.com/Kami1ot">Github</a></li>
								<li className="hover:underline transition-all duration-300 ease-out transform hover:scale-105"><a href="https://t.me/kamiloty">Telegramm</a></li>
							</ul>

						</div>
						<button className="bg-black text-white py-3 px-6 rounded-md w-full font-bold transition-all duration-300 ease-out transform hover:scale-105"
								onClick={() => navigate("/templates")}>
						Приступить
    	  </button>
    	</div> 
                </div>
  )
}

