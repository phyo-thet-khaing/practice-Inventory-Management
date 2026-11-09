CREATE TABLE category_translation (
    id BIGINT NOT NULL AUTO_INCREMENT,
    category_id BIGINT NOT NULL,
    language_code VARCHAR(10) NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (id),

    CONSTRAINT fk_category_translation_category
        FOREIGN KEY (category_id)
        REFERENCES category(id)
        ON DELETE CASCADE,

    CONSTRAINT uk_category_translation_language
        UNIQUE (category_id, language_code)
);