/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: { 
      colors: {
        "primary-muted": "oklch(var(--primary-muted) / <alpha-value>)",
      },},
  },
  plugins: [
    require('daisyui'),
  ],


  daisyui: {
    themes: [
      {
        mytheme: {
          "primary": "#f7911f",
          "primary-content": "#e6d9ff",
          "secondary": "#00c5ff",
          "secondary-content": "#FFF0D8",
          "accent": "#00baff",
          "accent-content": "#000d16",
          "neutral": "#ffffff",
          "neutral-content": "#cacaca",
          "base-100": "#e6ffff",
          "base-200": "#c8dede",
          "base-300": "#986c34", //color marron
          "base-content": "#986c34",
          "info": "#009ec3",
          "info-content": "#00090e",
          "success": "#00c44b",
          "success-content": "#000e02",
          "warning": "#d89600",
          "warning-content": "#5A3914",
          "error": "#ff7a8b",
          "error-content": "#160507",
         
        }
      },
 
      "light",
      "dark",
      "cupcake",
      "bumblebee",
      "emerald",
      "corporate",
      "synthwave",
      "retro",
      "cyberpunk",
      "valentine",
      "halloween",
      "garden",
      "forest",
      "aqua",
      "lofi",
      "pastel",
      "fantasy",
      "wireframe",
      "black",
      "luxury",
      "dracula",
      "cmyk",
      "autumn",
      "business",
      "acid",
      "lemonade",
      "night",
      "coffee",
      "winter",
      "dim",
      "nord",
      "sunset",
      
    ],
  },
}
