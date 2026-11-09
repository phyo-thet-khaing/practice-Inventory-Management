ALTER TABLE product
ADD COLUMN image_id BIGINT NULL;

ALTER TABLE product
ADD CONSTRAINT fk_product_image
FOREIGN KEY (image_id)
REFERENCES image(id);