import typography from '@tailwindcss/typography'
import form from '@tailwindcss/forms'
import aspectRatio from '@tailwindcss/aspect-ratio'

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
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
      },
    },
  },
  plugins: [
    typography,
    form,
    aspectRatio,
  ],
}

