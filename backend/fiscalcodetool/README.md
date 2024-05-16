# DB

## Descrizione

In questa cartella e' presente tutto il necessario per la creazione del Container con l'applicazione FiscalCodeTool.

## Comandi

Con il seguente comando verra creata l' immagine del container Docker.

    docker build -t app-fiscalcodetool .

Con il seguente comando verra fatto partire il container Docker

    docker run -d --name app-fiscalcodetool -p 8080:8080 app-fiscalcodetool