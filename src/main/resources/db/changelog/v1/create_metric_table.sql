CREATE TABLE IF NOT EXISTS metric (
    id SERIAL PRIMARY KEY,
    method_name VARCHAR(255),
    execution_time BIGINT,
    execution_date TIMESTAMP
);