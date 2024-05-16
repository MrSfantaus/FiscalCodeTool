# DB

## Descrizione

In questa cartella e' presente tutto il necessario per la creazione del DB dedicato all'applicazione BuddyInfo.

Il DB e' di tipo PostgreSQL ed e' contenuto all' interno di un container Docker.


## Comandi

Con il seguente comando verra creata l' immagine del container Docker.

    docker build -t postgresql-fiscalcodetool .

Con il seguente comando verra fatto partire il container Docker

    docker run -d --name postgresql-fiscalcodetool -p 5432:5432 postgresql-fiscalcodetool