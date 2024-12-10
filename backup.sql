PGDMP  "    ,        
        |         
   FRENESIEDB    17.2    17.2 3    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16388 
   FRENESIEDB    DATABASE        CREATE DATABASE "FRENESIEDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_France.1252';
    DROP DATABASE "FRENESIEDB";
                     postgres    false            �            1259    24731    artist_image_urls    TABLE     y   CREATE TABLE public.artist_image_urls (
    artist_id integer NOT NULL,
    image_url character varying(255) NOT NULL
);
 %   DROP TABLE public.artist_image_urls;
       public         heap r       postgres    false            �            1259    16440    artists    TABLE     �   CREATE TABLE public.artists (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(500),
    genre character varying(255) NOT NULL
);
    DROP TABLE public.artists;
       public         heap r       postgres    false            �            1259    16439    artists_id_seq    SEQUENCE     �   ALTER TABLE public.artists ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.artists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public               postgres    false    218            �            1259    24723 	   collectif    TABLE     �   CREATE TABLE public.collectif (
    id integer NOT NULL,
    nom character varying(255) NOT NULL,
    description text NOT NULL
);
    DROP TABLE public.collectif;
       public         heap r       postgres    false            �            1259    24722    collectif_id_seq    SEQUENCE     �   CREATE SEQUENCE public.collectif_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.collectif_id_seq;
       public               postgres    false    228            �           0    0    collectif_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.collectif_id_seq OWNED BY public.collectif.id;
          public               postgres    false    227            �            1259    16455    event_artist    TABLE     d   CREATE TABLE public.event_artist (
    event_id integer NOT NULL,
    artist_id integer NOT NULL
);
     DROP TABLE public.event_artist;
       public         heap r       postgres    false            �            1259    24641    event_image_urls    TABLE     w   CREATE TABLE public.event_image_urls (
    event_id integer NOT NULL,
    image_url character varying(255) NOT NULL
);
 $   DROP TABLE public.event_image_urls;
       public         heap r       postgres    false            �            1259    24632    events    TABLE     �  CREATE TABLE public.events (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    location character varying(255) NOT NULL,
    description character varying(100) NOT NULL,
    start_time timestamp without time zone NOT NULL,
    end_time timestamp without time zone NOT NULL,
    status character varying(20),
    CONSTRAINT chk_start_time_future CHECK ((start_time > now()))
);
    DROP TABLE public.events;
       public         heap r       postgres    false            �            1259    24631    events_id_seq    SEQUENCE     v   CREATE SEQUENCE public.events_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.events_id_seq;
       public               postgres    false    221            �           0    0    events_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.events_id_seq OWNED BY public.events.id;
          public               postgres    false    220            �            1259    24714    sets    TABLE     �   CREATE TABLE public.sets (
    id integer NOT NULL,
    artiste_id integer NOT NULL,
    collectif_id integer NOT NULL,
    date_set date,
    title character varying(255),
    url_sound_cloud character varying(255)
);
    DROP TABLE public.sets;
       public         heap r       postgres    false            �            1259    24713    sets_id_seq    SEQUENCE     �   CREATE SEQUENCE public.sets_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.sets_id_seq;
       public               postgres    false    226            �           0    0    sets_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.sets_id_seq OWNED BY public.sets.id;
          public               postgres    false    225            �            1259    24658    users    TABLE     �  CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    role character varying(50) NOT NULL,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'USER'::character varying, 'ARTIST'::character varying])::text[])))
);
    DROP TABLE public.users;
       public         heap r       postgres    false            �            1259    24657    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public               postgres    false    224            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public               postgres    false    223            &           2604    24726    collectif id    DEFAULT     l   ALTER TABLE ONLY public.collectif ALTER COLUMN id SET DEFAULT nextval('public.collectif_id_seq'::regclass);
 ;   ALTER TABLE public.collectif ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    227    228    228            #           2604    24635 	   events id    DEFAULT     f   ALTER TABLE ONLY public.events ALTER COLUMN id SET DEFAULT nextval('public.events_id_seq'::regclass);
 8   ALTER TABLE public.events ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    220    221    221            %           2604    24717    sets id    DEFAULT     b   ALTER TABLE ONLY public.sets ALTER COLUMN id SET DEFAULT nextval('public.sets_id_seq'::regclass);
 6   ALTER TABLE public.sets ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    226    225    226            $           2604    24661    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    223    224    224            �          0    24731    artist_image_urls 
   TABLE DATA           A   COPY public.artist_image_urls (artist_id, image_url) FROM stdin;
    public               postgres    false    229   �:       �          0    16440    artists 
   TABLE DATA           ?   COPY public.artists (id, name, description, genre) FROM stdin;
    public               postgres    false    218   �:       �          0    24723 	   collectif 
   TABLE DATA           9   COPY public.collectif (id, nom, description) FROM stdin;
    public               postgres    false    228   �;       �          0    16455    event_artist 
   TABLE DATA           ;   COPY public.event_artist (event_id, artist_id) FROM stdin;
    public               postgres    false    219   �;       �          0    24641    event_image_urls 
   TABLE DATA           ?   COPY public.event_image_urls (event_id, image_url) FROM stdin;
    public               postgres    false    222   �;       �          0    24632    events 
   TABLE DATA           `   COPY public.events (id, title, location, description, start_time, end_time, status) FROM stdin;
    public               postgres    false    221   �;       �          0    24714    sets 
   TABLE DATA           ^   COPY public.sets (id, artiste_id, collectif_id, date_set, title, url_sound_cloud) FROM stdin;
    public               postgres    false    226   a<       �          0    24658    users 
   TABLE DATA           D   COPY public.users (id, username, password, email, role) FROM stdin;
    public               postgres    false    224   ~<       �           0    0    artists_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.artists_id_seq', 4, true);
          public               postgres    false    217            �           0    0    collectif_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.collectif_id_seq', 1, false);
          public               postgres    false    227            �           0    0    events_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.events_id_seq', 2, true);
          public               postgres    false    220            �           0    0    sets_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.sets_id_seq', 1, false);
          public               postgres    false    225            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 4, true);
          public               postgres    false    223            <           2606    24735 (   artist_image_urls artist_image_urls_pkey 
   CONSTRAINT     x   ALTER TABLE ONLY public.artist_image_urls
    ADD CONSTRAINT artist_image_urls_pkey PRIMARY KEY (artist_id, image_url);
 R   ALTER TABLE ONLY public.artist_image_urls DROP CONSTRAINT artist_image_urls_pkey;
       public                 postgres    false    229    229            *           2606    24673    artists artists_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.artists DROP CONSTRAINT artists_pkey;
       public                 postgres    false    218            :           2606    24730    collectif collectif_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.collectif
    ADD CONSTRAINT collectif_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.collectif DROP CONSTRAINT collectif_pkey;
       public                 postgres    false    228            ,           2606    24692    event_artist event_artist_pkey 
   CONSTRAINT     m   ALTER TABLE ONLY public.event_artist
    ADD CONSTRAINT event_artist_pkey PRIMARY KEY (event_id, artist_id);
 H   ALTER TABLE ONLY public.event_artist DROP CONSTRAINT event_artist_pkey;
       public                 postgres    false    219    219            0           2606    24703 &   event_image_urls event_image_urls_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public.event_image_urls
    ADD CONSTRAINT event_image_urls_pkey PRIMARY KEY (event_id, image_url);
 P   ALTER TABLE ONLY public.event_image_urls DROP CONSTRAINT event_image_urls_pkey;
       public                 postgres    false    222    222            .           2606    24640    events events_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.events
    ADD CONSTRAINT events_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.events DROP CONSTRAINT events_pkey;
       public                 postgres    false    221            8           2606    24721    sets sets_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.sets
    ADD CONSTRAINT sets_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.sets DROP CONSTRAINT sets_pkey;
       public                 postgres    false    226            2           2606    24670    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public                 postgres    false    224            4           2606    24666    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public                 postgres    false    224            6           2606    24668    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public                 postgres    false    224            ?           2606    24736 2   artist_image_urls artist_image_urls_artist_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.artist_image_urls
    ADD CONSTRAINT artist_image_urls_artist_id_fkey FOREIGN KEY (artist_id) REFERENCES public.artists(id);
 \   ALTER TABLE ONLY public.artist_image_urls DROP CONSTRAINT artist_image_urls_artist_id_fkey;
       public               postgres    false    4650    229    218            =           2606    24693 (   event_artist event_artist_artist_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.event_artist
    ADD CONSTRAINT event_artist_artist_id_fkey FOREIGN KEY (artist_id) REFERENCES public.artists(id) ON DELETE CASCADE;
 R   ALTER TABLE ONLY public.event_artist DROP CONSTRAINT event_artist_artist_id_fkey;
       public               postgres    false    4650    219    218            >           2606    24704 /   event_image_urls event_image_urls_event_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.event_image_urls
    ADD CONSTRAINT event_image_urls_event_id_fkey FOREIGN KEY (event_id) REFERENCES public.events(id);
 Y   ALTER TABLE ONLY public.event_image_urls DROP CONSTRAINT event_image_urls_event_id_fkey;
       public               postgres    false    221    4654    222            �      x������ � �      �   �   x�}�;
�@@��S�EP����F��b�fAweg�X{�Tb�^f��j��MG��9Ef5��E�0��5��l �
���y/=����`�'o�
�
��@N?g9��)�tHm�~f��+�8E�Ú�ºd��j@!>����%�.�*f�������'޹�"�[��=%I���>n��>��S����Ӕ�޶��/,�f      �      x������ � �      �      x������ � �      �      x������ � �      �   j   x�3�tL�I�J�)J�-���SHI-V�M,MO-�t/�/.NU(��,:�2U!�,5Y�=�(93Q!8��,1=�����T��X��R����� ��b�
P�?�=... ��      �      x������ � �      �   �   x�5��r�0 @�u���B�qIxS1��nAd�������n��y��@Ӆ��k�Zy�r�oW��=�#��6uJЀD��a��ي�Ζ�EXS�Q�4�����M�*�̢EH��������*��?�7C��e�vBs�ҵ��7��&�qs�Aߖ�moe��e04jn&3C�%���4[��7���A��q�M���6nN?	�3O7�=���{#U�     