
INSERT INTO user (id, username, password, role)
              VALUES (1,'miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','ADMIN');
INSERT INTO user (id, username, password, role)
              VALUES (2,'tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','USER');
INSERT INTO user (id, username, password, role)
              VALUES (3,'petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','USER');

INSERT INTO address (id, street, number) VALUES (1, 'Djordja Rajkovica', 8);
INSERT INTO address (id, street, number) VALUES (2, 'Bulevar Oslobodjenja', 2);
INSERT INTO address (id, street, number) VALUES (3, 'Jevrejska', 3);  
INSERT INTO address (id, street, number) VALUES (4, 'Futoska', 5);  

INSERT INTO scooter (id, product_code, battery_level, max_speed, rented, address_id) VALUES (1, 'T0001', 85, 25, true, 1);
INSERT INTO scooter (id, product_code, battery_level, max_speed, rented, address_id) VALUES (2, 'T0002', 90, 30, false, 3);
INSERT INTO scooter (id, product_code, battery_level, max_speed, rented, address_id) VALUES (3, 'T0003', 30, 35, false, 2);
INSERT INTO scooter (id, product_code, battery_level, max_speed, rented, address_id) VALUES (4, 'T0004', 100, 15, true, 4);

INSERT INTO reservation (id, renting_date, return_date, email, scooter_id) 
		VALUES (1, '2024-03-15 15:00', '2024-03-15 18:00', 'pera@gmail.com', 4);
INSERT INTO reservation (id, renting_date, return_date, email, scooter_id) 
		VALUES (2, '2024-03-16 18:00', '2024-03-16 20:00', 'marko@gmail.com', 1);
INSERT INTO reservation (id, renting_date, return_date, email, scooter_id) 
		VALUES (3, '2024-02-20 12:00', '2024-02-21 11:00', 'jelena@gmail.com', 2);
INSERT INTO reservation (id, renting_date, return_date, email, scooter_id) 
		VALUES (4, '2024-01-10 10:00', '2024-01-10 20:00', 'jovana@gmail.com', 3);







