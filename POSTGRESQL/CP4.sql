--- Creacion de las base de datos
CREATE Database CP4

CREATE TABLE Dpto (
    dno INTEGER PRIMARY KEY,
    nombred VARCHAR(50),
    salariot NUMERIC(12,2)
);

CREATE TABLE Empleado (
    nroc INTEGER PRIMARY KEY,
    nombre VARCHAR(50),
    salario NUMERIC(12,2),
    dno INTEGER REFERENCES Dpto(dno)
);
---ejercicio 1
--- Crea la funcion, si ya existe con ese nombre la renombra
CREATE OR REPLACE FUNCTION salario_total_departamento(
    p_dno INTEGER ---parametro de entrada
)
RETURNS NUMERIC
AS $$
DECLARE ---declaran variables internas
    total NUMERIC;
BEGIN ---comienza el code

    SELECT COALESCE(SUM(salario),0)
    INTO total
    FROM Empleado
    WHERE dno = p_dno;

    RETURN total;

END;
$$ LANGUAGE plpgsql;

---ejercicio 2
CREATE OR REPLACE FUNCTION salarios_por_departamento()
RETURNS TABLE(
    departamento INTEGER,
    total_salarios NUMERIC
)
AS $$
BEGIN

    RETURN QUERY
    SELECT dno,
           SUM(salario)
    FROM Empleado
    GROUP BY dno;

END;
$$ LANGUAGE plpgsql;

---ejerciciom 3
CREATE OR REPLACE PROCEDURE transferir_empleado(
    p_nroc INTEGER,
    p_nuevo_dno INTEGER
)
AS $$
DECLARE

    v_salario NUMERIC;
    v_dno_actual INTEGER;

BEGIN

    SELECT salario, dno
    INTO v_salario, v_dno_actual
    FROM Empleado
    WHERE nroc = p_nroc;

    IF NOT FOUND THEN---excepcion
        RAISE EXCEPTION 'Empleado no existe';
    END IF;

    UPDATE Empleado
    SET dno = p_nuevo_dno
    WHERE nroc = p_nroc;

    UPDATE Dpto
    SET salariot = salariot - v_salario
    WHERE dno = v_dno_actual;

    UPDATE Dpto
    SET salariot = salariot + v_salario
    WHERE dno = p_nuevo_dno;

END;
$$ LANGUAGE plpgsql;

--- ejercicio 4
CREATE OR REPLACE PROCEDURE aumentar_salarios(
    p_dno INTEGER
)
AS $$
BEGIN

    UPDATE Empleado
    SET salario =--- condicional
        CASE
            WHEN salario < 1000
                THEN salario * 1.15

            WHEN salario BETWEEN 1000 AND 2000
                THEN salario * 1.10

            ELSE salario * 1.05
        END
    WHERE dno = p_dno;

END;
$$ LANGUAGE plpgsql;

---5.a
CREATE OR REPLACE FUNCTION verificar_limite_salarios()
RETURNS TRIGGER
AS $$
DECLARE
    total NUMERIC;
BEGIN

    SELECT COALESCE(SUM(salario),0)
    INTO total
    FROM Empleado
    WHERE dno = NEW.dno;

    total := total + NEW.salario;

    IF total > 500000 THEN

        RAISE EXCEPTION
        'El total de salarios excede 500000';

    END IF;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;
---la opercacion de aumentar salarios es la que puede causar que el total excedan los 50k

---ejercicio 5.b
--- se crean los gatillos
CREATE TRIGGER trg_limite_insert
BEFORE INSERT
ON Empleado
FOR EACH ROW
EXECUTE FUNCTION verificar_limite_salarios();

CREATE TRIGGER trg_limite_update
BEFORE UPDATE OF salario,dno
ON Empleado
FOR EACH ROW
EXECUTE FUNCTION verificar_limite_salarios();

CREATE OR REPLACE FUNCTION aviso_aumento()
RETURNS TRIGGER
AS $$
BEGIN

    IF NEW.salario > OLD.salario * 1.10 THEN

        RAISE NOTICE
        'El salario aumentó más del 10%%';

    END IF;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;

---aqui se activa el gatillo
CREATE TRIGGER trg_aumento
BEFORE UPDATE OF salario
ON Empleado
FOR EACH ROW
EXECUTE FUNCTION aviso_aumento();

--- ejercicio 5.c
ALTER TABLE Empleado
ADD COLUMN fechanacimiento DATE;

CREATE OR REPLACE FUNCTION impedir_cambio_fecha()
RETURNS TRIGGER
AS $$
BEGIN

    IF NEW.fechanacimiento <> OLD.fechanacimiento THEN ---forma sencilla de verificar 

        RAISE EXCEPTION
        'La fecha de nacimiento no puede modificarse';

    END IF;

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;

--- se activa en cada consulta 
CREATE TRIGGER trg_fecha_nacimiento
BEFORE UPDATE
ON Empleado
FOR EACH ROW
EXECUTE FUNCTION impedir_cambio_fecha();

--- 5.d
ALTER TABLE Dpto
ADD COLUMN presupuesto NUMERIC(12,2);

--- Se crea una tabla presupuesto para manejar con mayor facilidad cambios
CREATE TABLE AuditoriaPresupuesto(

    id SERIAL PRIMARY KEY,

    dno INTEGER,

    presupuesto_anterior NUMERIC(12,2),

    presupuesto_nuevo NUMERIC(12,2),

    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

CREATE OR REPLACE FUNCTION registrar_cambio_presupuesto()
RETURNS TRIGGER
AS $$
BEGIN

    INSERT INTO AuditoriaPresupuesto(
        dno,
        presupuesto_anterior,
        presupuesto_nuevo
    )
    VALUES(
        OLD.dno,
        OLD.presupuesto,
        NEW.presupuesto
    );

    RETURN NEW;

END;
$$ LANGUAGE plpgsql;

--- gatillo referenciado
CREATE TRIGGER trg_presupuesto
AFTER UPDATE OF presupuesto
ON Dpto
FOR EACH ROW
EXECUTE FUNCTION registrar_cambio_presupuesto();