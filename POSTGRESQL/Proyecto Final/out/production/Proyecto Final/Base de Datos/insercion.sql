-- ======================================================
-- SCRIPT DE INSERCIÓN DE DATOS (ORDENADO POR DEPENDENCIAS)
-- ======================================================

-- 1. INSERTAR ESCUELAS (No dependen de nadie)
-- ======================================================
INSERT INTO escuela (codigo_escuela, nombre, direccion, telefono, tipo) VALUES
(1, 'Kilo 7', 'Calle Principal #123, Camagüey', '32201122', 'Preuniversitario'),
(2, 'Cerámica', 'Ave. de los Mártires Km 4', '32254433', 'Politécnico'),
(3, 'A. Morell', 'Calle Finlay e/ 4ta y 5ta', '32298877', 'Secundaria Básica'),
(4, 'Antonio Maceo', 'Plaza de la Revolución S/N', '32215566', 'Primaria');

-- Sincronizar el secuenciador automático de la llave primaria de escuela
SELECT setval('escuela_codigo_escuela_seq', (SELECT MAX(codigo_escuela) FROM escuela));


-- 2. INSERTAR GRUPOS (Dependen de que exista la ESCUELA)
-- ======================================================
INSERT INTO grupo (codigo_grupo, nombre, codigo_escuela) VALUES
(101, 'Grupo 7mo-A', 1), -- Pertenece a Kilo 7
(102, 'Grupo 8vo-B', 1), -- Pertenece a Kilo 7
(201, 'Grupo 1ro Informática', 2), -- Pertenece a Cerámica
(301, 'Grupo 9no-C', 3); -- Pertenece a A. Morell

-- Sincronizar el secuenciador automático de la llave primaria de grupo
SELECT setval('grupo_codigo_grupo_seq', (SELECT MAX(codigo_grupo) FROM grupo));


-- 3. INSERTAR ALUMNOS (Dependen de que exista el GRUPO)
-- ======================================================
INSERT INTO alumno (
    ci, nombre1ro, nombre2do, apellido1ro, apellido2do, 
    fecha_nacimiento, sexo, color_piel, municipio, consejo_popular, 
    grado, regimen, sesion, estatus_alumno, especialidad, 
    procedencia_social_padre, procedencia_social_madre, direccion, telefono, 
    codigo_grupo
) VALUES
(
    '05031234567', 'Luis', 'Daniel', 'García', 'Alfonso', 
    '2005-03-12', 'Masculino', 'Blanco', 'Camagüey', 'Garrido', 
    '11no', 'Interno', 'Mañana', 'Activo', 'Ciencias Exactas', 
    'Obrero', 'Profesional', 'Calle Central #45', '55443322', 
    101 -- Se integra al grupo 101
),
(
    '06072298765', 'María', NULL, 'Pérez', 'Rodríguez', 
    '2006-07-22', 'Femenino', 'Mulato', 'Camagüey', 'Centro', 
    '12no', 'Externo', 'Tarde', 'Activo', 'Letras', 
    'Campesino', 'Ama de Casa', 'Ave. Avellaneda #210', NULL, 
    102 -- Se integra al grupo 102
);


-- 4. INSERTAR PROFESORES (No dependen de escuela ni grupo directamente)
-- ======================================================
INSERT INTO profesor (
    ci, nombre1ro, nombre2do, apellido1ro, apellido2do, 
    edad, sexo, color_piel, direccion_particular, telefono_particular, 
    municipio, integracion_politica, centro_graduacion, anio_graduacion, 
    anio_inicio_trabajo, procedencia, categoria_docente, categoria_cientifica, 
    mision_internacionalista, es_cuadro, cargo, nivel_ensenanza, se_supera, 
    ultima_evaluacion_profesional
) VALUES
(
    '75081299887', 'Carlos', 'Alberto', 'Fernández', 'Gómez', 
    50, 'Masculino', 'Blanco', 'Calle San Fernando #302', '52112233', 
    'Camagüey', 'PCC', 'UCP José Martí', 1998, 
    1999, 'Urbana', 'Asistente', 'Máster', 
    TRUE, FALSE, 'Profesor de Matemática', 'Media Superior', TRUE, 
    'Excelente'
),
(
    '82110411223', 'Ana', 'Luisa', 'Martínez', 'Silva', 
    44, 'Femenino', 'Negro', 'Calle Cisneros #15', '53887766', 
    'Camagüey', 'FMC', 'Universidad de Camagüey', 2005, 
    2006, 'Urbana', 'Instructor', 'Ninguna', 
    FALSE, TRUE, 'Jefa de Cátedra', 'Media', FALSE, 
    'Bien'
);


-- 5. INSERTAR ASIGNATURAS (No dependen de nadie)
-- ======================================================
INSERT INTO asignatura (codigo_asignatura, nombre) VALUES
(1, 'Matemática'),
(2, 'Física'),
(3, 'Historia de Cuba'),
(4, 'Programación Básica');

-- Sincronizar el secuenciador automático de la llave primaria de asignatura
SELECT setval('asignatura_codigo_asignatura_seq', (SELECT MAX(codigo_asignatura) FROM asignatura));


-- 6. RELACIÓN TERNARIA: IMPARTE (Depende de Profesor, Grupo y Asignatura)
-- ======================================================
INSERT INTO imparte (id_profesor, id_grupo, codigo_asignatura) VALUES
('75081299887', 101, 1), -- Carlos Fernández imparte Matemática al Grupo 101
('75081299887', 102, 1), -- Carlos Fernández imparte Matemática al Grupo 102
('82110411223', 201, 4); -- Ana Martínez imparte Programación Básica al Grupo 201