// InvoiceTemplate.js
import React from "react";
import "./style.css";

const InvoiceTemplate = ({
                             formData,
                             handleInputChange,
                             tableData,
                             handleTableChange,
                             addTableRow,
                             deleteTableRow
                         }) => {
    return (
        <div className="bg-white/75 shadow-lg rounded-lg p-6 text-xs">
            <div>
                <div className="text-right">
                    <span>
                        Приложение № 1<br/>
                        к постановлению Правительства<br/>
                        Российской Федерации<br/>
                        от 26.12.2011 № 1137
                    </span>
                </div>
                <div className="text-left w-2/4">
                    {/* Поля формы */}
                    <div className="max-h-[22px] text-xs">
                        СЧЕТ-ФАКТУРА №
                        <input
                            type="text"
                            className="border border-black rounded px-0.5 py-0.5 w-32 placeholder-gray-500 text-xs"
                            placeholder="Номер"
                            value={formData.invoiceNumber}
                            onChange={(e) => handleInputChange("invoiceNumber", e.target.value)}
                        />
                        от
                        <input
                            type="date"
                            className="border border-black rounded px-0.5 py-0.5 w-32 placeholder-gray-500 text-xs"
                            placeholder="Дата"
                            value={formData.invoiceDate}
                            onChange={(e) => handleInputChange("invoiceDate", e.target.value)}
                        />
                    </div>

                    <br/>
                    <div className="max-h-[22px] text-xs">
                        ИСПРАВЛЕНИЕ №
                        <input
                            type="text"
                            className="border border-black rounded px-0.5 py-0.5 w-32 placeholder-gray-500 text-xs"
                            placeholder="Номер"
                            value={formData.invoiceTypoNumber}
                            onChange={(e) => handleInputChange("invoiceTypoNumber", e.target.value)}
                        />
                        от
                        <input
                            type="date"
                            className="border border-black rounded px-0.5 py-0.5 w-32 placeholder-gray-500 text-xs"
                            placeholder="Дата"
                            value={formData.invoiceTypoDate}
                            onChange={(e) => handleInputChange("invoiceTypoDate", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        Продавец
                        <input
                            type="text"
                            placeholder="Продавец"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.sellerName}
                            onChange={(e) => handleInputChange("sellerName", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        Адрес
                        <input
                            type="text"
                            placeholder="Город, Улица, Дом"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.sellerAddress}
                            onChange={(e) => handleInputChange("sellerAddress", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        ИНН/КПП продавца
                        <input
                            type="text"
                            placeholder="ИНН/КПП"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.sellerIIN}
                            onChange={(e) => handleInputChange("sellerIIN", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        Грузоотправитель и его адрес
                        <input
                            type="text"
                            placeholder="Компания, ее Адрес"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.fromField}
                            onChange={(e) => handleInputChange("fromField", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        Грузополучатель и его адрес
                        <input
                            type="text"
                            placeholder="Компания, ее Адрес"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.whereField}
                            onChange={(e) => handleInputChange("whereField", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        К платежно-расчетному документу №
                        <input
                            type="text"
                            className="border border-black rounded px-0.5 py-0.5 w-32 placeholder-gray-500 text-xs"
                            placeholder="Номер"
                            value={formData.payNumber}
                            onChange={(e) => handleInputChange("payNumber", e.target.value)}
                        />
                        от
                        <input
                            type="date"
                            className="border border-black rounded px-0.5 py-0.5 w-32 placeholder-gray-500 text-xs"
                            placeholder="Дата"
                            value={formData.payDate}
                            onChange={(e) => handleInputChange("payDate", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        Покупатель
                        <input
                            type="text"
                            placeholder="Покупатель"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.buyerName}
                            onChange={(e) => handleInputChange("buyerName", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        Адрес
                        <input
                            type="text"
                            placeholder="Город, Улица, Дом"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.buyerAddress}
                            onChange={(e) => handleInputChange("buyerAddress", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        ИНН/КПП покупателя
                        <input
                            type="text"
                            placeholder="ИНН/КПП"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.buyerIIN}
                            onChange={(e) => handleInputChange("buyerIIN", e.target.value)}
                        />
                    </div>
                    <br/>
                    <div className="max-h-[22px] text-xs">
                        Валюта: наименование, код
                        <input
                            type="text"
                            placeholder="Наименование, Код"
                            className="border border-black rounded px-0.5 py-0.5 w-3/5 placeholder-gray-500 text-xs"
                            value={formData.currency}
                            onChange={(e) => handleInputChange("currency", e.target.value)}
                        />
                    </div>
                </div>
            </div>

            {/* Таблица */}
            <br/>
            <table border="1" className="w-full text-center border-collapse border border-gray-300 text-xs">
                <thead>
                <tr>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Наименование товара
                    </th>
                    <th colSpan="2" className="border border-gray-300 px-2 py-1">
                        Единица измерения
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Количество (объем)
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Цена (тариф) за единицу измерения
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Стоимость товаров (работ, услуг), имущественных прав без налога - всего
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        В том числе сумма акциза
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Налоговая ставка
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Сумма налога, предъявляемая покупателю
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Стоимость товаров (работ, услуг), имущественных прав с налогом - всего
                    </th>
                    <th colSpan="2" className="border border-gray-300 px-2 py-1">
                        Страна происхождения товара
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Номер таможенной декларации
                    </th>
                    <th rowSpan="2" className="border border-gray-300 px-2 py-1">
                        Действия
                    </th>
                </tr>
                <tr>
                    <th className="border border-gray-300 px-2 py-1">Код</th>
                    <th className="border border-gray-300 px-2 py-1">Условное обозначение (национальное)</th>
                    <th className="border border-gray-300 px-2 py-1">Цифровой код</th>
                    <th className="border border-gray-300 px-2 py-1">Краткое наименование</th>
                </tr>
                </thead>
                <tbody>
                {tableData.map((row, index) => (
                    <tr key={index}>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="Текст"
                                value={row.productName}
                                onChange={(e) =>
                                    handleTableChange(index, "productName", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="123"
                                value={row.unitCode}
                                onChange={(e) =>
                                    handleTableChange(index, "unitCode", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="шт"
                                value={row.unitSymbol}
                                onChange={(e) =>
                                    handleTableChange(index, "unitSymbol", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1 placeholder-gray-500">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1"
                                placeholder="1"
                                value={row.quantity}
                                onChange={(e) =>
                                    handleTableChange(index, "quantity", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="420,000.00"
                                value={row.price}
                                onChange={(e) =>
                                    handleTableChange(index, "price", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="420,000.00"
                                value={row.totalWithoutTax}
                                onChange={(e) =>
                                    handleTableChange(index, "totalWithoutTax", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="без акциза"
                                value={row.exciseSum}
                                onChange={(e) =>
                                    handleTableChange(index, "exciseSum", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="20.00"
                                value={row.taxRate}
                                onChange={(e) =>
                                    handleTableChange(index, "taxRate", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="84,000.00"
                                value={row.taxSum}
                                onChange={(e) =>
                                    handleTableChange(index, "taxSum", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="504,000.00"
                                value={row.totalWithTax}
                                onChange={(e) =>
                                    handleTableChange(index, "totalWithTax", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="360"
                                value={row.originCountryCode}
                                onChange={(e) =>
                                    handleTableChange(index, "originCountryCode", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="ИНДОНЕЗИЯ"
                                value={row.originCountryName}
                                onChange={(e) =>
                                    handleTableChange(index, "originCountryName", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <input
                                type="text"
                                className="w-full border border-black rounded px-2 py-1 placeholder-gray-500"
                                placeholder="10129000/010721/0000025/1"
                                value={row.customsDeclaration}
                                onChange={(e) =>
                                    handleTableChange(index, "customsDeclaration", e.target.value)
                                }
                            />
                        </td>
                        <td className="border border-gray-300 px-2 py-1">
                            <button
                                onClick={() => deleteTableRow(index)}
                                className="bg-red-500 text-white px-2 py-1 rounded"
                            >
                                Удалить
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

            <button
                onClick={addTableRow}
                className="bg-blue-500 text-white px-4 py-2 mt-4 rounded"
            >
                Добавить строку
            </button>
        </div>
    );
}
export default InvoiceTemplate;
