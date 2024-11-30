const path = require('path');

module.exports = {
    entry: {
        app: './src/main/resources/static/js/index.js', // Точка входа в приложение
    },
    output: {
        filename: '[name].bundle.js', // Динамически формирует имя файла (header_block.bundle.js, auth_block.bundle.js)
        path: path.resolve(__dirname, 'src/main/resources/static/dist'), // Папка для сохранения
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader', 'postcss-loader'],
            },
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react'],
                    },
                },
            },
        ],
    },
    resolve: {
        extensions: ['.js', '.jsx'],
    },
    mode: 'development',
};
