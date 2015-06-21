--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: roles_permissions; Type: TABLE; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE TABLE roles_permissions (
    role_name text NOT NULL,
    permission text NOT NULL,
    id integer NOT NULL
);


ALTER TABLE roles_permissions OWNER TO bulldozer;

--
-- Name: roles_permissions_idCol_seq; Type: SEQUENCE; Schema: public; Owner: bulldozer
--

CREATE SEQUENCE "roles_permissions_idCol_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "roles_permissions_idCol_seq" OWNER TO bulldozer;

--
-- Name: roles_permissions_idCol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bulldozer
--

ALTER SEQUENCE "roles_permissions_idCol_seq" OWNED BY roles_permissions.id;


--
-- Name: server_properties; Type: TABLE; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE TABLE server_properties (
    property_key character varying(250) NOT NULL,
    property_value character varying(255) NOT NULL
);


ALTER TABLE server_properties OWNER TO bulldozer;

--
-- Name: users; Type: TABLE; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE TABLE users (
    username text NOT NULL,
    password text NOT NULL,
    id integer NOT NULL,
    email text NOT NULL
);


ALTER TABLE users OWNER TO bulldozer;

--
-- Name: users_roles; Type: TABLE; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE TABLE users_roles (
    username text NOT NULL,
    role_name text NOT NULL,
    id integer NOT NULL
);


ALTER TABLE users_roles OWNER TO bulldozer;

--
-- Name: users_roles_idCol_seq; Type: SEQUENCE; Schema: public; Owner: bulldozer
--

CREATE SEQUENCE "users_roles_idCol_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "users_roles_idCol_seq" OWNER TO bulldozer;

--
-- Name: users_roles_idCol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bulldozer
--

ALTER SEQUENCE "users_roles_idCol_seq" OWNED BY users_roles.id;


--
-- Name: users_usersCol_seq; Type: SEQUENCE; Schema: public; Owner: bulldozer
--

CREATE SEQUENCE "users_usersCol_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "users_usersCol_seq" OWNER TO bulldozer;

--
-- Name: users_usersCol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bulldozer
--

ALTER SEQUENCE "users_usersCol_seq" OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: bulldozer
--

ALTER TABLE ONLY roles_permissions ALTER COLUMN id SET DEFAULT nextval('"roles_permissions_idCol_seq"'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: bulldozer
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('"users_usersCol_seq"'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: bulldozer
--

ALTER TABLE ONLY users_roles ALTER COLUMN id SET DEFAULT nextval('"users_roles_idCol_seq"'::regclass);


--
-- Data for Name: roles_permissions; Type: TABLE DATA; Schema: public; Owner: bulldozer
--



--
-- Name: roles_permissions_idCol_seq; Type: SEQUENCE SET; Schema: public; Owner: bulldozer
--

SELECT pg_catalog.setval('"roles_permissions_idCol_seq"', 1, false);


--
-- Data for Name: server_properties; Type: TABLE DATA; Schema: public; Owner: bulldozer
--

INSERT INTO server_properties VALUES ('de.refactorco.Bulldozer.net.web.WebServer.host', '127.0.0.1');
INSERT INTO server_properties VALUES ('de.refactorco.Bulldozer.net.web.WebServer.port', '8080');
INSERT INTO server_properties VALUES ('de.refactorco.Bulldozer.net.web.WebServer.maxRequestsPerSec', '30');


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: bulldozer
--

INSERT INTO users VALUES ('test', '$shiro1$SHA-256$500000$lXaJB+sajeFMnSjg4loSCg==$WGj9VeaHAmCKwDRogWCJD0oUwE15VVUO7eQsPnW3J1k=', 1, 'test@test.com');


--
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: bulldozer
--



--
-- Name: users_roles_idCol_seq; Type: SEQUENCE SET; Schema: public; Owner: bulldozer
--

SELECT pg_catalog.setval('"users_roles_idCol_seq"', 1, false);


--
-- Name: users_usersCol_seq; Type: SEQUENCE SET; Schema: public; Owner: bulldozer
--

SELECT pg_catalog.setval('"users_usersCol_seq"', 1, true);


--
-- Name: roles_permissions_unique_id; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY roles_permissions
    ADD CONSTRAINT roles_permissions_unique_id PRIMARY KEY (id);


--
-- Name: server_properties_pkey; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY server_properties
    ADD CONSTRAINT server_properties_pkey PRIMARY KEY (property_key);


--
-- Name: unique_email; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT unique_email UNIQUE (email);


--
-- Name: unique_id; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT unique_id PRIMARY KEY (id);


--
-- Name: unique_name; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT unique_name UNIQUE (username);


--
-- Name: unique_usersCol; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT "unique_usersCol" UNIQUE (id);


--
-- Name: users_email_key; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users_rolesUnique; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users_roles
    ADD CONSTRAINT "users_rolesUnique" PRIMARY KEY (id);


--
-- Name: users_usersCol_key; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT "users_usersCol_key" UNIQUE (id);


--
-- Name: index_email; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX index_email ON users USING btree (email);


--
-- Name: index_name; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX index_name ON users USING btree (username);


--
-- Name: index_role_name; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX index_role_name ON users_roles USING btree (role_name);


--
-- Name: index_username; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX index_username ON users_roles USING btree (username);


--
-- Name: index_usersCol; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX "index_usersCol" ON users USING btree (id);


--
-- Name: roles_permissions_index_id; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX roles_permissions_index_id ON roles_permissions USING btree (id);


--
-- Name: roles_permissions_index_role_name; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX roles_permissions_index_role_name ON roles_permissions USING btree (role_name);

--
-- PostgreSQL database dump complete
--

