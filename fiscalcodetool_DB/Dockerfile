# Usa l'immagine di PostgreSQL come base
FROM postgres:latest

# Definisci variabili di ambiente per l'utente e la password dell'amministratore e dell'utente di servizio
ENV POSTGRES_USER=admin
ENV POSTGRES_PASSWORD=admin
ENV SERVICE_USER=service_user
ENV SERVICE_PASSWORD=service

# Crea un database chiamato FiscalCodeTool
ENV POSTGRES_DB=FiscalCodeTool

# Copia uno script SQL per creare l'utente di servizio e concedergli i privilegi necessari
COPY init.sql /docker-entrypoint-initdb.d/

# Imposta le autorizzazioni dello script SQL
RUN chown postgres:postgres /docker-entrypoint-initdb.d/init.sql
RUN chmod 755 /docker-entrypoint-initdb.d/init.sql
