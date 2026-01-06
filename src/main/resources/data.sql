-- Inserir cafés iniciais
INSERT INTO coffees (name, price, intensity, available, body, sweetness, acidity, bitterness, description, origin, aroma, caffeine_level, type, bean_variety)
VALUES ('Espresso', 1.20, 'High', TRUE, 'Strong', 'Low', 'Medium', 'High', 'Classic espresso shot', 'Italy', 'Intense', 'High', 'Espresso', 'Arabica');

INSERT INTO coffees (name, price, intensity, available, body, sweetness, acidity, bitterness, description, origin, aroma, caffeine_level, type, bean_variety)
VALUES ('Cappuccino', 1.80, 'Medium', TRUE, 'Creamy', 'Medium', 'Low', 'Low', 'Espresso with steamed milk and foam', 'Italy', 'Milky', 'Medium', 'Cappuccino', 'Arabica');

INSERT INTO coffees (name, price, intensity, available, body, sweetness, acidity, bitterness, description, origin, aroma, caffeine_level, type, bean_variety)
VALUES ('Mocha', 2.20, 'Medium', TRUE, 'Rich', 'High', 'Low', 'Medium', 'Espresso with chocolate and milk', 'Yemen', 'Chocolatey', 'Medium', 'Mocha', 'Arabica');

INSERT INTO coffees (name, price, intensity, available, body, sweetness, acidity, bitterness, description, origin, aroma, caffeine_level, type, bean_variety)
VALUES ('Flat White', 2.00, 'Medium', FALSE, 'Smooth', 'Medium', 'Low', 'Low', 'Espresso with velvety microfoam', 'Australia', 'Creamy', 'Medium', 'Flat White', 'Arabica');

INSERT INTO coffees (name, price, intensity, available, body, sweetness, acidity, bitterness, description, origin, aroma, caffeine_level, type, bean_variety)
VALUES ('Macchiato', 1.50, 'High', FALSE, 'Strong', 'Low', 'Medium', 'High', 'Espresso with a dash of milk foam', 'Italy', 'Bold', 'High', 'Macchiato', 'Robusta');

-- Inserir estado inicial da máquina
INSERT INTO machine_status (water_ml, cups_made, maintenance_required)
VALUES (1000, 0, FALSE);

-- Inserir utilizadores iniciais
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$12$loTaPwXUJsas7UqPWspiKunY/0Lxp2jvK2hF5j3CpFAvgFfPOHN8e', 'ADMIN');

INSERT INTO users (username, password, role)
VALUES ('joao', '$2a$12$HvdcfCgvlwWResYzVLt7iuqh5Nj66YgUZ6uPTFiGLkbFfp5AHabe6', 'USER');
INSERT INTO users (username, password, role)
VALUES ('joao2', '$2a$12$HvdcfCgvlwWResYzVLt7iuqh5Nj66YgUZ6uPTFiGLkbFfp5AHabe6', 'USER');
