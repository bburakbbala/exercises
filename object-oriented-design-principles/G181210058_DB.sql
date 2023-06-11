--
-- PostgreSQL database dump
--

-- Dumped from database version 12.0
-- Dumped by pg_dump version 12rc1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: _User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."_User" (
    "Id" integer NOT NULL,
    "userName" character varying(100) NOT NULL,
    password character varying(20) NOT NULL
);


ALTER TABLE public."_User" OWNER TO postgres;

--
-- Name: _User_Id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."_User_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."_User_Id_seq" OWNER TO postgres;

--
-- Name: _User_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."_User_Id_seq" OWNED BY public."_User"."Id";


--
-- Name: _User Id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."_User" ALTER COLUMN "Id" SET DEFAULT nextval('public."_User_Id_seq"'::regclass);


--
-- Data for Name: _User; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."_User" VALUES (1, 'burak', '12345');


--
-- Name: _User_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."_User_Id_seq"', 2, true);


--
-- Name: _User _User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."_User"
    ADD CONSTRAINT "_User_pkey" PRIMARY KEY ("Id");


--
-- PostgreSQL database dump complete
--

