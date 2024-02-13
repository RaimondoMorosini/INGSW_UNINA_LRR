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
    extend: {},
  },
  plugins: [
    typography,
    form,
    aspectRatio,
  ],
}

