CREATE TABLE data (
  id BIGSERIAL PRIMARY KEY,
  data TEXT,
  last_update TIMESTAMP,
  source_id BIGSERIAL REFERENCES data_source
);