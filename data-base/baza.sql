PGDMP         *                {           registration    15.1    15.1 "               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17154    registration    DATABASE        CREATE DATABASE registration WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Polish_Poland.1250';
    DROP DATABASE registration;
                postgres    false            �            1259    17218    hibernate_sequence    SEQUENCE     {   CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hibernate_sequence;
       public          postgres    false            �            1259    17165    roles    TABLE     S   CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    17164    roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    217                        0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    216            �            1259    17232    task    TABLE     �   CREATE TABLE public.task (
    task_id bigint NOT NULL,
    date date,
    description character varying(255),
    name character varying(255),
    owner_id bigint,
    creator_name character varying(255),
    is_completed boolean NOT NULL
);
    DROP TABLE public.task;
       public         heap    postgres    false            �            1259    17231    task_task_id_seq    SEQUENCE     y   CREATE SEQUENCE public.task_task_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.task_task_id_seq;
       public          postgres    false    221            !           0    0    task_task_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.task_task_id_seq OWNED BY public.task.task_id;
          public          postgres    false    220            �            1259    17156    users    TABLE     �   CREATE TABLE public.users (
    id integer NOT NULL,
    email character varying(255),
    password text,
    name character varying(255),
    username character varying(255)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    17155    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    215            "           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    214            �            1259    17174    users_roles    TABLE     `   CREATE TABLE public.users_roles (
    user_id integer NOT NULL,
    role_id integer NOT NULL
);
    DROP TABLE public.users_roles;
       public         heap    postgres    false            u           2604    17168    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            v           2604    17235    task task_id    DEFAULT     l   ALTER TABLE ONLY public.task ALTER COLUMN task_id SET DEFAULT nextval('public.task_task_id_seq'::regclass);
 ;   ALTER TABLE public.task ALTER COLUMN task_id DROP DEFAULT;
       public          postgres    false    220    221    221            t           2604    17159    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215                      0    17165    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          postgres    false    217   ?$                 0    17232    task 
   TABLE DATA           f   COPY public.task (task_id, date, description, name, owner_id, creator_name, is_completed) FROM stdin;
    public          postgres    false    221   p$                 0    17156    users 
   TABLE DATA           D   COPY public.users (id, email, password, name, username) FROM stdin;
    public          postgres    false    215   %                 0    17174    users_roles 
   TABLE DATA           7   COPY public.users_roles (user_id, role_id) FROM stdin;
    public          postgres    false    218   J&       #           0    0    hibernate_sequence    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hibernate_sequence', 62, true);
          public          postgres    false    219            $           0    0    roles_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.roles_id_seq', 38, true);
          public          postgres    false    216            %           0    0    task_task_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.task_task_id_seq', 30, true);
          public          postgres    false    220            &           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 69, true);
          public          postgres    false    214            |           2606    17172    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    217            �           2606    17239    task task_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (task_id);
 8   ALTER TABLE ONLY public.task DROP CONSTRAINT task_pkey;
       public            postgres    false    221            x           2606    17248 !   users uk6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ]   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 K   ALTER TABLE ONLY public.users DROP CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    215            z           2606    17163    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    215            ~           2606    17277    users_roles users_roles_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (role_id, user_id);
 F   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT users_roles_pkey;
       public            postgres    false    218    218            �           2606    17240     task fkphl46nwqwa5kw3dn00l4cj93w    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT fkphl46nwqwa5kw3dn00l4cj93w FOREIGN KEY (owner_id) REFERENCES public.users(id);
 J   ALTER TABLE ONLY public.task DROP CONSTRAINT fkphl46nwqwa5kw3dn00l4cj93w;
       public          postgres    false    221    215    3194            �           2606    17182 $   users_roles users_roles_role_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);
 N   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT users_roles_role_id_fkey;
       public          postgres    false    217    3196    218            �           2606    17177 $   users_roles users_roles_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT users_roles_user_id_fkey;
       public          postgres    false    3194    215    218               !   x�3���q�v�2��]|=��b���� t��         �   x�e�A� ����^ ��p	W]�1�&�Q���c�����̐)�R�,�����7~����53f��/A����M����l
�ZI}�]}��"�-Xj�2Ղ�.��Z~Υ<�b�4f1�B���I+         8  x�e�G��@ @�5��59�n �0#Y�)7�.�&(�x�)q�^�����ES�_MZ�D�5؎Iw4�SsMD�I(��'�I����������V�T`���Zǧ�ڸ `#Fz㖊��ڨ+����3�"_4���kHn8�<4��-��ĸ���8�X��VW\]f��T�s��l�\�C��F�c@+�`�ڿ��`��P�>LN���2��_]v�V��W}�� �����8ďM�DJ�ȂZ�ݝ��VY�U��`��eWWZ]n�!:�z#�n)?nvI��D �wr��S�&0�p*_�ݑa6�:�˽�+���?5�             x�3��4�23�4�23 �H��qqq C!     