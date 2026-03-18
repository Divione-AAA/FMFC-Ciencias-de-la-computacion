--
-- PostgreSQL database dump
--

-- Dumped from database version 17.4
-- Dumped by pg_dump version 17.4

-- Started on 2026-03-17 11:58:49

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
-- TOC entry 865 (class 1247 OID 24687)
-- Name: direccion; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.direccion AS (
	calle character varying(50),
	numero integer,
	municipio character varying(50),
	provincia character varying(50)
);


ALTER TYPE public.direccion OWNER TO postgres;

--
-- TOC entry 849 (class 1247 OID 24645)
-- Name: dom_cid; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.dom_cid AS character(11)
	CONSTRAINT dom_cid_check CHECK ((VALUE ~ '^[0-9]{11}$'::text));


ALTER DOMAIN public.dom_cid OWNER TO postgres;

--
-- TOC entry 859 (class 1247 OID 24670)
-- Name: procsocial; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.procsocial AS ENUM (
    'Obrero',
    'Campesino'
);


ALTER TYPE public.procsocial OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 24655)
-- Name: estaa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estaa (
    cid public.dom_cid NOT NULL,
    nivel integer,
    salario numeric(10,2),
    fnombramiento date,
    CONSTRAINT ck_nivel CHECK ((nivel = ANY (ARRAY[1, 2, 3, 4])))
);


ALTER TABLE public.estaa OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24647)
-- Name: estgral; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estgral (
    cid public.dom_cid NOT NULL,
    nombres character varying(50) NOT NULL,
    apellidos character varying(50) NOT NULL,
    sexo character(1),
    fechaingreso date DEFAULT CURRENT_DATE,
    dir public.direccion,
    CONSTRAINT ck_sexo CHECK ((sexo = ANY (ARRAY['F'::bpchar, 'f'::bpchar, 'M'::bpchar, 'm'::bpchar])))
);


ALTER TABLE public.estgral OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24675)
-- Name: evaluacion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.evaluacion (
    cid public.dom_cid,
    asignatura character varying(50),
    notas integer[]
);


ALTER TABLE public.evaluacion OWNER TO postgres;

--
-- TOC entry 4868 (class 0 OID 24655)
-- Dependencies: 218
-- Data for Name: estaa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estaa (cid, nivel, salario, fnombramiento) FROM stdin;
\.


--
-- TOC entry 4867 (class 0 OID 24647)
-- Dependencies: 217
-- Data for Name: estgral; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estgral (cid, nombres, apellidos, sexo, fechaingreso, dir) FROM stdin;
\.


--
-- TOC entry 4869 (class 0 OID 24675)
-- Dependencies: 219
-- Data for Name: evaluacion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.evaluacion (cid, asignatura, notas) FROM stdin;
\.


--
-- TOC entry 4719 (class 2606 OID 24661)
-- Name: estaa estaa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estaa
    ADD CONSTRAINT estaa_pkey PRIMARY KEY (cid);


--
-- TOC entry 4717 (class 2606 OID 24654)
-- Name: estgral estgral_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estgral
    ADD CONSTRAINT estgral_pkey PRIMARY KEY (cid);


--
-- TOC entry 4720 (class 2606 OID 24662)
-- Name: estaa fk_est; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estaa
    ADD CONSTRAINT fk_est FOREIGN KEY (cid) REFERENCES public.estgral(cid);


--
-- TOC entry 4721 (class 2606 OID 24680)
-- Name: evaluacion fk_eval; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.evaluacion
    ADD CONSTRAINT fk_eval FOREIGN KEY (cid) REFERENCES public.estgral(cid);


-- Completed on 2026-03-17 11:58:49

--
-- PostgreSQL database dump complete
--

