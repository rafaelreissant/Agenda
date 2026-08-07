CREATE TABLE tb_appointment (
    id UUID PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    start_date_time TIMESTAMP NOT NULL,
    end_date_time TIMESTAMP NOT NULL,
    priority VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    category_id UUID NOT NULL,

    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES tb_category(id)
);