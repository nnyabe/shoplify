INSERT INTO users (username, password, email, first_name, last_name) VALUES
                                                                         ('john_doe', 'password1', 'john@example.com', 'John', 'Doe'),
                                                                         ('jane_smith', 'password2', 'jane@example.com', 'Jane', 'Smith'),
                                                                         ('mark_jones', 'password3', 'mark@example.com', 'Mark', 'Jones'),
                                                                         ('lucy_brown', 'password4', 'lucy@example.com', 'Lucy', 'Brown'),
                                                                         ('alice_white', 'password5', 'alice@example.com', 'Alice', 'White'),
                                                                         ('bob_black', 'password6', 'bob@example.com', 'Bob', 'Black'),
                                                                         ('carol_green', 'password7', 'carol@example.com', 'Carol', 'Green'),
                                                                         ('dave_young', 'password8', 'dave@example.com', 'Dave', 'Young'),
                                                                         ('emma_red', 'password9', 'emma@example.com', 'Emma', 'Red'),
                                                                         ('frank_blue', 'password10', 'frank@example.com', 'Frank', 'Blue'),
                                                                         ('grace_purple', 'password11', 'grace@example.com', 'Grace', 'Purple'),
                                                                         ('hannah_pink', 'password12', 'hannah@example.com', 'Hannah', 'Pink'),
                                                                         ('ian_gold', 'password13', 'ian@example.com', 'Ian', 'Gold'),
                                                                         ('jack_silver', 'password14', 'jack@example.com', 'Jack', 'Silver'),
                                                                         ('katie_orange', 'password15', 'katie@example.com', 'Katie', 'Orange'),
                                                                         ('luke_brown', 'password16', 'luke@example.com', 'Luke', 'Brown'),
                                                                         ('mona_silver', 'password17', 'mona@example.com', 'Mona', 'Silver'),
                                                                         ('nina_gray', 'password18', 'nina@example.com', 'Nina', 'Gray'),
                                                                         ('oliver_blue', 'password19', 'oliver@example.com', 'Oliver', 'Blue'),
                                                                         ('paul_black', 'password20', 'paul@example.com', 'Paul', 'Black');

INSERT INTO address (address_line_1, address_line_2, country, city, user_id) VALUES
                                                                                 ('123 Main St', 'Apt 101', 'USA', 'New York', 1),
                                                                                 ('456 Elm St', 'Suite 200', 'Canada', 'Toronto', 2),
                                                                                 ('789 Oak St', 'Building A', 'UK', 'London', 3),
                                                                                 ('101 Pine St', 'Floor 2', 'Germany', 'Berlin', 4),
                                                                                 ('202 Maple St', 'Apt 303', 'France', 'Paris', 5),
                                                                                 ('303 Birch St', 'Unit 10', 'USA', 'Los Angeles', 6),
                                                                                 ('404 Cedar St', 'Floor 1', 'Australia', 'Sydney', 7),
                                                                                 ('505 Ash St', 'Suite 5', 'Canada', 'Vancouver', 8),
                                                                                 ('606 Fir St', 'Apt 4', 'New Zealand', 'Auckland', 9),
                                                                                 ('707 Redwood St', 'Floor 3', 'Ireland', 'Dublin', 10),
                                                                                 ('808 Palm St', 'Building 5', 'Mexico', 'Mexico City', 11),
                                                                                 ('909 Willow St', 'Apt 6', 'Brazil', 'São Paulo', 12),
                                                                                 ('1010 Chestnut St', 'Suite 7', 'India', 'Mumbai', 13),
                                                                                 ('1111 Magnolia St', 'Floor 4', 'South Africa', 'Cape Town', 14),
                                                                                 ('1212 Juniper St', 'Unit 12', 'China', 'Beijing', 15),
                                                                                 ('1313 Sequoia St', 'Suite 13', 'Japan', 'Tokyo', 16),
                                                                                 ('1414 Pine St', 'Apt 9', 'Italy', 'Rome', 17),
                                                                                 ('1515 Spruce St', 'Floor 10', 'Spain', 'Madrid', 18),
                                                                                 ('1616 Maple St', 'Building 8', 'Argentina', 'Buenos Aires', 19),
                                                                                 ('1717 Oak St', 'Unit 11', 'Egypt', 'Cairo', 20);

INSERT INTO products (name, description, details, price) VALUES
                                                             ('Laptop', 'High-end laptop', '16GB RAM, 512GB SSD', 999.99),
                                                             ('Smartphone', 'Latest model', '6.5-inch display, 128GB storage', 699.99),
                                                             ('Tablet', 'Portable tablet', '10-inch screen, 64GB storage', 399.99),
                                                             ('Smartwatch', 'Wearable smartwatch', 'Fitness tracker, heart rate monitor', 199.99),
                                                             ('Headphones', 'Noise-canceling headphones', 'Wireless, over-ear', 149.99),
                                                             ('Camera', 'Digital camera', '24MP, 4K video recording', 899.99),
                                                             ('Keyboard', 'Mechanical keyboard', 'RGB backlight, wireless', 79.99),
                                                             ('Mouse', 'Wireless mouse', 'Ergonomic design, rechargeable', 29.99),
                                                             ('Monitor', 'LED monitor', '27-inch, Full HD', 249.99),
                                                             ('Printer', 'Inkjet printer', 'Wireless, high-quality printing', 120.99),
                                                             ('Speakers', 'Bluetooth speakers', 'Portable, 360-degree sound', 99.99),
                                                             ('Smart TV', 'Ultra HD Smart TV', '55-inch, 4K resolution', 599.99),
                                                             ('Game Console', 'Gaming console', 'PlayStation 5, 1TB storage', 499.99),
                                                             ('External HDD', 'External hard drive', '1TB, USB 3.0', 59.99),
                                                             ('Smart Bulb', 'Smart light bulb', 'Wi-Fi enabled, color-changing', 19.99),
                                                             ('Router', 'Wi-Fi router', 'Dual-band, 1Gbps speed', 69.99),
                                                             ('Vacuum Cleaner', 'Robot vacuum', 'Automatic, smart navigation', 299.99),
                                                             ('Blender', 'High-speed blender', 'Smoothie maker, 1000W', 99.99),
                                                             ('Coffee Maker', 'Automatic coffee machine', '12-cup capacity', 79.99),
                                                             ('Toaster', 'Two-slice toaster', 'Stainless steel, adjustable browning', 39.99);

INSERT INTO orders (user_id, address_id) VALUES
                                             (1, 1), (2, 2), (3, 3), (4, 4), (5, 5),
                                             (6, 6), (7, 7), (8, 8), (9, 9), (10, 10),
                                             (11, 11), (12, 12), (13, 13), (14, 14), (15, 15),
                                             (16, 16), (17, 17), (18, 18), (19, 19), (20, 20);


INSERT INTO order_quantity (quantity, product_id, order_id) VALUES
                                                                (2, 1, 1), (3, 2, 2), (1, 3, 3), (4, 4, 4), (2, 5, 5),
                                                                (1, 6, 6), (5, 7, 7), (3, 8, 8), (2, 9, 9), (1, 10, 10),
                                                                (2, 11, 11), (1, 12, 12), (3, 13, 13), (4, 14, 14), (2, 15, 15),
                                                                (5, 16, 16), (3, 17, 17), (1, 18, 18), (4, 19, 19), (2, 20, 20);


INSERT INTO inventory (product_id, quantity) VALUES
                                                 (1, 100), (2, 200), (3, 150), (4, 80), (5, 250),
                                                 (6, 300), (7, 120), (8, 90), (9, 75), (10, 60),
                                                 (11, 50), (12, 150), (13, 180), (14, 130), (15, 160),
                                                 (16, 140), (17, 170), (18, 90), (19, 210), (20, 130);
