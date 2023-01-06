create database DBProyectoLP1;

use DBProyectoLP1;

create table Usuario(
idUsu		char(8) primary key,
passUsu		varchar(50),
nomUsu		varchar(50),
apeUsu		varchar(50)
);

create table Solicitud(
codSoli 	char(8)primary key,
fechaSoli 	date,
destinoSoli varchar(50),
temaSoli 	varchar(50),
firmaSoli 	varchar(50)
);

create table Designacion(
codDesig 		char(8)primary key,
fechaDesig 		date,
destinoDesig 	varchar(50),
temaDesig 		varchar(50),
firmaDesig 		varchar(50)
);
select * from Designacion;
create table Productos(
codProd		char(8) primary key,
descripProd	varchar(50)
);

create table ProductosEntrada(
codProdEnt		char(8) primary key,
fechaProdEnt	date,
codProd			char(8) references Productos(codProd),
descripProdEnt	varchar(50),
cantidadProdEnt	int
);

create table ProductosSalida(
codProdSal		char(8) primary key,
fechaProdSal	date,
codProd			char(8) references Productos(codProd),
descripProdSal	varchar(50),
cantidadProdSal	int
);

create table ActaInventario(
codActInv		char(8) primary key,
codProd			char(8), foreign key(codProd) references Productos(codProd),
descripInv	varchar(50),
stockInv	int,
codDiferencia int,foreign key(codDiferencia) references tipoDiferencia(codDiferencia)
);

create table tipoDiferencia(
codDiferencia int auto_increment primary key,
tipoDif varchar(70)
);
insert into tipoDiferencia values(null , 'Bienes sobrantes');
insert into tipoDiferencia values(null , 'Bienes faltantes por robo');
insert into tipoDiferencia values(null , 'Bienes faltantes por negligencia');
insert into tipoDiferencia values(null , 'Bienes faltantes por merma');



insert into Usuario values('ADMIN' , 'ADMIN' , 'Juan' , 'Lopez');
insert into Usuario values('SUB' , 'SUB' , 'Jesus' , 'Quispe');

select Productos.codProd,Productos.descripProd,ProductosEntrada.cantidadProdEnt,ProductosSalida.cantidadProdSal,ProductosEntrada.cantidadProdEnt-ProductosSalida.cantidadProdSal
from Productos join ProductosEntrada 
on Productos.codProd = ProductosEntrada.codProd join ProductosSalida
on ProductosSalida.codProd=Productos.codProd;

select P.codProd,P.descripProd,E.cantidadProdEnt-S.cantidadProdSal 
					from Productos P join ProductosEntrada E
					on P.codProd = E.codProd join ProductosSalida S
					on S.codProd=P.codProd where P.codProd like 'P%';

                     
select a.codActInv,p.codProd,p.descripProd, e.cantidadProdEnt-s.cantidadProdSal, d.tipoDif
					from ActaInventario a join Productos p
					 on a.codProd = p.codProd join ProductosSalida s
					on s.codProd=p.codProd join ProductosEntrada e
					on e.codProd=p.codProd join tipoDiferencia d
					on d.codDiferencia=a.codDiferencia
                    where d.tipoDif='Bienes sobrantes';
                    

/*PROCEDIMIENTO ALMACENADO
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reporte_actaInventario_por_tipoDif`(nom varchar(60))
BEGIN
	select a.codActInv,p.codProd,p.descripProd, e.cantidadProdEnt-s.cantidadProdSal, d.tipoDif
	from ActaInventario a join Productos p
	on a.codProd = p.codProd join ProductosSalida s
	on s.codProd=p.codProd join ProductosEntrada e
	on e.codProd=p.codProd join tipoDiferencia d
	on d.codDiferencia=a.codDiferencia
	where d.tipoDif=nom;
END
*/
