# Usa un'immagine di base di Node.js
FROM node:latest AS builder

# Imposta la directory di lavoro nel container
WORKDIR /app

# Copia il package.json e il package-lock.json nella directory di lavoro
COPY FiscalCodeTool/package.json FiscalCodeTool/package-lock.json ./

# Esegui l'installazione delle dipendenze
RUN npm install

# Copia il resto dei file dell'applicazione nella directory di lavoro
COPY FiscalCodeTool/ ./

# Compila l'app Angular
RUN npm run build --prod

# Espone la porta 4200
EXPOSE 4200

# Avvia l'applicazione Angular
CMD ["npm", "start"]
