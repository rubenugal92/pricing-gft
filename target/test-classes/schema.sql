CREATE TABLE prices (
    id BIGINT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    brand_id BIGINT NOT NULL,
    price_list INT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    price DOUBLE NOT NULL,
    currency VARCHAR(3) NOT NULL,
    priority INT NOT NULL
);