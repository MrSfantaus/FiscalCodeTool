-- Crea DB
CREATE DATABASE FiscalCodeTool;

-- Utilizza il DB FiscalCodeTool
\c FiscalCodeTool;

-- Elimino schema public
DROP SCHEMA public CASCADE;

-- Crea lo schema TOOL
CREATE SCHEMA TOOL AUTHORIZATION pg_database_owner;

-- Crea la tabella BELFIORE_CODES
CREATE TABLE TOOL.BELFIORE_CODES (
	FLAG_COMUNE_NORMAL CHAR(1) NOT NULL,
	SIGLA_PROVINCIA CHAR(2) NOT NULL,
	DESCR_PROV_COMUNE CHAR(50) NOT NULL,
	COMUNE_COD_FISC CHAR(4) NOT NULL
);
CREATE UNIQUE INDEX BELFIORE_CODES_SIGLA_PROVINCIA_IDX ON TOOL.BELFIORE_CODES 
USING BTREE (SIGLA_PROVINCIA, DESCR_PROV_COMUNE, COMUNE_COD_FISC);

-- Crea l'utente di servizio
CREATE USER service_user WITH PASSWORD 'service';

-- Concedi i privilegi nel database FiscalCodeTool all'utente di servizio
GRANT CONNECT ON DATABASE FiscalCodeTool TO service_user;
GRANT USAGE ON SCHEMA TOOL TO service_user;
GRANT SELECT ON ALL TABLES IN SCHEMA TOOL TO service_user;
