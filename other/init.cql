DROP KEYSPACE IF EXISTS imetro;
DROP TABLE IF EXISTS imetro.status;

CREATE  KEYSPACE IF NOT EXISTS imetro WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

CREATE TABLE imetro.status (timestamp bigint PRIMARY KEY, position int, direction boolean, passengers int, battery int, speed int, temperature int, failure boolean, line int, metro_id int);
