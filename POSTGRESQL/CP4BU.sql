--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2026-06-12 16:02:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 225 (class 1255 OID 32816)
-- Name: aumentar_salarios(integer); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.aumentar_salarios(IN p_dno integer)
    LANGUAGE plpgsql
    AS $$
BEGIN

    UPDATE Empleado
    SET salario =
        CASE
            WHEN salario < 1000
                THEN salario * 1.15

            WHEN salario BETWEEN 1000 AND 2000
                THEN salario * 1.10

            ELSE salario * 1.05
        END
    WHERE dno = p_dno;

END;
$$;


ALTER PROCEDURE public.aumentar_salarios(IN p_dno integer) OWNER TO postgres;

--
-- TOC entry 227 (class 1255 OID 32820)
-- Name: aviso_aumento(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.aviso_aumento() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN

    IF NEW.salario > OLD.salario * 1.10 THEN

        RAISE NOTICE
        'El salario aumentó más del 10%%';

    END IF;

    RETURN NEW;

END;
$$;


ALTER FUNCTION public.aviso_aumento() OWNER TO postgres;

--
-- TOC entry 228 (class 1255 OID 32822)
-- Name: impedir_cambio_fecha(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.impedir_cambio_fecha() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN

    IF NEW.fechanacimiento <> OLD.fechanacimiento THEN

        RAISE EXCEPTION
        'La fecha de nacimiento no puede modificarse';

    END IF;

    RETURN NEW;

END;
$$;


ALTER FUNCTION public.impedir_cambio_fecha() OWNER TO postgres;

--
-- TOC entry 229 (class 1255 OID 32832)
-- Name: registrar_cambio_presupuesto(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.registrar_cambio_presupuesto() RETURNS trigger
    LANGUAGE plpgsql
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
$$;


ALTER FUNCTION public.registrar_cambio_presupuesto() OWNER TO postgres;

--
-- TOC entry 222 (class 1255 OID 32813)
-- Name: salario_total_departamento(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.salario_total_departamento(p_dno integer) RETURNS numeric
    LANGUAGE plpgsql
    AS $$
DECLARE
    total NUMERIC;
BEGIN

    SELECT COALESCE(SUM(salario),0)
    INTO total
    FROM Empleado
    WHERE dno = p_dno;

    RETURN total;

END;
$$;


ALTER FUNCTION public.salario_total_departamento(p_dno integer) OWNER TO postgres;

--
-- TOC entry 223 (class 1255 OID 32814)
-- Name: salarios_por_departamento(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.salarios_por_departamento() RETURNS TABLE(departamento integer, total_salarios numeric)
    LANGUAGE plpgsql
    AS $$
BEGIN

    RETURN QUERY
    SELECT dno,
           SUM(salario)
    FROM Empleado
    GROUP BY dno;

END;
$$;


ALTER FUNCTION public.salarios_por_departamento() OWNER TO postgres;

--
-- TOC entry 224 (class 1255 OID 32815)
-- Name: transferir_empleado(integer, integer); Type: PROCEDURE; Schema: public; Owner: postgres
--

CREATE PROCEDURE public.transferir_empleado(IN p_nroc integer, IN p_nuevo_dno integer)
    LANGUAGE plpgsql
    AS $$
DECLARE

    v_salario NUMERIC;
    v_dno_actual INTEGER;

BEGIN

    SELECT salario, dno
    INTO v_salario, v_dno_actual
    FROM Empleado
    WHERE nroc = p_nroc;

    IF NOT FOUND THEN
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
$$;


ALTER PROCEDURE public.transferir_empleado(IN p_nroc integer, IN p_nuevo_dno integer) OWNER TO postgres;

--
-- TOC entry 226 (class 1255 OID 32817)
-- Name: verificar_limite_salarios(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.verificar_limite_salarios() RETURNS trigger
    LANGUAGE plpgsql
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
$$;


ALTER FUNCTION public.verificar_limite_salarios() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 221 (class 1259 OID 32825)
-- Name: auditoriapresupuesto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auditoriapresupuesto (
    id integer NOT NULL,
    dno integer,
    presupuesto_anterior numeric(12,2),
    presupuesto_nuevo numeric(12,2),
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.auditoriapresupuesto OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 32824)
-- Name: auditoriapresupuesto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auditoriapresupuesto_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.auditoriapresupuesto_id_seq OWNER TO postgres;

--
-- TOC entry 4886 (class 0 OID 0)
-- Dependencies: 220
-- Name: auditoriapresupuesto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auditoriapresupuesto_id_seq OWNED BY public.auditoriapresupuesto.id;


--
-- TOC entry 217 (class 1259 OID 32788)
-- Name: dpto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.dpto (
    dno integer NOT NULL,
    nombred character varying(50),
    salariot numeric(12,2),
    presupuesto numeric(12,2)
);


ALTER TABLE public.dpto OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 32793)
-- Name: empleado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empleado (
    nroc integer NOT NULL,
    nombre character varying(50),
    salario numeric(12,2),
    dno integer,
    fechanacimiento date
);


ALTER TABLE public.empleado OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 32808)
-- Name: test; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test (
    dno integer NOT NULL,
    nombred character varying(50),
    salariot numeric(12,2)
);


ALTER TABLE public.test OWNER TO postgres;

--
-- TOC entry 4715 (class 2604 OID 32828)
-- Name: auditoriapresupuesto id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditoriapresupuesto ALTER COLUMN id SET DEFAULT nextval('public.auditoriapresupuesto_id_seq'::regclass);


--
-- TOC entry 4880 (class 0 OID 32825)
-- Dependencies: 221
-- Data for Name: auditoriapresupuesto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auditoriapresupuesto (id, dno, presupuesto_anterior, presupuesto_nuevo, fecha) FROM stdin;
\.


--
-- TOC entry 4876 (class 0 OID 32788)
-- Dependencies: 217
-- Data for Name: dpto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.dpto (dno, nombred, salariot, presupuesto) FROM stdin;
\.


--
-- TOC entry 4877 (class 0 OID 32793)
-- Dependencies: 218
-- Data for Name: empleado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.empleado (nroc, nombre, salario, dno, fechanacimiento) FROM stdin;
\.


--
-- TOC entry 4878 (class 0 OID 32808)
-- Dependencies: 219
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test (dno, nombred, salariot) FROM stdin;
\.


--
-- TOC entry 4887 (class 0 OID 0)
-- Dependencies: 220
-- Name: auditoriapresupuesto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auditoriapresupuesto_id_seq', 1, false);


--
-- TOC entry 4724 (class 2606 OID 32831)
-- Name: auditoriapresupuesto auditoriapresupuesto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auditoriapresupuesto
    ADD CONSTRAINT auditoriapresupuesto_pkey PRIMARY KEY (id);


--
-- TOC entry 4718 (class 2606 OID 32792)
-- Name: dpto dpto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.dpto
    ADD CONSTRAINT dpto_pkey PRIMARY KEY (dno);


--
-- TOC entry 4720 (class 2606 OID 32797)
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (nroc);


--
-- TOC entry 4722 (class 2606 OID 32812)
-- Name: test test_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.test
    ADD CONSTRAINT test_pkey PRIMARY KEY (dno);


--
-- TOC entry 4727 (class 2620 OID 32821)
-- Name: empleado trg_aumento; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trg_aumento BEFORE UPDATE OF salario ON public.empleado FOR EACH ROW EXECUTE FUNCTION public.aviso_aumento();


--
-- TOC entry 4728 (class 2620 OID 32823)
-- Name: empleado trg_fecha_nacimiento; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trg_fecha_nacimiento BEFORE UPDATE ON public.empleado FOR EACH ROW EXECUTE FUNCTION public.impedir_cambio_fecha();


--
-- TOC entry 4729 (class 2620 OID 32818)
-- Name: empleado trg_limite_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trg_limite_insert BEFORE INSERT ON public.empleado FOR EACH ROW EXECUTE FUNCTION public.verificar_limite_salarios();


--
-- TOC entry 4730 (class 2620 OID 32819)
-- Name: empleado trg_limite_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trg_limite_update BEFORE UPDATE OF salario, dno ON public.empleado FOR EACH ROW EXECUTE FUNCTION public.verificar_limite_salarios();


--
-- TOC entry 4726 (class 2620 OID 32833)
-- Name: dpto trg_presupuesto; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER trg_presupuesto AFTER UPDATE OF presupuesto ON public.dpto FOR EACH ROW EXECUTE FUNCTION public.registrar_cambio_presupuesto();


--
-- TOC entry 4725 (class 2606 OID 32798)
-- Name: empleado empleado_dno_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_dno_fkey FOREIGN KEY (dno) REFERENCES public.dpto(dno);


-- Completed on 2026-06-12 16:02:36

--
-- PostgreSQL database dump complete
--

