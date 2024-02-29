
/** @type {import('prettier').Config} */
module.exports = {
    tailwindConfig: './tailwind.config.js',
    semi: false,
    singleQuote: true,
    trailingComma: 'all',
    pluginSearchDirs: false,
    plugins: ['@ianvs/prettier-plugin-sort-imports',
                'prettier-plugin-tailwindcss'],
    importOrder: ['^@', '^[a-zA-Z0-9-]+', '^[./]'],
  }