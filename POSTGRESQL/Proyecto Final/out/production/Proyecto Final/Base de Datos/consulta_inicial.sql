-- ======================================================
-- ELIMINAR TABLAS ANTERIORES SI EXISTEN (LIMPIEZA TOTAL)
-- ======================================================
DROP TABLE IF EXISTS imparte CASCADE;
DROP TABLE IF EXISTS matricula CASCADE;
DROP TABLE IF EXISTS alumno CASCADE;
DROP TABLE IF EXISTS profesor CASCADE;
DROP TABLE IF EXISTS asignatura CASCADE;
DROP TABLE IF EXISTS grupo CASCADE;
DROP TABLE IF EXISTS escuela CASCADE;

-- ======================================================
-- ENTIDAD: ESCUELA
-- ======================================================
CREATE TABLE escuela (
    codigo_escuela SERIAL PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    tipo VARCHAR(60)
);

-- ======================================================
-- ENTIDAD: GRUPO
-- ======================================================
CREATE TABLE grupo (
    codigo_grupo SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL, -- Atributo explícito en el diagrama
    
    -- Relación PERTENECE (1,1) con Escuela
    codigo_escuela INTEGER NOT NULL,
    CONSTRAINT fk_grupo_escuela
        FOREIGN KEY (codigo_escuela)
        REFERENCES escuela (codigo_escuela)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

-- ======================================================
-- ENTIDAD: ALUMNO
-- ======================================================
CREATE TABLE alumno (
    ci VARCHAR(11) PRIMARY KEY,
    nombre1ro VARCHAR(40) NOT NULL,
    nombre2do VARCHAR(40),
    apellido1ro VARCHAR(40) NOT NULL,
    apellido2do VARCHAR(40),
    fecha_nacimiento DATE,
    sexo VARCHAR(15),
    color_piel VARCHAR(30),
    municipio VARCHAR(60),
    consejo_popular VARCHAR(80),
    grado VARCHAR(30),
    regimen VARCHAR(30),
    sesion VARCHAR(30),
    estatus_alumno VARCHAR(50), -- Corregido según diagrama (antes estado_alumno)
    especialidad VARCHAR(80),
    procedencia_social_padre VARCHAR(80),
    procedencia_social_madre VARCHAR(80),
    direccion VARCHAR(200),
    telefono VARCHAR(20),
    
    -- Relación INTEGRA (1,1) con Grupo
    codigo_grupo INTEGER NOT NULL,
    CONSTRAINT fk_alumno_grupo
        FOREIGN KEY (codigo_grupo)
        REFERENCES grupo (codigo_grupo)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

-- ======================================================
-- ENTIDAD: PROFESOR
-- ======================================================
CREATE TABLE profesor (
    ci VARCHAR(11) PRIMARY KEY,
    nombre1ro VARCHAR(40) NOT NULL,
    nombre2do VARCHAR(40),
    apellido1ro VARCHAR(40) NOT NULL,
    apellido2do VARCHAR(40),
    edad INTEGER,
    sexo VARCHAR(15),
    color_piel VARCHAR(30),
    direccion_particular VARCHAR(200),
    telefono_particular VARCHAR(20),
    municipio VARCHAR(60),
    integracion_politica VARCHAR(60),
    centro_graduacion VARCHAR(120),
    anio_graduacion INTEGER,
    anio_inicio_trabajo INTEGER,
    procedencia VARCHAR(120),
    categoria_docente VARCHAR(60),
    categoria_cientifica VARCHAR(60),
    mision_internacionalista BOOLEAN,
    es_cuadro BOOLEAN,
    cargo VARCHAR(80),
    nivel_ensenanza VARCHAR(60),
    se_supera BOOLEAN,
    ultima_evaluacion_profesional VARCHAR(80)
    
    -- Se elimina 'codigo_escuela' ya que PROFESOR no conecta directamente 
    -- con ESCUELA en el diagrama de la imagen_34d9fe.jpg.
);

-- ======================================================
-- ENTIDAD: ASIGNATURA
-- ======================================================
CREATE TABLE asignatura (
    codigo_asignatura SERIAL PRIMARY KEY, -- Corregido según diagrama (antes id_asignatura)
    nombre VARCHAR(100) NOT NULL UNIQUE
);

-- ======================================================
-- RELACIÓN TERNARIA: IMPARTE (PROFESOR - GRUPO - ASIGNATURA)
-- ======================================================
CREATE TABLE imparte (
    id_imparte SERIAL PRIMARY KEY,
    id_profesor VARCHAR(11) NOT NULL,
    id_grupo INTEGER NOT NULL,
    codigo_asignatura INTEGER NOT NULL, -- Actualizado por el cambio en la tabla asignatura

    CONSTRAINT fk_imparte_profesor
        FOREIGN KEY (id_profesor)
        REFERENCES profesor (ci)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT fk_imparte_grupo
        FOREIGN KEY (id_grupo)
        REFERENCES grupo (codigo_grupo)
        ON UPDATE CASCADE
        ON DELETE CASCADE,

    CONSTRAINT fk_imparte_asignatura
        FOREIGN KEY (codigo_asignatura)
        REFERENCES asignatura (codigo_asignatura)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- ======================================================
-- ÍNDICES OPTIMIZADOS PARA LAS RELACIONES
-- ======================================================
CREATE INDEX idx_alumno_grupo ON alumno (codigo_grupo);
CREATE INDEX idx_grupo_escuela ON grupo (codigo_escuela);
CREATE INDEX idx_imparte_profesor ON imparte (id_profesor);
CREATE INDEX idx_imparte_grupo ON imparte (id_grupo);
CREATE INDEX idx_imparte_asignatura ON imparte (codigo_asignatura);