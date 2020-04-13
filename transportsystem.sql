--
-- PostgreSQL database dump
--

-- Dumped from database version 10.12
-- Dumped by pg_dump version 10.12

-- Started on 2020-04-07 13:22:11

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

--
-- TOC entry 1 (class 3079 OID 12924)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2921 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16394)
-- Name: authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authorities (
    username character varying(32) NOT NULL,
    authority character varying(50) NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.authorities OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16401)
-- Name: employeedocuments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employeedocuments (
    id integer NOT NULL,
    documenttype character varying(32) NOT NULL,
    series integer,
    number integer NOT NULL,
    issuedby character varying(64) NOT NULL,
    issueddate date,
    employeeid integer
);


ALTER TABLE public.employeedocuments OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16399)
-- Name: employeedocuments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employeedocuments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employeedocuments_id_seq OWNER TO postgres;

--
-- TOC entry 2922 (class 0 OID 0)
-- Dependencies: 197
-- Name: employeedocuments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employeedocuments_id_seq OWNED BY public.employeedocuments.id;


--
-- TOC entry 200 (class 1259 OID 16409)
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
    id integer NOT NULL,
    surname character varying(32) NOT NULL,
    name character varying(32) NOT NULL,
    patronymic character varying(32),
    department character varying(64) NOT NULL,
    "position" character varying(32) NOT NULL,
    address character varying(100),
    telephonenumber integer,
    dateofreceipt date NOT NULL,
    dateofdismissal date,
    userid integer
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16407)
-- Name: employees_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employees_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employees_id_seq OWNER TO postgres;

--
-- TOC entry 2923 (class 0 OID 0)
-- Dependencies: 199
-- Name: employees_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employees_id_seq OWNED BY public.employees.id;


--
-- TOC entry 202 (class 1259 OID 16417)
-- Name: engine; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.engine (
    id integer NOT NULL,
    name character varying(16) NOT NULL,
    volume double precision NOT NULL,
    fuel character varying(16) NOT NULL
);


ALTER TABLE public.engine OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16415)
-- Name: engine_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.engine_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.engine_id_seq OWNER TO postgres;

--
-- TOC entry 2924 (class 0 OID 0)
-- Dependencies: 201
-- Name: engine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.engine_id_seq OWNED BY public.engine.id;


--
-- TOC entry 204 (class 1259 OID 16425)
-- Name: goods; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.goods (
    id integer NOT NULL,
    nomenclatureid integer NOT NULL,
    amount integer NOT NULL,
    length double precision NOT NULL,
    height double precision NOT NULL,
    weight double precision NOT NULL,
    transportorderid integer,
    name character varying(255) NOT NULL
);


ALTER TABLE public.goods OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16423)
-- Name: goods_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.goods_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.goods_id_seq OWNER TO postgres;

--
-- TOC entry 2925 (class 0 OID 0)
-- Dependencies: 203
-- Name: goods_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.goods_id_seq OWNED BY public.goods.id;


--
-- TOC entry 206 (class 1259 OID 16433)
-- Name: nomenclatures; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nomenclatures (
    id integer NOT NULL,
    length double precision NOT NULL,
    height double precision NOT NULL,
    weight double precision NOT NULL,
    number character varying(255) NOT NULL,
    comments character varying,
    width double precision NOT NULL
);


ALTER TABLE public.nomenclatures OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16431)
-- Name: nomenclatures_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.nomenclatures_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.nomenclatures_id_seq OWNER TO postgres;

--
-- TOC entry 2926 (class 0 OID 0)
-- Dependencies: 205
-- Name: nomenclatures_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.nomenclatures_id_seq OWNED BY public.nomenclatures.id;


--
-- TOC entry 208 (class 1259 OID 16444)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(64)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16442)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 2927 (class 0 OID 0)
-- Dependencies: 207
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 210 (class 1259 OID 16452)
-- Name: transportdocuments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transportdocuments (
    id integer NOT NULL,
    documenttype character varying(32) NOT NULL,
    series integer,
    number integer NOT NULL,
    issuedby character varying(255) NOT NULL,
    issueddate date,
    expiredate date,
    transportid integer NOT NULL
);


ALTER TABLE public.transportdocuments OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16450)
-- Name: transportdocuments_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transportdocuments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transportdocuments_id_seq OWNER TO postgres;

--
-- TOC entry 2928 (class 0 OID 0)
-- Dependencies: 209
-- Name: transportdocuments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transportdocuments_id_seq OWNED BY public.transportdocuments.id;


--
-- TOC entry 212 (class 1259 OID 16460)
-- Name: transportmodel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transportmodel (
    id integer NOT NULL,
    name character varying(64) NOT NULL,
    producer character varying(32) NOT NULL,
    maxweight integer,
    length integer,
    width integer,
    height integer
);


ALTER TABLE public.transportmodel OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16458)
-- Name: transportmodel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transportmodel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transportmodel_id_seq OWNER TO postgres;

--
-- TOC entry 2929 (class 0 OID 0)
-- Dependencies: 211
-- Name: transportmodel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transportmodel_id_seq OWNED BY public.transportmodel.id;


--
-- TOC entry 214 (class 1259 OID 16468)
-- Name: transportorders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transportorders (
    id integer NOT NULL,
    ordernumber character varying(255) NOT NULL,
    orderdate date NOT NULL,
    transportpresentingdate date NOT NULL,
    transportid integer NOT NULL,
    loadingplace character varying(100) NOT NULL,
    unloadingplace character varying(100) NOT NULL,
    placemethod character varying(100) NOT NULL,
    responsibleemployeeid integer NOT NULL,
    telephonenumber integer NOT NULL,
    customeremployeeid integer NOT NULL,
    declinereason character varying,
    comments character varying
);


ALTER TABLE public.transportorders OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16466)
-- Name: transportorders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transportorders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transportorders_id_seq OWNER TO postgres;

--
-- TOC entry 2930 (class 0 OID 0)
-- Dependencies: 213
-- Name: transportorders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transportorders_id_seq OWNED BY public.transportorders.id;


--
-- TOC entry 216 (class 1259 OID 16479)
-- Name: transports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transports (
    id integer NOT NULL,
    number character varying(16) NOT NULL,
    vin character varying(16) NOT NULL,
    transportmodelid integer NOT NULL,
    producedyear integer NOT NULL,
    startupdate date NOT NULL,
    writeoffdate date,
    color character varying(32),
    engineid integer,
    enginepower integer
);


ALTER TABLE public.transports OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16477)
-- Name: transports_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transports_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transports_id_seq OWNER TO postgres;

--
-- TOC entry 2931 (class 0 OID 0)
-- Dependencies: 215
-- Name: transports_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transports_id_seq OWNED BY public.transports.id;


--
-- TOC entry 218 (class 1259 OID 16487)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    id integer NOT NULL,
    user_id integer NOT NULL,
    role_id integer NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16485)
-- Name: user_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_roles_id_seq OWNER TO postgres;

--
-- TOC entry 2932 (class 0 OID 0)
-- Dependencies: 217
-- Name: user_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_roles_id_seq OWNED BY public.user_roles.id;


--
-- TOC entry 220 (class 1259 OID 16495)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(32) NOT NULL,
    password character varying NOT NULL,
    employeeid integer,
    enabled boolean NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16493)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2933 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2743 (class 2604 OID 16404)
-- Name: employeedocuments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeedocuments ALTER COLUMN id SET DEFAULT nextval('public.employeedocuments_id_seq'::regclass);


--
-- TOC entry 2744 (class 2604 OID 16412)
-- Name: employees id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees ALTER COLUMN id SET DEFAULT nextval('public.employees_id_seq'::regclass);


--
-- TOC entry 2745 (class 2604 OID 16420)
-- Name: engine id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.engine ALTER COLUMN id SET DEFAULT nextval('public.engine_id_seq'::regclass);


--
-- TOC entry 2746 (class 2604 OID 16428)
-- Name: goods id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods ALTER COLUMN id SET DEFAULT nextval('public.goods_id_seq'::regclass);


--
-- TOC entry 2747 (class 2604 OID 16436)
-- Name: nomenclatures id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nomenclatures ALTER COLUMN id SET DEFAULT nextval('public.nomenclatures_id_seq'::regclass);


--
-- TOC entry 2748 (class 2604 OID 16447)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 2749 (class 2604 OID 16455)
-- Name: transportdocuments id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportdocuments ALTER COLUMN id SET DEFAULT nextval('public.transportdocuments_id_seq'::regclass);


--
-- TOC entry 2750 (class 2604 OID 16463)
-- Name: transportmodel id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportmodel ALTER COLUMN id SET DEFAULT nextval('public.transportmodel_id_seq'::regclass);


--
-- TOC entry 2751 (class 2604 OID 16471)
-- Name: transportorders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportorders ALTER COLUMN id SET DEFAULT nextval('public.transportorders_id_seq'::regclass);


--
-- TOC entry 2752 (class 2604 OID 16482)
-- Name: transports id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transports ALTER COLUMN id SET DEFAULT nextval('public.transports_id_seq'::regclass);


--
-- TOC entry 2753 (class 2604 OID 16490)
-- Name: user_roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles ALTER COLUMN id SET DEFAULT nextval('public.user_roles_id_seq'::regclass);


--
-- TOC entry 2754 (class 2604 OID 16498)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2756 (class 2606 OID 16398)
-- Name: authorities authorities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorities_pkey PRIMARY KEY (id);


--
-- TOC entry 2758 (class 2606 OID 16406)
-- Name: employeedocuments employeedocuments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeedocuments
    ADD CONSTRAINT employeedocuments_pkey PRIMARY KEY (id);


--
-- TOC entry 2760 (class 2606 OID 16414)
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (id);


--
-- TOC entry 2762 (class 2606 OID 16422)
-- Name: engine engine_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.engine
    ADD CONSTRAINT engine_pkey PRIMARY KEY (id);


--
-- TOC entry 2764 (class 2606 OID 16430)
-- Name: goods goods_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods
    ADD CONSTRAINT goods_pkey PRIMARY KEY (id);


--
-- TOC entry 2766 (class 2606 OID 16441)
-- Name: nomenclatures nomenclatures_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nomenclatures
    ADD CONSTRAINT nomenclatures_pkey PRIMARY KEY (id);


--
-- TOC entry 2768 (class 2606 OID 16449)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2770 (class 2606 OID 16457)
-- Name: transportdocuments transportdocuments_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportdocuments
    ADD CONSTRAINT transportdocuments_pkey PRIMARY KEY (id);


--
-- TOC entry 2772 (class 2606 OID 16465)
-- Name: transportmodel transportmodel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportmodel
    ADD CONSTRAINT transportmodel_pkey PRIMARY KEY (id);


--
-- TOC entry 2774 (class 2606 OID 16476)
-- Name: transportorders transportorders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportorders
    ADD CONSTRAINT transportorders_pkey PRIMARY KEY (id);


--
-- TOC entry 2776 (class 2606 OID 16484)
-- Name: transports transports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transports
    ADD CONSTRAINT transports_pkey PRIMARY KEY (id);


--
-- TOC entry 2778 (class 2606 OID 16492)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2780 (class 2606 OID 16503)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2781 (class 2606 OID 16505)
-- Name: employeedocuments employeedocuments_employeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employeedocuments
    ADD CONSTRAINT employeedocuments_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employees(id) ON DELETE CASCADE;


--
-- TOC entry 2782 (class 2606 OID 16510)
-- Name: employees employees_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);


--
-- TOC entry 2783 (class 2606 OID 16515)
-- Name: goods goods_nomenclatureid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods
    ADD CONSTRAINT goods_nomenclatureid_fkey FOREIGN KEY (nomenclatureid) REFERENCES public.nomenclatures(id);


--
-- TOC entry 2784 (class 2606 OID 16520)
-- Name: goods goods_transportorderid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goods
    ADD CONSTRAINT goods_transportorderid_fkey FOREIGN KEY (transportorderid) REFERENCES public.transportorders(id);


--
-- TOC entry 2785 (class 2606 OID 16540)
-- Name: transportdocuments transportdocuments_transportid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportdocuments
    ADD CONSTRAINT transportdocuments_transportid_fkey FOREIGN KEY (transportid) REFERENCES public.transports(id);


--
-- TOC entry 2786 (class 2606 OID 16545)
-- Name: transportorders transportorders_customeremployeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportorders
    ADD CONSTRAINT transportorders_customeremployeeid_fkey FOREIGN KEY (customeremployeeid) REFERENCES public.employees(id);


--
-- TOC entry 2787 (class 2606 OID 16550)
-- Name: transportorders transportorders_responsibleemployeeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportorders
    ADD CONSTRAINT transportorders_responsibleemployeeid_fkey FOREIGN KEY (responsibleemployeeid) REFERENCES public.employees(id);


--
-- TOC entry 2788 (class 2606 OID 16555)
-- Name: transportorders transportorders_transportid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transportorders
    ADD CONSTRAINT transportorders_transportid_fkey FOREIGN KEY (transportid) REFERENCES public.transports(id);


--
-- TOC entry 2789 (class 2606 OID 16560)
-- Name: transports transports_engineid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transports
    ADD CONSTRAINT transports_engineid_fkey FOREIGN KEY (engineid) REFERENCES public.engine(id);


--
-- TOC entry 2790 (class 2606 OID 16565)
-- Name: transports transports_transportmodelid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transports
    ADD CONSTRAINT transports_transportmodelid_fkey FOREIGN KEY (transportmodelid) REFERENCES public.transportmodel(id);


--
-- TOC entry 2791 (class 2606 OID 16570)
-- Name: user_roles user_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 2792 (class 2606 OID 16575)
-- Name: user_roles user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


-- Completed on 2020-04-07 13:22:11

--
-- PostgreSQL database dump complete
--

