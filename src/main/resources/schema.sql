CREATE TABLE IF NOT EXISTS quote (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, quote DOUBLE);

CREATE TABLE IF NOT EXISTS quote_stats 
                        (id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, 
                        quote_id BIGINT NOT NULL, 
                        viewed BIGINT, 
                        bought BIGINT,
                        channel VARCHAR(20));
ALTER TABLE quote_stats ADD FOREIGN KEY (quote_id) REFERENCES quote(id);