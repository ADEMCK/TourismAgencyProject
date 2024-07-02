PGDMP      0                |            turizmacentesistemi    15.7    16.3 I    R           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            S           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            T           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            U           1262    16928    turizmacentesistemi    DATABASE     �   CREATE DATABASE turizmacentesistemi WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
 #   DROP DATABASE turizmacentesistemi;
                postgres    false            �            1259    16929 
   guest_info    TABLE     )  CREATE TABLE public.guest_info (
    guest_id integer NOT NULL,
    reservations_id integer NOT NULL,
    full_name character varying(255) NOT NULL,
    national_number character varying(11) NOT NULL,
    country character varying(255) NOT NULL,
    guest_class character varying(255) NOT NULL
);
    DROP TABLE public.guest_info;
       public         heap    postgres    false            �            1259    16934    guest_info_guest_id_seq    SEQUENCE     �   CREATE SEQUENCE public.guest_info_guest_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.guest_info_guest_id_seq;
       public          postgres    false    214            V           0    0    guest_info_guest_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.guest_info_guest_id_seq OWNED BY public.guest_info.guest_id;
          public          postgres    false    215            �            1259    16935    hotel_property    TABLE     �   CREATE TABLE public.hotel_property (
    property_id integer NOT NULL,
    property_names text[] NOT NULL,
    hotel_id integer
);
 "   DROP TABLE public.hotel_property;
       public         heap    postgres    false            �            1259    16940    hotel_property_property_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_property_property_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.hotel_property_property_id_seq;
       public          postgres    false    216            W           0    0    hotel_property_property_id_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.hotel_property_property_id_seq OWNED BY public.hotel_property.property_id;
          public          postgres    false    217            �            1259    16941    hotel_seasons    TABLE     �   CREATE TABLE public.hotel_seasons (
    season_id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    season_type character varying(20) NOT NULL
);
 !   DROP TABLE public.hotel_seasons;
       public         heap    postgres    false            �            1259    16944    hotel_seasons_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_seasons_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.hotel_seasons_season_id_seq;
       public          postgres    false    218            X           0    0    hotel_seasons_season_id_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.hotel_seasons_season_id_seq OWNED BY public.hotel_seasons.season_id;
          public          postgres    false    219            �            1259    16945    hotels    TABLE     �  CREATE TABLE public.hotels (
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
       public         heap    postgres    false            �            1259    16951    hotels_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotels_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.hotels_hotel_id_seq;
       public          postgres    false    220            Y           0    0    hotels_hotel_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.hotels_hotel_id_seq OWNED BY public.hotels.hotel_id;
          public          postgres    false    221            �            1259    16952    reservations    TABLE       CREATE TABLE public.reservations (
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
       public         heap    postgres    false            �            1259    16957    reservations_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reservations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.reservations_id_seq;
       public          postgres    false    222            Z           0    0    reservations_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.reservations_id_seq OWNED BY public.reservations.id;
          public          postgres    false    223            �            1259    16958    room    TABLE     1  CREATE TABLE public.room (
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
       public         heap    postgres    false            �            1259    16961    room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.room_id_seq;
       public          postgres    false    224            [           0    0    room_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;
          public          postgres    false    225            �            1259    16962    room_properties    TABLE     �   CREATE TABLE public.room_properties (
    property_id integer NOT NULL,
    property character varying(255) NOT NULL,
    room_id integer NOT NULL,
    area integer NOT NULL,
    adult_bed_num integer,
    child_bed_num integer
);
 #   DROP TABLE public.room_properties;
       public         heap    postgres    false            �            1259    16965    room_properties_property_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_properties_property_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.room_properties_property_id_seq;
       public          postgres    false    226            \           0    0    room_properties_property_id_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.room_properties_property_id_seq OWNED BY public.room_properties.property_id;
          public          postgres    false    227            �            1259    16966 
   type_hotel    TABLE     �   CREATE TABLE public.type_hotel (
    type_id integer NOT NULL,
    hotel_id integer NOT NULL,
    type_name character varying(255) NOT NULL
);
    DROP TABLE public.type_hotel;
       public         heap    postgres    false            �            1259    16969    type_hotel_type_id_seq    SEQUENCE     �   CREATE SEQUENCE public.type_hotel_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.type_hotel_type_id_seq;
       public          postgres    false    228            ]           0    0    type_hotel_type_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.type_hotel_type_id_seq OWNED BY public.type_hotel.type_id;
          public          postgres    false    229            �            1259    16970    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16975    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    230            �           2604    16976    guest_info guest_id    DEFAULT     z   ALTER TABLE ONLY public.guest_info ALTER COLUMN guest_id SET DEFAULT nextval('public.guest_info_guest_id_seq'::regclass);
 B   ALTER TABLE public.guest_info ALTER COLUMN guest_id DROP DEFAULT;
       public          postgres    false    215    214            �           2604    16977    hotel_property property_id    DEFAULT     �   ALTER TABLE ONLY public.hotel_property ALTER COLUMN property_id SET DEFAULT nextval('public.hotel_property_property_id_seq'::regclass);
 I   ALTER TABLE public.hotel_property ALTER COLUMN property_id DROP DEFAULT;
       public          postgres    false    217    216            �           2604    16978    hotel_seasons season_id    DEFAULT     �   ALTER TABLE ONLY public.hotel_seasons ALTER COLUMN season_id SET DEFAULT nextval('public.hotel_seasons_season_id_seq'::regclass);
 F   ALTER TABLE public.hotel_seasons ALTER COLUMN season_id DROP DEFAULT;
       public          postgres    false    219    218            �           2604    16979    hotels hotel_id    DEFAULT     r   ALTER TABLE ONLY public.hotels ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotels_hotel_id_seq'::regclass);
 >   ALTER TABLE public.hotels ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    221    220            �           2604    16980    reservations id    DEFAULT     r   ALTER TABLE ONLY public.reservations ALTER COLUMN id SET DEFAULT nextval('public.reservations_id_seq'::regclass);
 >   ALTER TABLE public.reservations ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    222            �           2604    16981    room id    DEFAULT     b   ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);
 6   ALTER TABLE public.room ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224            �           2604    16982    room_properties property_id    DEFAULT     �   ALTER TABLE ONLY public.room_properties ALTER COLUMN property_id SET DEFAULT nextval('public.room_properties_property_id_seq'::regclass);
 J   ALTER TABLE public.room_properties ALTER COLUMN property_id DROP DEFAULT;
       public          postgres    false    227    226            �           2604    16983    type_hotel type_id    DEFAULT     x   ALTER TABLE ONLY public.type_hotel ALTER COLUMN type_id SET DEFAULT nextval('public.type_hotel_type_id_seq'::regclass);
 A   ALTER TABLE public.type_hotel ALTER COLUMN type_id DROP DEFAULT;
       public          postgres    false    229    228            >          0    16929 
   guest_info 
   TABLE DATA           q   COPY public.guest_info (guest_id, reservations_id, full_name, national_number, country, guest_class) FROM stdin;
    public          postgres    false    214   Z       @          0    16935    hotel_property 
   TABLE DATA           O   COPY public.hotel_property (property_id, property_names, hotel_id) FROM stdin;
    public          postgres    false    216   [       B          0    16941    hotel_seasons 
   TABLE DATA           _   COPY public.hotel_seasons (season_id, hotel_id, start_date, end_date, season_type) FROM stdin;
    public          postgres    false    218   �[       D          0    16945    hotels 
   TABLE DATA           o   COPY public.hotels (hotel_id, hotel_name, city, district, full_address, email, phone_number, star) FROM stdin;
    public          postgres    false    220   D\       F          0    16952    reservations 
   TABLE DATA           �   COPY public.reservations (id, room_id, reser_fll_name, reser_phone, reser_email, reser_note, reser_check_in_date, reser_check_out_date, adult_numb, child_numb, total_price) FROM stdin;
    public          postgres    false    222   �^       H          0    16958    room 
   TABLE DATA           x   COPY public.room (id, room_type, stock, season_id, adult_price, child_price, type_id, hotel_id, room_price) FROM stdin;
    public          postgres    false    224   �_       J          0    16962    room_properties 
   TABLE DATA           m   COPY public.room_properties (property_id, property, room_id, area, adult_bed_num, child_bed_num) FROM stdin;
    public          postgres    false    226   e`       L          0    16966 
   type_hotel 
   TABLE DATA           B   COPY public.type_hotel (type_id, hotel_id, type_name) FROM stdin;
    public          postgres    false    228   a       N          0    16970    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    230   �a       ^           0    0    guest_info_guest_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.guest_info_guest_id_seq', 23, true);
          public          postgres    false    215            _           0    0    hotel_property_property_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.hotel_property_property_id_seq', 36, true);
          public          postgres    false    217            `           0    0    hotel_seasons_season_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.hotel_seasons_season_id_seq', 68, true);
          public          postgres    false    219            a           0    0    hotels_hotel_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.hotels_hotel_id_seq', 45, true);
          public          postgres    false    221            b           0    0    reservations_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.reservations_id_seq', 18, true);
          public          postgres    false    223            c           0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 26, true);
          public          postgres    false    225            d           0    0    room_properties_property_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.room_properties_property_id_seq', 24, true);
          public          postgres    false    227            e           0    0    type_hotel_type_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.type_hotel_type_id_seq', 308, true);
          public          postgres    false    229            f           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 24, true);
          public          postgres    false    231            �           2606    16985    guest_info guest_info_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.guest_info
    ADD CONSTRAINT guest_info_pkey PRIMARY KEY (guest_id);
 D   ALTER TABLE ONLY public.guest_info DROP CONSTRAINT guest_info_pkey;
       public            postgres    false    214            �           2606    16987 "   hotel_property hotel_property_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.hotel_property
    ADD CONSTRAINT hotel_property_pkey PRIMARY KEY (property_id);
 L   ALTER TABLE ONLY public.hotel_property DROP CONSTRAINT hotel_property_pkey;
       public            postgres    false    216            �           2606    16989     hotel_seasons hotel_seasons_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.hotel_seasons
    ADD CONSTRAINT hotel_seasons_pkey PRIMARY KEY (season_id);
 J   ALTER TABLE ONLY public.hotel_seasons DROP CONSTRAINT hotel_seasons_pkey;
       public            postgres    false    218            �           2606    16991    hotels hotels_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.hotels
    ADD CONSTRAINT hotels_pkey PRIMARY KEY (hotel_id);
 <   ALTER TABLE ONLY public.hotels DROP CONSTRAINT hotels_pkey;
       public            postgres    false    220            �           2606    16993    reservations reservations_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_pkey;
       public            postgres    false    222            �           2606    16995    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    224            �           2606    16997 $   room_properties room_properties_pkey 
   CONSTRAINT     k   ALTER TABLE ONLY public.room_properties
    ADD CONSTRAINT room_properties_pkey PRIMARY KEY (property_id);
 N   ALTER TABLE ONLY public.room_properties DROP CONSTRAINT room_properties_pkey;
       public            postgres    false    226            �           2606    16999    type_hotel type_hotel_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.type_hotel
    ADD CONSTRAINT type_hotel_pkey PRIMARY KEY (type_id);
 D   ALTER TABLE ONLY public.type_hotel DROP CONSTRAINT type_hotel_pkey;
       public            postgres    false    228            �           2606    17001    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    230            �           2606    17002 '   hotel_seasons fk_hotel_seasons_hotel_id    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_seasons
    ADD CONSTRAINT fk_hotel_seasons_hotel_id FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 Q   ALTER TABLE ONLY public.hotel_seasons DROP CONSTRAINT fk_hotel_seasons_hotel_id;
       public          postgres    false    3229    218    220            �           2606    17007 *   guest_info guest_info_reservations_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.guest_info
    ADD CONSTRAINT guest_info_reservations_id_fkey FOREIGN KEY (reservations_id) REFERENCES public.reservations(id) ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.guest_info DROP CONSTRAINT guest_info_reservations_id_fkey;
       public          postgres    false    214    3231    222            �           2606    17012 +   hotel_property hotel_property_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.hotel_property
    ADD CONSTRAINT hotel_property_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 U   ALTER TABLE ONLY public.hotel_property DROP CONSTRAINT hotel_property_hotel_id_fkey;
       public          postgres    false    3229    216    220            �           2606    17017 &   reservations reservations_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reservations
    ADD CONSTRAINT reservations_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id) ON DELETE CASCADE;
 P   ALTER TABLE ONLY public.reservations DROP CONSTRAINT reservations_room_id_fkey;
       public          postgres    false    3233    224    222            �           2606    17022    room room_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.room DROP CONSTRAINT room_hotel_id_fkey;
       public          postgres    false    224    3229    220            �           2606    17027 ,   room_properties room_properties_room_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room_properties
    ADD CONSTRAINT room_properties_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id) ON DELETE CASCADE;
 V   ALTER TABLE ONLY public.room_properties DROP CONSTRAINT room_properties_room_id_fkey;
       public          postgres    false    224    3233    226            �           2606    17032    room room_season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.hotel_seasons(season_id);
 B   ALTER TABLE ONLY public.room DROP CONSTRAINT room_season_id_fkey;
       public          postgres    false    218    3227    224            �           2606    17037 #   type_hotel type_hotel_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.type_hotel
    ADD CONSTRAINT type_hotel_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotels(hotel_id) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.type_hotel DROP CONSTRAINT type_hotel_hotel_id_fkey;
       public          postgres    false    228    220    3229            >     x�m��n� ���S�����m2E޲T�d"�`.��<H���w��^=ڡU�����a��=�K���2�K�8^?ǳ�T�b�"ѵ�z�x}���Nw	VI�y2������>�/�)9�\��O�m�PB<;��i���7c��}k٣�Gۧ%ת��,�J��;��2�KRL.wR��ڍ����m��yQJɑ����I������6q�?�!�9¨��{?�^l����HT�U*m���C�e_i���      @   �   x�3�Vr+JMUH,���KWҁp�3�2������\��B@~~��_���������Z��
R�Y��Z\����W�Z02�7W���UN-*�LNU��4�26�Т� G"-36�2���m�\�0ˠ���T�eB7�pQ�؍����� 1��      B   s   x�]�1� ����ő����!K���j�_P0�����'�P"MeE��Y����y�C���ʂ1G��{-1�ISĕW�(�q�6f�ӕ|'ɟ�տm�b"���K�3�,+      D   1  x�e��n�@��맘S/EX��;R%���j/��ͦ �]��N��[��\�{o���L�(��v�����'�F�Ү���M��
��J�v6k]o�e�L2+�����MP	���h���m�s(?l��ҵ�`��M�<���Q�go��6�x����3�x��Ҳԕ��#0�e�PC	���M�
[v�fA�\j������Ʊ(ӆ$h8��N=�Ӡ��t��y��K�?(�y�A�ʃ��y��\#|G����;QjS�'�/.T�r����h�]Y�#�1A)��Pm.mQ��Z�BS�Z�����'�9�[��~VF�E'�bW;�B��?����_k'��W�*��V+��T+�Q4�7yj��6LQ�_���+���L���hLV�|σ�����ԉ�i�}`��4:#��)l�	�H�\��PYc�1��
3Y�ba�	�M�E��?�_����b[��LN�#�	;�:���Z��X���%b����f�%�)�l�z��@�н3�������S*�ٲ��8�M}��C�p��!hLb|������B�      F   #  x����N�0���SxFĺ�عx�RacA�b�A�S���xޣ;[�`8��uA�p����Ο��
x���'��{�o��Z�R����b�t����������E��s,sF6@x,J]!�"��G�%N��6��_��w��D�|t����@�h�?�c��B�=��~�K���_��m�4��dE��|P��7������eQ)ݷ�U��6m��4.�rd��:)z�݅�_��#BsJ+�xQ�=�1�j4�����d0]���-�˳��S�X��d��,�]�~�      H   �   x�m�M
�0FדSx����=�ݺD
j6���P�Iq����8��r��N�>��2"��Y BLt�8*����Bq��ذ��/�� ٹ���m����T���qLfm+S�V3���j̉ހ��<j��XL�F�xb,�m����H;�SJo��I�      J   �   x����
�0���S�	�I7�g;	�;�RG�Jm���vՉ���-����zm�ӌ�;�ƙꢂt��P@Á Ra�Sw]ލ��%�)��q�NL�f5��)�n���e�Hha��Ox��rѤ����W$҈�K�N��Y]���H�O����	�L����Đ9      L   �   x�m��� E��+�PY�D�+��;7��؈��a��V�ʒ3��x͉$?�n�����y�_�R��RLy�5�[�5�݌����ǀ� �[�!	��RB%�w�N,/+������2�ee`��<L �OhF��c|�c�Ԩ�1�?3��{�t�F޹W10(�y�.��mg7;N( �(qU�c��uUU��8�k      N   +   x�3�LL����4426�0�M9Ssr�+SS!�0W� i��     