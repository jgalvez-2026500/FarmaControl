USE FarmaControl_db;

-- Enfermera de prueba:  usuario = enfermera  |  clave = enfermera123
INSERT INTO usuario(nombre_usuario, clave, nombre_completo, user_status) VALUES
('enfermera', SHA2('enfermera123', 256), 'Enfermera del Colegio', TRUE),
('suplente',  SHA2('suplente123', 256),  'Enfermera Suplente (cuenta inactiva)', FALSE);

INSERT INTO laboratorio(nombre_lab, pais_origen) VALUES
('Bayer',             'Alemania'),
('Pfizer',            'Estados Unidos'),
('Genfar',            'Colombia'),
('Laboratorios Bonin','Guatemala'),
('Sanofi',            'Francia');

INSERT INTO medicamento(nombre, miligramos, cantidad_stock, fecha_caducidad, id_laboratorio) VALUES
('Paracetamol',  500.00, 40, '2027-06-30', 3),
('Ibuprofeno',   400.00, 25, '2027-03-15', 1),
('Loratadina',    10.00, 18, '2026-12-01', 4),
('Amoxicilina',  500.00, 12, '2026-09-30', 2),   -- ya vencido (ejemplo)
('Jarabe Ambroxol', 15.00, 8, '2027-01-20', 5);