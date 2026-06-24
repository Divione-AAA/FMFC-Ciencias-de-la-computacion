-- ==========================================
-- CREAR BASE DE DATOS
-- ==========================================

CREATE DATABASE edja_camaguey;

-- ==========================================
-- TABLA ESCUELA
-- ==========================================

CREATE TABLE escuela (
    id SERIAL PRIMARY KEY,
    codigo_escuela VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    direccion TEXT NOT NULL,
    telefono VARCHAR(30),
    tipo VARCHAR(80)
);



-- ==========================================
-- TABLA GRUPO
-- ==========================================

CREATE TABLE grupo (

    id SERIAL PRIMARY KEY,

    codigo_grupo VARCHAR(20) UNIQUE NOT NULL,

    nombre VARCHAR(100),

    escuela_id INTEGER NOT NULL,

    CONSTRAINT fk_grupo_escuela
        FOREIGN KEY (escuela_id)
        REFERENCES escuela(id)
        ON DELETE RESTRICT
);



-- ==========================================
-- TABLA ASIGNATURA
-- ==========================================

CREATE TABLE asignatura (

    id SERIAL PRIMARY KEY,

    codigo_asignatura VARCHAR(20)
        UNIQUE
        NOT NULL,

    nombre VARCHAR(120)
        NOT NULL
);



-- ==========================================
-- TABLA ALUMNO
-- ==========================================

CREATE TABLE alumno (

    id SERIAL PRIMARY KEY,

    ci CHAR(11)
        UNIQUE
        NOT NULL,

    nombre1 VARCHAR(50) NOT NULL,

    nombre2 VARCHAR(50),

    apellido1 VARCHAR(50) NOT NULL,

    apellido2 VARCHAR(50),

    fecha_nacimiento DATE,

    sexo VARCHAR(20),

    color_piel VARCHAR(40),

    municipio VARCHAR(100),

    consejo_popular VARCHAR(100),

    grado VARCHAR(50),

    regimen VARCHAR(50),

    sesion VARCHAR(50),

    estatus_alumno VARCHAR(50),

    especialidad VARCHAR(100),

    procedencia_social_padre VARCHAR(100),

    procedencia_social_madre VARCHAR(100),

    direccion TEXT,

    telefono VARCHAR(30),

    grupo_id INTEGER NOT NULL,

    CONSTRAINT fk_alumno_grupo
        FOREIGN KEY (grupo_id)
        REFERENCES grupo(id)
        ON DELETE RESTRICT
);



-- ==========================================
-- TABLA PROFESOR
-- ==========================================

CREATE TABLE profesor (

    id SERIAL PRIMARY KEY,

    ci CHAR(11)
        UNIQUE
        NOT NULL,

    nombre1 VARCHAR(50),

    nombre2 VARCHAR(50),

    apellido1 VARCHAR(50),

    apellido2 VARCHAR(50),

    edad INTEGER,

    sexo VARCHAR(20),

    color_piel VARCHAR(50),

    direccion_particular TEXT,

    telefono_particular VARCHAR(30),

    municipio VARCHAR(100),

    integracion_politica VARCHAR(80),

    centro_graduacion VARCHAR(150),

    anio_graduacion INTEGER,

    anio_inicio_trabajo INTEGER,

    procedencia VARCHAR(100),

    categoria_docente VARCHAR(100),

    categoria_cientifica VARCHAR(100),

    mision_internacionalista BOOLEAN,

    es_cuadro BOOLEAN,

    cargo VARCHAR(100),

    nivel_ensenanza VARCHAR(100),

    se_supera BOOLEAN,

    ultima_evaluacion_profesional VARCHAR(100),

    escuela_id INTEGER NOT NULL,

    CONSTRAINT fk_profesor_escuela
        FOREIGN KEY (escuela_id)
        REFERENCES escuela(id)
        ON DELETE RESTRICT
);



-- ==========================================
-- INTERRELACION TERNARIA IMPARTE
-- ==========================================

CREATE TABLE imparte (

    profesor_id INTEGER NOT NULL,

    grupo_id INTEGER NOT NULL,

    asignatura_id INTEGER NOT NULL,

    PRIMARY KEY (
        profesor_id,
        grupo_id,
        asignatura_id
    ),

    CONSTRAINT fk_imparte_profesor
        FOREIGN KEY (profesor_id)
        REFERENCES profesor(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_imparte_grupo
        FOREIGN KEY (grupo_id)
        REFERENCES grupo(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_imparte_asignatura
        FOREIGN KEY (asignatura_id)
        REFERENCES asignatura(id)
        ON DELETE CASCADE
);



-- ==========================================
-- INDICES
-- ==========================================

CREATE INDEX idx_alumno_grupo
ON alumno(grupo_id);

CREATE INDEX idx_profesor_escuela
ON profesor(escuela_id);

CREATE INDEX idx_grupo_escuela
ON grupo(escuela_id);

CREATE INDEX idx_imparte_profesor
ON imparte(profesor_id);

CREATE INDEX idx_imparte_grupo
ON imparte(grupo_id);

CREATE INDEX idx_imparte_asignatura
ON imparte(asignatura_id);



-- ==========================================
-- VALIDACIONES
-- ==========================================

ALTER TABLE profesor
ADD CONSTRAINT chk_edad
CHECK (edad >= 18);

ALTER TABLE profesor
ADD CONSTRAINT chk_anio_graduacion
CHECK (
    anio_graduacion >= 1950
);

ALTER TABLE profesor
ADD CONSTRAINT chk_inicio_trabajo
CHECK (
    anio_inicio_trabajo >= 1950
);