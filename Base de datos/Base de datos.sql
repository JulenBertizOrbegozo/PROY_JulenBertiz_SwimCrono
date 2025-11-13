drop database if exists proy_swimcrono;
create database proy_swimcrono;
use proy_swimcrono;

-- CLUB
drop table if exists club;
create table club(
	IdClub int primary key auto_increment,
    Correo Varchar(50) not null unique,
    Telefono int not null unique,
    Nombre Varchar(50) not null,
    Provincia Varchar(50) not null,
    Contraseina Varchar(50) not null,
    -- F_Creacion date,
    Logo  Varchar(100),
    Activo boolean default true,
	Web Varchar(100) not null,
    NumNadadores int default 0
);

insert into club (Correo, Telefono, Nombre, Provincia, Contraseina, Logo, Web, NumNadadores) values ('natacion@anaitasuna.com', 948254900, 'S.C.D.R. Anaitasuna', 'Navarra', 'HASHED_PASSWORD', NULL, 'https://anaitasuna.com', NULL),('info@cdamaya.com', 948246594, 'Club Ciudad Deportiva Amaya', 'Navarra', 'HASHED_PASSWORD', NULL, 'http://www.cdamaya.com', NULL),('clubnatacionburlada@gmail.com', 626429981, 'Club Natación Burlada', 'Navarra', 'HASHED_PASSWORD', NULL, 'https://www.burlada.es', NULL),('oficina@sociedad-lagunak.com', 948181233, 'C.D. Lagunak (natación)', 'Navarra', 'HASHED_PASSWORD', NULL, 'https://www.lagunak.org', NULL),('jjjoselu@hotmail.com', 605860675, 'C.D. Mendialdea (natación)', 'Navarra', 'HASHED_PASSWORD', NULL, 'https://sites.google.com/view/mkeberriozar17', NULL),('recepcion@cdmclaracampoamor.com', 948825909, 'C.D.M. Clara Campoamor (Tudela)', 'Navarra', 'HASHED_PASSWORD', NULL, 'https://cdmclaracampoamor.com', NULL),('info@club-tenis.com', 948233700, 'Club Tenis Pamplona (sección natación)', 'Navarra', 'HASHED_PASSWORD', NULL, 'https://www.club-tenis.com', NULL),('info@clubnatacionpamplona.com', 948223706, 'Club Natación Pamplona', 'Navarra', 'HASHED_PASSWORD', NULL, 'http://www.clubnatacionpamplona.com', NULL),('deportes-kirolak@aranguren.es', 948246850, 'Servicio Deportivo / Natación Aranguren', 'Navarra', 'HASHED_PASSWORD', NULL, 'https://www.aranguren.es', NULL);

-- USUARIO
drop table if exists usuario;
create table usuario(
	IdUsuario int primary key auto_increment,
    Correo Varchar(50) not null unique,
    Telefono int not null unique,
    Nombre  Varchar(50) not null,
    Apellido1 Varchar(50) not null,
    Apellido2 Varchar(50),
    Contraseina Varchar(50) not null,
    F_Nacimineto date not null,
    Rol Enum("Nadador", "Entrenador", "Admin") not null default "Nadador",
    Genero Enum("Masc", "Fem") not null,
    Nivel Enum("Principiante", "Medio", "Avanzado", "Profesional") not null default "Principiante",
    FotoPerfil Varchar(100),
    FKIdClub int,
    constraint Usuarios_FKIdClub foreign key (FKIdClub) references club(IdClub)
);

insert into usuario (Correo, Telefono, Nombre, Apellido1, Apellido2, Contraseina, F_Nacimineto, Rol, Genero, Nivel, FotoPerfil, FKIdClub) values ('iker.martinez@example.com', 600111222, 'Iker', 'Martínez', 'Goñi', 'HASHED_PASSWORD', '2005-07-14', 'Nadador', 'Masc', 'Avanzado', 'fotos/iker.png', 1), ('maria.lopez@example.com', 600333444, 'María', 'López', 'Echeverría', 'HASHED_PASSWORD', '1989-03-22', 'Entrenador', 'Fem', 'Profesional', 'fotos/maria.png', 2), ('admin@appnatacion.es', 600555666, 'Julen', 'Bertiz', 'Orbegozo', 'HASHED_PASSWORD', '2006-06-15', 'Admin', 'Masc', 'Profesional', 'fotos/admin.png', NULL);


-- PRUEBA
drop table if exists prueba;
create table prueba(
	IdPrueba int primary key auto_increment,
    Distancia int not null,
    Estilo Enum("Mariposa", "Espalda", "Braza", "Libre", "Estilos") not null,
    Piscina Enum("Piscina25", "Piscina50") not null,
    Genero Enum("Masc", "Fem") not null
);

insert into prueba (Distancia, Estilo, Piscina, Genero) values (50, "Libre", "Piscina50", "Masc"),(50, "Libre", "Piscina50", "Fem"),(50, "Libre", "Piscina25", "Masc"),(50, "Libre", "Piscina25", "Fem"),(100, "Libre", "Piscina50", "Masc"),(100, "Libre", "Piscina50", "Fem"),(100, "Libre", "Piscina25", "Masc"),(100, "Libre", "Piscina25", "Fem"),(200, "Libre", "Piscina50", "Masc"),(200, "Libre", "Piscina50", "Fem"),(200, "Libre", "Piscina25", "Masc"),(200, "Libre", "Piscina25", "Fem"),(400, "Libre", "Piscina50", "Masc"),(400, "Libre", "Piscina50", "Fem"),(400, "Libre", "Piscina25", "Masc"),(400, "Libre", "Piscina25", "Fem"),(800, "Libre", "Piscina50", "Masc"),(800, "Libre", "Piscina50", "Fem"),(800, "Libre", "Piscina25", "Masc"),(800, "Libre", "Piscina25", "Fem"),(1500, "Libre", "Piscina50", "Masc"),(1500, "Libre", "Piscina50", "Fem"),(1500, "Libre", "Piscina25", "Masc"),(1500, "Libre", "Piscina25", "Fem"),(50, "Mariposa", "Piscina50", "Masc"),(50, "Mariposa", "Piscina50", "Fem"),(50, "Mariposa", "Piscina25", "Masc"),(50, "Mariposa", "Piscina25", "Fem"),(100, "Mariposa", "Piscina50", "Masc"),(100, "Mariposa", "Piscina50", "Fem"),(100, "Mariposa", "Piscina25", "Masc"),(100, "Mariposa", "Piscina25", "Fem"),(200, "Mariposa", "Piscina50", "Masc"),(200, "Mariposa", "Piscina50", "Fem"),(200, "Mariposa", "Piscina25", "Masc"),(200, "Mariposa", "Piscina25", "Fem"),(50, "Espalda", "Piscina50", "Masc"),(50, "Espalda", "Piscina50", "Fem"),(50, "Espalda", "Piscina25", "Masc"),(50, "Espalda", "Piscina25", "Fem"),(100, "Espalda", "Piscina50", "Masc"),(100, "Espalda", "Piscina50", "Fem"),(100, "Espalda", "Piscina25", "Masc"),(100, "Espalda", "Piscina25", "Fem"),(200, "Espalda", "Piscina50", "Masc"),(200, "Espalda", "Piscina50", "Fem"),(200, "Espalda", "Piscina25", "Masc"),(200, "Espalda", "Piscina25", "Fem"),(50, "Braza", "Piscina50", "Masc"),(50, "Braza", "Piscina50", "Fem"),(50, "Braza", "Piscina25", "Masc"),(50, "Braza", "Piscina25", "Fem"),(100, "Braza", "Piscina50", "Masc"),(100, "Braza", "Piscina50", "Fem"),(100, "Braza", "Piscina25", "Masc"),(100, "Braza", "Piscina25", "Fem"),(200, "Braza", "Piscina50", "Masc"),(200, "Braza", "Piscina50", "Fem"),(200, "Braza", "Piscina25", "Masc"),(200, "Braza", "Piscina25", "Fem"),(100, "Estilos", "Piscina25", "Masc"),(100, "Estilos", "Piscina25", "Fem"),(200, "Estilos", "Piscina50", "Masc"),(200, "Estilos", "Piscina50", "Fem"),(200, "Estilos", "Piscina25", "Masc"),(200, "Estilos", "Piscina25", "Fem"),(400, "Estilos", "Piscina50", "Masc"),(400, "Estilos", "Piscina50", "Fem"),(400, "Estilos", "Piscina25", "Masc"),(400, "Estilos", "Piscina25", "Fem");

-- WORLD RECORD
drop table if exists worldRecord;
create table worldRecord(
	IdWorldRecord int primary key auto_increment,
    NombreNadador varchar(100) not null,
    Fecha date not null,
    Tiempo time not null,
    FKIdPrueba int not null,
    constraint WorldRecord_FKIdPrueba foreign key (FKIdPrueba) references prueba(IdPrueba)
);

-- Usuario_Prueba
drop table if exists usuario_prueba;
create table usuario_prueba(
	IdUsuario_prueba int primary key auto_increment,
    Tiempo time not null,
    FKIdUsuario int not null,
    FKIdPrueba int not null,
    constraint Usuario_prueba_FKIdClub foreign key (FKIdUsuario) references usuario(IdUsuario),
    constraint Usuario_prueba_FKIdPrueba foreign key (FKIdPrueba) references prueba(IdPrueba)
);

-- COMPETICION
drop table if exists competicion;
create table competicion(
	IdCompeticion int primary key auto_increment,
    Nombre varchar(100) not null,
    Fecha date not null,
    Ubicacion Varchar(100),
    TipoPiscina Enum("Piscina25", "Piscina50") not null
);

-- COMPETICION_CLUB
drop table if exists competicion_club;
create table competicion_club(
	IdCompeticion_Club int primary key auto_increment,
    FKIdCompeticion int not null,
    FKIdClub int not null,
    constraint CompeticionClub_FKIdCompeticion foreign key (FKIdCompeticion) references competicion(IdCompeticion),
    constraint CompeticionClub_FKIdClub foreign key (FKIdClub) references club(IdClub),
    constraint CompeticionClub_UNQ unique(FKIdCompeticion, FKIdClub)
);

-- COMPETICION_USUARIO
drop table if exists competicion_usuario;
create table competicion_usuario(
	FKIdCompeticion int,
    FKIdUsuario int,
    constraint CompeticionUsuario_PK primary key(FKIdCompeticion, FKIdUsuario),
    constraint CompeticionUsuario_FKIdCompeticion foreign key (FKIdCompeticion) references competicion(IdCompeticion),
    constraint CompeticionUsuario_FKIdUsuario foreign key (FKIdUsuario) references usuario(IdUsuario)
);

-- COMPETICION_PRUEBA
drop table if exists commpeticion_prueba;
create table commpeticion_prueba(
	IdCompeticion_Prueba int primary key auto_increment,
    FKIdCompeticion int Not null,
    FKIdPrueba int not null,
    Constraint CommpeticionPrueba_UNQ unique (FKIdCompeticion, FKIdPrueba),
    Constraint CommpeticionPrueba_FKIdCompeticion foreign key (FKIdCompeticion) references competicion(IdCompeticion),
    Constraint CommpeticionPrueba_FKIdPrueba foreign key (FKIdPrueba) references prueba(IdPrueba)
);
select * from prueba;
select * from usuario;
select * from club