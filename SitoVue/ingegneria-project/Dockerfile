# Usa l'immagine base di Nginx
FROM nginx:alpine

# Copia i file della build di Vue.js nella directory predefinita di Nginx
COPY dist/ /usr/share/nginx/html

# Esponi la porta 80 per il server
EXPOSE 80

# Comando di default per avviare Nginx
CMD ["nginx", "-g", "daemon off;"]
