// Templates_block.js
import React, { useState } from "react";
import "./style.css";
import InvoiceTemplate from "./InvoiceTemplate";
import { initialData } from "./InitialData";
import { useNavigate } from "react-router-dom";

export const Templates_block = ({ isAuthenticated, userEmail }) => {
    const [activeTemplate, setActiveTemplate] = useState(null); // Активный шаблон
    const [templatesData, setTemplatesData] = useState({}); // Данные форм и таблиц для всех шаблонов
    const navigate = useNavigate();

    // Пример шаблонов
    const templates = [
        { id: "invoice template", name: "Счет-фактура", component: InvoiceTemplate }, // Новый шаблон
        // Добавьте другие шаблоны здесь
    ];

    const handleTemplateSelection = (id) => {
        setActiveTemplate(id);
        setTemplatesData((prevData) => ({
            ...prevData,
            [id]: prevData[id] || initialData[id]
        }));
    };

    const handleInputChange = (field, value) => {
        setTemplatesData((prevData) => ({
            ...prevData,
            [activeTemplate]: {
                ...prevData[activeTemplate],
                formData: {
                    ...prevData[activeTemplate].formData,
                    [field]: value,
                },
            },
        }));
    };

    const handleTableChange = (index, name, value) => {
        setTemplatesData((prevData) => {
            const updatedTableData = [...prevData[activeTemplate].tableData];
            updatedTableData[index][name] = value;
            return {
                ...prevData,
                [activeTemplate]: {
                    ...prevData[activeTemplate],
                    tableData: updatedTableData,
                },
            };
        });
    };

    const addTableRow = () => {
        setTemplatesData((prevData) => ({
            ...prevData,
            [activeTemplate]: {
                ...prevData[activeTemplate],
                tableData: [
                    ...prevData[activeTemplate].tableData,
                    {
                        productName: "",
                        unitCode: "",
                        unitSymbol: "",
                        quantity: "",
                        price: "",
                        totalWithoutTax: "",
                        exciseSum: "",
                        taxRate: "",
                        taxSum: "",
                        totalWithTax: "",
                        originCountryCode: "",
                        originCountryName: "",
                        customsDeclaration: ""
                    }
                ],
            },
        }));
    };

    const deleteTableRow = (index) => {
        setTemplatesData((prevData) => {
            const currentTableData = prevData[activeTemplate].tableData;

            if (currentTableData.length > 1) {
                if (window.confirm("Вы уверены, что хотите удалить эту строку?")) {
                    const updatedTableData = currentTableData.filter((_, i) => i !== index);
                    return {
                        ...prevData,
                        [activeTemplate]: {
                            ...prevData[activeTemplate],
                            tableData: updatedTableData,
                        },
                    };
                } else {
                    // Если пользователь отменил удаление, возвращаем предыдущее состояние
                    return prevData;
                }
            } else {
                alert("Нельзя удалить последнюю строку.");
                // Возвращаем предыдущее состояние, так как ничего не меняется
                return prevData;
            }
        });
    };


    // Отправка данных на сервер
    const handleSubmit = async () => {
        if (!activeTemplate) {
            alert("Пожалуйста, выберите шаблон.");
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/api/templates/submit", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    templateName: activeTemplate,
                    userEmail: userEmail,
                    data: templatesData[activeTemplate],
                }),
            });

            if (response.ok) {
                // Получаем имя файла из заголовков ответа, если оно есть
                let filename = "document.xlsx";
                const disposition = response.headers.get('Content-Disposition');
                if (disposition && disposition.indexOf('filename=') !== -1) {
                    const filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
                    const matches = filenameRegex.exec(disposition);
                    if (matches != null && matches[1]) {
                        filename = matches[1].replace(/['"]/g, '');
                    }
                }

                // Получаем Blob из ответа
                const blob = await response.blob();
                const url = window.URL.createObjectURL(blob);

                // Создаём ссылку и программно инициируем скачивание
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', filename); // Устанавливаем имя файла
                document.body.appendChild(link);
                link.click();
                document.body.removeChild(link);

                alert("Документ успешно сформирован и скачан!");
            } else {
                const errorText = await response.text();
                alert("Ошибка при формировании документа: " + errorText);
            }
        } catch (error) {
            alert("Ошибка соединения с сервером.");
        }
    };

    const ActiveTemplateComponent = templates.find((t) => t.id === activeTemplate)?.component || null;

    return (
        <div className="flex items-center justify-center mt-2">
            {isAuthenticated ?(
            <div className="templates-block bg-white/75 shadow-lg rounded-lg p-6 backdrop-blur-lg w-[55%]">
                <div className="template-buttons gap-2 mb-4">
                    <span>Выберите документ:  </span>
                    {templates.map((template) => (
                        <button
                            key={template.id}
                            className={`px-4 py-2 rounded-md border transition-all duration-300 ease-out transform hover:scale-105 ${
                                activeTemplate === template.id ? "bg-primary-500 text-white" : "bg-white text-black"
                            }`}
                            onClick={() => handleTemplateSelection(template.id)}
                        >
                            {template.name}
                        </button>
                    ))}
                </div>

                {ActiveTemplateComponent && (
                    <ActiveTemplateComponent
                        formData={templatesData[activeTemplate]?.formData || {}}
                        handleInputChange={handleInputChange}
                        tableData={templatesData[activeTemplate]?.tableData || []}
                        handleTableChange={handleTableChange}
                        addTableRow={addTableRow}
                        deleteTableRow={deleteTableRow}
                    />
                )}

                {activeTemplate && (
                    <div className="mt-4">
                        <button
                            className="bg-primary-500 text-white px-4 py-2 rounded-md"
                            onClick={handleSubmit}
                        >
                            Сформировать
                        </button>
                    </div>
                )}
            </div>
            ):(
                <div className="w-[400px] bg-white/75 text-neutral-950 shadow-lg border border-neutral-300 rounded-lg min-h-[200px] flex flex-col items-center justify-center backdrop-blur-lg p-6">
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

