CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    category_id BIGINT NOT NULL,

    supplier_id BIGINT NOT NULL,

    name VARCHAR(100) NOT NULL,

    unit VARCHAR(20),

    price DECIMAL(10,2),

    image_url VARCHAR(500),

    status ENUM('ACTIVE', 'INACTIVE')
        DEFAULT 'ACTIVE',

    created_at DATETIME NOT NULL,

    updated_at DATETIME NOT NULL,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
        REFERENCES category(id)
);