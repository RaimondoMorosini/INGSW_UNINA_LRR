module.exports = {
    env: {
        browser: true,
        es2021: true,
        node: true,
    },
    extends: ['eslint:recommended', 'standard', 'plugin:vue/vue3-essential', 'prettier'],
    overrides: [
        {
            env: {
                node: true,
            },
            files: ['.eslintrc.{js,cjs}'],
            parserOptions: {
                sourceType: 'script',
            },
        },
    ],
    parserOptions: {
        ecmaVersion: 'latest',
        sourceType: 'module',
    },
    plugins: ['vue', 'prettier'],
    singleQuote: false,
    printWidth: 120,
    rules: {
        'prettier/prettier': 'error',
        'no-unused-vars': 'warn',
    },
};
