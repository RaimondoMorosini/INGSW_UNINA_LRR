import typography from '@tailwindcss/typography';
import form from '@tailwindcss/forms';
import aspectRatio from '@tailwindcss/aspect-ratio';

/** @type {import('tailwindcss').Config} */
module.exports = {
    mode: 'jit',

    content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}', 'formkit.theme.mjs'],

    media: false, // or 'media' or 'class'
    theme: {
        screen: {
            sm: '640px',
            md: '768px',
            lg: '1000px',
            xl: '1280px',
            '2xl': '1536px',
        },
        extend: {
            colors: {
                'primary-50': 'rgb(var(--primary-50))',
                'primary-100': 'rgb(var(--primary-100))',
                'primary-200': 'rgb(var(--primary-200))',
                'primary-300': 'rgb(var(--primary-300))',
                'primary-400': 'rgb(var(--primary-400))',
                'primary-500': 'rgb(var(--primary-500))',
                'primary-600': 'rgb(var(--primary-600))',
                'primary-700': 'rgb(var(--primary-700))',
                'primary-800': 'rgb(var(--primary-800))',
                'primary-900': 'rgb(var(--primary-900))',
                'primary-950': 'rgb(var(--primary-950))',
                'surface-0': 'rgb(var(--surface-0))',
                'surface-50': 'rgb(var(--surface-50))',
                'surface-100': 'rgb(var(--surface-100))',
                'surface-200': 'rgb(var(--surface-200))',
                'surface-300': 'rgb(var(--surface-300))',
                'surface-400': 'rgb(var(--surface-400))',
                'surface-500': 'rgb(var(--surface-500))',
                'surface-600': 'rgb(var(--surface-600))',
                'surface-700': 'rgb(var(--surface-700))',
                'surface-800': 'rgb(var(--surface-800))',
                'surface-900': 'rgb(var(--surface-900))',
                'surface-950': 'rgb(var(--surface-950))',
                primario: { 
                    /* vecchia tonalità di viola
                    100: '#cd87f6',
                    200: '#c169f4',
                    300: '#9752c0',
                    400: '#904eb7',
                    500: '#8a4baf',
                    600: '#7c439d',
                    700: '#673883',
                    800: '#522d69',
                    900: '#3e214e',
                    */
                    50: '#fdf4ff', 
                    100: '#fae8ff', 
                    200: '#f5d0fe', 
                    300: '#f0abfc', 
                    400: '#e879f9', 
                    500: '#d946ef', 
                    600: '#c026d3', 
                    700: '#a21caf', 
                    800: '#86198f', 
                    900: '#701a75', 
                    950: '#4a044e' 
                },
                secondario: {
                    // Esempio: Blu
                    100: '#b3d4ff',
                    200: '#80b3ff',
                    300: '#4d91ff',
                    400: '#1a6fff',
                    500: '#0056e0',
                    600: '#0047b3',
                    700: '#003886',
                    800: '#002859',
                    900: '#00182c',
                },
                successo: {
                    // Esempio: Verde
                    100: '#e9fac8',
                    200: '#d0f5a1',
                    300: '#b7ef8e',
                    400: '#9ee67a',
                    500: '#85dd66',
                    600: '#6cd453',
                    700: '#53cb40',
                    800: '#3ac22d',
                    900: '#21b91a',
                },
                danger: {
                    // Esempio: Rosso
                    100: '#fcd1d1',
                    200: '#f8a3a3',
                    300: '#f47575',
                    400: '#f04747',
                    500: '#ec1919',
                    600: '#d90e0e',
                    700: '#c60d0d',
                    800: '#b30c0c',
                    900: '#a00b0b',
                },
                warning: {
                    // Esempio: Arancione
                    100: '#ffe5cc',
                    200: '#ffcc99',
                    300: '#ffb266',
                    400: '#ff9933',
                    500: '#ff8000',
                    600: '#e67300',
                    700: '#cc6600',
                    800: '#b35900',
                    900: '#994d00',
                },
            },
            spacing: {
                '1col': '74px',
                '2col': '172px',
                '3col': '270px',
                '4col': '368px',
                '5col': '466px',
                '6col': '564px',
                '7col': '662px',
                '8col': '760px',
                '9col': '858px',
                '10col': '956px',
                '11col': '1054px',
                '12col': '1152px',
            },
        },
    },
    plugins: [typography, form, aspectRatio],
};
