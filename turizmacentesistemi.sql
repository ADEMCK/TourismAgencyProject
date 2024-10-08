PGDMP      /                |            turizmacentesistemi    15.7    16.3 I    R           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            S           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            T           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            U           1262    16820    turizmacentesistemi    DATABASE     �   CREATE DATABASE turizmacentesistemi WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
 #   DROP DATABASE turizmacentesistemi;
                postgres    false            �            1259    16821 
   guest_info    TABLE     )  CREATE TABLE public.guest_info (
    guest_id integer NOT NULL,
    reservations_id integer NOT NULL,
    full_name character varying(255) NOT NULL,
    national_number character varying(11) NOT NULL,
    country character varying(255) NOT NULL,
    guest_class character varying(255) NOT NULL
);
    DROP TABLE public.guest_info;
       public         heap    postgres    false            �            1259    16826    guest_info_guest_id_seq    SEQUENCE     �   CREATE SEQUENCE public.guest_info_guest_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.guest_info_guest_id_seq;
       public          postgres    false    214            V           0    0    guest_info_guest_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.guest_info_guest_id_seq OWNED BY public.guest_info.guest_id;
          public          postgres    false    215            �            1259    16827    hotel_property    TABLE     �   CREATE TABLE public.hotel_property (
    property_id integer NOT NULL,
    property_names text[] NOT NULL,
    hotel_id integer
);
 "   DROP TABLE public.hotel_property;
       public         heap    postgres    false            �            1259    16832    hotel_property_property_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_property_property_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.hotel_property_property_id_seq;
       public          postgres    false    216            W           0    0    hotel_property_property_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.hotel_property_property_id_seq OWNED BY public.hotel_property.property_id;
          public          postgres    false    217            �            1259    16833    hotel_seasons    TABLE     �   CREATE TABLE public.hotel_seasons (
    season_id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    season_type character varying(20) NOT NULL
);
 !   DROP TABLE public.hotel_seasons;
       public         heap    postgres    false            �            1259    16836    hotel_seasons_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_seasons_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.hotel_seasons_season_id_seq;
       public          postgres    false    218            X           0    0    hotel_seasons_season_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.hotel_seasons_season_id_seq OWNED BY public.hotel_seasons.season_id;
          public          postgres    false    219            �            1259    16837    hotels    TABLE     �  CREATE TABLE public.hotels (
    hotel_id integer NOT NULL,
    hotel_name character varying(255) NOT NULL,
    city character varying(100) NOT NULL,
    district character varying(100) NOT NULL,
    full_address text NOT NULL,
    email character varying(100),
    phone_number character varying(20),
    star integer,
    CONSTRAINT hotels_star_rating_check CHECK (((star >= 1) AND (star <= 5)))
);
    DROP TABLE public.hotels;
       public         heap    postgres    false            �            1259    16843    hotels_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotels_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.hotels_hotel_id_seq;
       public          postgres    false    220            Y           0    0    hotels_hotel_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.hotels_hotel_id_seq OWNED BY public.hotels.hotel_id;
          public          postgres    false    221            �            1259    16844    reservations    TABLE       CREATE TABLE public.reservations (
    id integer NOT NULL,
    room_id integer NOT NULL,
    reser_fll_name character varying(255) NOT NULL,
    reser_phone character varying(20) NOT NULL,
    reser_email character varying(255) NOT NULL,
    reser_note text,
    reser_check_in_date character varying(10) NOT NULL,
    reser_check_out_date character varying(10) NOT NULL,
    adult_numb character varying(10) NOT NULL,
    child_numb character varying(10) NOT NULL,
    total_price character varying(20) NOT NULL
);
     DROP TABLE public.reservations;
       public         heap    postgres    false            �            1259    16849    reservations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.reservations_id_seq;
       public          postgres    false    222            Z           0    0    reservations_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.reservations_id_seq OWNED BY public.reservations.id;
          public          postgres    false    223            �            1259    16850    room    TABLE     1  CREATE TABLE public.room (
    id integer NOT NULL,
    room_type character varying(255) NOT NULL,
    stock integer NOT NULL,
    season_id integer,
    adult_price integer NOT NULL,
    child_price integer NOT NULL,
    type_id integer NOT NULL,
    hotel_id integer NOT NULL,
    room_price integer
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16853    room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.room_id_seq;
       public          postgres    false    224            [           0    0    room_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;
          public          postgres    false    225            �            1259    16854    room_properties    TABLE     �   CREATE TABLE public.room_properties (
    property_id integer NOT NULL,
    property character varying(255) NOT NULL,
    room_id integer NOT NULL,
    area integer NOT NULL,
    adult_bed_num integer,
    child_bed_num integer
);
 #   DROP TABLE public.room_properties;
       public         heap    postgres    false            �            1259    16857    room_properties_property_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_properties_property_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.room_properties_property_id_seq;
       public          postgres    false    226            \           0    0    room_properties_property_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.room_properties_property_id_seq OWNED BY public.room_properties.property_id;
          public          postgres    false    227            �            1259    16858 
   type_hotel    TABLE     �   CREATE TABLE public.type_hotel (
    type_id integer NOT NULL,
    hotel_id integer NOT NULL,
    type_name character varying(255) NOT NULL
);
    DROP TABLE public.type_hotel;
       public         heap    postgres    false            �            1259    16861    type_hotel_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.type_hotel_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.type_hotel_type_id_seq;
       public          postgres    false    228            ]           0    0    type_hotel_type_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.type_hotel_type_id_seq OWNED BY public.type_hotel.type_id;
          public          postgres    false    229            �            1259    16862    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16867    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    230            �           2604    16868    guest_info guest_id    DEFAULT     z   ALTER TABLE ONLY public.guest_info ALTER COLUMN guest_id SET DEFAULT nextval('public.guest_info_guest_id_seq'::regclass);
 B   ALTER TABLE public.guest_info ALTER COLUMN guest_id DROP DEFAULT;
       public          postgres    false    215    214            �           2604    16869    hotel_property property_id    DEFAULT     �   ALTER TABLE ONLY public.hotel_property ALTER COLUMN property_id SET DEFAULT nextval('public.hotel_property_property_id_seq'::regclass);
 I   ALTER TABLE public.hotel_property ALTER COLUMN property_id DROP DEFAULT;
       public          postgres    false    217    216            �           2604    16870    hotel_seasons season_id    DEFAULT     �   ALTER TABLE ONLY public.hotel_seasons ALTER COLUMN season_id SET DEFAULT nextval('public.hotel_seasons_season_id_seq'::regclass);
 F   ALTER TABLE public.hotel_seasons ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    219    218            �           2604    16871    hotels hotel_id    DEFAULT     r   ALTER TABLE ONLY public.hotels ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotels_hotel_id_seq'::regclass);
 >   ALTER TABLE public.hotels ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    221    220            �           2604    16872    reservations id    DEFAULT     r   ALTER TABLE ONLY public.reservations ALTER COLUMN id SET DEFAULT nextval('public.reservations_id_seq'::regclass);
 >   ALTER TABLE public.reservations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222            �           2604    16873    room id    DEFAULT     b   ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);
 6   ALTER TABLE public.room ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224            �           2604    16874    room_properties property_id    DEFAULT     �   ALTER TABLE ONLY public.room_properties ALTER COLUMN property_id SET DEFAULT nextval('public.room_properties_property_id_seq'::regclass);
 J   ALTER TABLE public.room_properties ALTER COLUMN property_id DROP DEFAULT;
       public          postgres    false    227    226            �           2604    16875    type_hotel type_id    DEFAULT     x   ALTER TABLE ONLY public.type_hotel ALTER COLUMN type_id SET DEFAULT nextval('public.type_hotel_type_id_seq'::regclass);
 A   ALTER TABLE public.type_hotel ALTER COLUMN type_id DROP DEFAULT;
       public          postgres    false    229    228            >          0    16821 
   guest_info 
   TABLE DATA           q   COPY public.guest_info (guest_id, reservations_id, full_name, national_number, country, guest_class) FROM stdin;
    public          postgres    false    214   Z       @          0    16827    hotel_property 
   TABLE DATA           O   COPY public.hotel_property (property_id, property_names, hotel_id) FROM stdin;
    public          postgres    false    216   [       B          0    16833    hotel_seasons 
   TABLE DATA           _   COPY public.hotel_seasons (season_id, hotel_id, start_date, end_date, season_type) FROM stdin;
    public          postgres    false    218   �[       D          0    16837    hotels 
   TABLE DATA           o   COPY public.hotels (hotel_id, hotel_name, city, district, full_address, email, phone_number, star) FROM stdin;
    public          postgres    false    220   d\       F          0    16844    reservations 
   TABLE DATA           �   COPY public.reservations (id, room_id, reser_fll_name, reser_phone, reser_email, reser_note, reser_check_in_date, reser_check_out_date, adult_numb, child_numb, total_price) FROM stdin;
    public          postgres    false    222   �^       H          0    16850    room 
   TABLE DATA           x   COPY public.room (id, room_type, stock, season_id, adult_price, child_price, type_id, hotel_id, room_price) FROM stdin;
    public          postgres    false    224   �_       J          0    16854    room_properties 
   TABLE DATA           m   COPY public.room_properties (property_id, property, room_id, area, adult_bed_num, child_bed_num) FROM stdin;
    public          postgres    false    226   �`       L          0    16858 
   type_hotel 
   TABLE DATA           B   COPY public.type_hotel (type_id, hotel_id, type_name) FROM stdin;
    public          postgres    false    228   ~a       N          0    16862    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    230   }b       ^           0    0    guest_info_guest_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.guest_info_guest_id_seq', 32, true);
          public          postgres    false    215            _           0    0    hotel_property_property_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.hotel_property_property_id_seq', 38, true);
          public          postgres    false    217            `           0    0    hotel_seasons_season_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.hotel_seasons_season_id_seq', 76, true);
          public          postgres    false    219            a           0    0    hotels_hotel_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotels_hotel_id_seq', 47, true);
          public          postgres    false    221            b           0    0    reservations_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.reservations_id_seq', 23, true);
          public          postgres    false    223            c           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 34, true);
          public          postgres    false    225            d           0    0    room_properties_property_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.room_properties_property_id_seq', 32, true);
          public          postgres    false    227            e           0    0    type_hotel_type_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.type_hotel_type_id_seq', 316, true);
          public          postgres    false    229            f           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 24, true);
          public          postgres    false    231            �           2606    16877    guest_info guest_info_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.guest_info
    ADD CONSTRAINT guest_info_pkey PRIMARY KEY (guest_id);
 D   ALTER TABLE ONLY public.guest_info DROP CONSTRAINT guest_info_pkey;
       public            postgres    false    214            �           2606    16879 "   hotel_property hotel_property_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.hotel_property
    ADD CONSTRAINT hotel_property_pkey PRIMARY KEY (property_id);
 L   ALTER TABLE ONLY public.hotel_property DROP CONSTRAINT hotel_property_pkey;
       public            postgres    false    216            �           2606    16881     hotel_seasons hotel_seasons_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.hotel_seasons
    ADD CONSTRAINT hotel_seasons_pkey PRIMARY KEY (season_id);
 J   ALTER TABLE ONLY public.hotel_seasons DROP CONSTRAINT hotel_seasons_pkey;
       public            postgres    false    218            �           2606    16883    hotels hotels_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (hotel_id);
 <   ALTER TABLE ONLY public.hotels DROP CONSTRAINT hotels_pkey;
       public            postgres    false    220            �           2606    16885    reservations reservations_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_pkey;
       public            postgres    false    222            �           2606    16887    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    224            �           2606    16889 $   room_properties room_properties_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.room_properties
    ADD CONSTRAINT room_properties_pkey PRIMARY KEY (property_id);
 N   ALTER TABLE ONLY public.room_properties DROP CONSTRAINT room_properties_pkey;
       public            postgres    false    226            �           2606    16891    type_hotel type_hotel_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.type_hotel
    ADD CONSTRAINT type_hotel_pkey PRIMARY KEY (type_id);
 D   ALTER TABLE ONLY public.type_hotel DROP CONSTRAINT type_hotel_pkey;
       public            postgres    false    228            �           2606    16893    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    230            �           2606    16894 '   hotel_seasons fk_hotel_seasons_hotel_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_seasons
    ADD CONSTRAINT fk_hotel_seasons_hotel_id FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 Q   ALTER TABLE ONLY public.hotel_seasons DROP CONSTRAINT fk_hotel_seasons_hotel_id;
       public          postgres    false    3229    218    220            �           2606    16899 *   guest_info guest_info_reservations_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.guest_info
    ADD CONSTRAINT guest_info_reservations_id_fkey FOREIGN KEY (reservations_id) REFERENCES public.reservations(id) ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.guest_info DROP CONSTRAINT guest_info_reservations_id_fkey;
       public          postgres    false    214    3231    222            �           2606    16904 +   hotel_property hotel_property_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_property
    ADD CONSTRAINT hotel_property_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 U   ALTER TABLE ONLY public.hotel_property DROP CONSTRAINT hotel_property_hotel_id_fkey;
       public          postgres    false    3229    216    220            �           2606    16909 &   reservations reservations_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id) ON DELETE CASCADE;
 P   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_room_id_fkey;
       public          postgres    false    3233    224    222            �           2606    16914    room room_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.room DROP CONSTRAINT room_hotel_id_fkey;
       public          postgres    false    224    3229    220            �           2606    16919 ,   room_properties room_properties_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room_properties
    ADD CONSTRAINT room_properties_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id) ON DELETE CASCADE;
 V   ALTER TABLE ONLY public.room_properties DROP CONSTRAINT room_properties_room_id_fkey;
       public          postgres    false    224    3233    226            �           2606    16924    room room_season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.hotel_seasons(season_id);
 B   ALTER TABLE ONLY public.room DROP CONSTRAINT room_season_id_fkey;
       public          postgres    false    218    3227    224            �           2606    16929 #   type_hotel type_hotel_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.type_hotel
    ADD CONSTRAINT type_hotel_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.type_hotel DROP CONSTRAINT type_hotel_hotel_id_fkey;
       public          postgres    false    228    220    3229            >   �   x�}��N�0F盧� �k'qF�t�ԡ����*Q~�LR�}Fx��lMߋ�.@�m��� "���?Mc���|6�P�6Kaw�uubp�����.[��������|'p"�����;]?8~�}��\!i��ʪ)"�Spb������'V�D�~R2�p8r|}�e�ʹJsc�~ʀn���]�HE"=���V�g.�_�I��������-��y�v}�_�I�R����S~�      @   �   x�36�Vr+JMUH,���KWҁp�3�2������\��B@~~��_���������Z�����ԑY��Z\����W�ZTfd�o������ZT����T�il�elI?�,��a�AMG7��˄n2�2��.��q�in�\d��m���!� *��b���� ��      B   �   x�U�1�0Eg|�D`���!K�ҡj�_[��n_��a�6`���7�i�6�q�>�w�
<����� ����Np��.�Ȓ:���,��fB���,��-=NK���8d9�aM͐�֚��#�	W��2C����SJ?��V�      D   :  x�e��n�0�g�)n�RA�DY�;%v�&H\I�.Y�S�H���/з�Ǭ����{����M �#x��ytB�U"�Zi������]V(F���)[�z+9<c�a\8<��6E�`�*���h���ε�a�M�׬�Y���)�.�(
��:�^E=��� ���'h:4&w��,U�|KLUY�����w|��Y��-�D���.\(�����&	+yC6t<�S��Ԩ�5�E�w���R���|��Р�0��}��k\#|E����[V*]��+�e�b���7��\�#�1E!,�E5�0E���aj�
u�k]&.���*ͭ�Uaaq��ʬjщz؝�X�C�,j0����Z1Y�e����2k���W��z�0��y������ �)���P6�cq޷��=xg��E�����J:�Zx%9��p��B`I����#�qcQ�;�q�xÅv�����z�m�@i�Y�m=�Nw]�g�L(�(��&�D1A|��N@~�e�b�5�fJ��V\I�٨\mr��H�a�b��Vx���	�Gv���Cop�{�D<�d�>���6��}���8'�H      F   �   x�U�=n�0Fg�:@-��~�-�5@�.�����V���=�wKV)m�\>��{$��*X�m�qOS<}-�"TV��$���xZk��B�y��P�u)PH �D�׈w�����g�5�����nJckKҒa�ӄ�7y �/���/��8K��1��lٺ!9���������_��tgr��,�yb���Z@%`9�۱�?TI�ki�L+��ٍaD%��w�1��Յ��EQ� Kb�      H   �   x�m��
�@E�ٯ��<����ҴV�H@� ���I�dC��g�K�e�>n͹�'x�X?Jd?|@Gm����3HT�1A���[阢��0�n�ߋ̈́��IZ 2HjHUQ!Y�%�����'C	����L<cS<��8��լ��g�Q�v`�$[�Fv$UN�����L�J�-�+�!O�dø�i�9�Rث����m�җ�s�p*n�      J   �   x����
�0Eד���L�k� �e6�D��Z��M�J�T�����Mjթ��5�^���>�u�
�8 ��K� �>F��خ����f�����ۋjo.XNC]����s��;B@>�#��\<�(ˏ{n��欞����JG� M���K��i"E
�)"�
p�pޤ��O;�"���G��b�HLnc�o�w      L   �   x�m�An!E�p
NPaLXf�F�Q��A��J@
3U{��UŔ��`�}����>F��q-�w�J+��_2�����[�#vX	�B*sN�����?���ۆ0���V�6[mc�*l����iF	ZJE�a%C���M���K1�V;�s���s��(��Oa���%v�0P�1L§I���.�,@3���G)�~��n�o3�� �i�J!U��39A�7]d�����9 %��      N   +   x�3�LL����4426�0�M9Ssr�+SS!�0W� i��     