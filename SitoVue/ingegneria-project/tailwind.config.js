import typography from '@tailwindcss/typography'
import form from '@tailwindcss/forms'
import aspectRatio from '@tailwindcss/aspect-ratio'

/** @type {import('tailwindcss').Config} */
module.exports = {
  mode: 'jit',
  purge:['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false, // or 'media' or 'class'

  //content: [
  //  "index.html",
  //  "./src/**/*.{vue,js,ts,jsx,tsx}",
  //],
  theme: {
    extend: {
      colors: {
        primario: {
          100: '#cd87f6',
          200: '#c169f4',
          300: '#9752c0',
          400: '#904eb7',
          500: '#8a4baf',
          600: '#7c439d',
          700: '#673883',
          800: '#522d69',
          900: '#3e214e',
          
        }, 
        secondario: { // Esempio: Blu
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
        successo: { // Esempio: Verde
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
        danger: { // Esempio: Rosso
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
        warning: { // Esempio: Arancione
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
    },
  },
  plugins: [
    typography,
    form,
    aspectRatio,
  ],
}

