// Templates_block.js
import React, { useState } from "react";
import "./style.css";
import InvoiceTemplate from "./InvoiceTemplate"; // Импортируем вынесенный шаблон
import { initialData } from "./InitialData"; // Импортируем начальные данные

// Главный компонент для выбора шаблона
export const Templates_block = () => {
    const [activeTemplate, setActiveTemplate] = useState(null); // Активный шаблон
    const [templatesData, setTemplatesData] = useState({}); // Данные форм и таблиц для всех шаблонов

    // Пример шаблонов
    const templates = [
        { id: "invoice template", name: "Счет-фактура", component: InvoiceTemplate }, // Новый шаблон
        // Добавьте другие шаблоны здесь
    ];

    // Устанавливаем активный шаблон и инициализируем его данные, если они еще не инициализированы
    const handleTemplateSelection = (id) => {
        setActiveTemplate(id);
        setTemplatesData((prevData) => ({
            ...prevData,
            [id]: prevData[id] || initialData[id]
        }));
    };

    // Обновляем значения в форме для активного шаблона
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

    // Обновление данных таблицы для активного шаблона
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

    // Добавление строки в таблицу для активного шаблона
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

    // Удаление строки из таблицы для активного шаблона
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
            <div className="templates-block bg-white/75 shadow-lg rounded-lg p-6 backdrop-blur-lg">
                <div className="template-buttons gap-2 mb-4">
                    <span>Выберите документ:</span>
                    {templates.map((template) => (
                        <button
                            key={template.id}
                            className={`px-4 py-2 rounded-md border ${
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
        </div>
    );
};

