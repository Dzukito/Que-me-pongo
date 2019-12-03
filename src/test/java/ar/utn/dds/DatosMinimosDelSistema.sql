Use queMePongo;
/* Membresia */
	/* Free */
/*
INSERT INTO queMePongo.membrecia(id_membrecia,tipoMembrecia, descripcion)
        VALUES ( 1,'Gratuito','Gratuito');
*/
	/* Premium */
/*
INSERT INTO queMePongo.membrecia(id_membrecia,tipoMembrecia, descripcion)
        VALUES ( 2,'Premium','Premium');
*/
/* Fin Membrecia */

/* Categorias */
	/* 1 Superior */
/*
insert into queMePongo.categoria(id_categoria,categoria,nivelCalorMaximo,nivelCalorMinimo)
	values (1,'Superior', 5, 1);
*/
	/* 2 Inferior */
/*
insert into queMePongo.categoria(id_categoria,categoria,nivelCalorMaximo,nivelCalorMinimo)
	values (2,'Inferior', 3, 1);
 */
	/* 3 Calzado */
 /*
insert into queMePongo.categoria(id_categoria,categoria,nivelCalorMaximo,nivelCalorMinimo)
	values (3,'Calzado', 3, 1);
 */
	/* 4 Accesorios */
/*
insert into queMePongo.categoria(id_categoria,categoria,nivelCalorMaximo,nivelCalorMinimo)
	values (4,'Accesorios', 10, 0);
*/ 
	/* 5 Cuello */
/*    
insert into queMePongo.categoria(id_categoria,categoria,nivelCalorMaximo,nivelCalorMinimo)
	values (5,'Cuello', 1, 0);
*/
    /* 6 Manos */
/*
insert into queMePongo.categoria(id_categoria,categoria,nivelCalorMaximo,nivelCalorMinimo)
	values (6,'Manos', 1, 0);
*/
    /* 7 Cabeza */
/*
insert into queMePongo.categoria(id_categoria,categoria,nivelCalorMaximo,nivelCalorMinimo)
	values (7,'Cabeza', 1, 0);
*/
/* Fin Categoria*/
/* Material */
	/* Algodón */ 
/*
insert into queMePongo.material(id_material,nombre)
	values (1,'Algodón');
*/
	/* seda */ 
/*
insert into queMePongo.material(id_material,nombre)
	values (2,'Seda');
 */
	/* Poliester */ 
/*
insert into queMePongo.material(id_material,nombre)
	values (3,'Poliester');
 */
	/* Lycra */ 
/*
insert into queMePongo.material(id_material,nombre)
	values (4,'Lycra');
 */
	/* Cuero */ 
/*
insert into queMePongo.material(id_material,nombre)
	values (5,'Cuero');
*/    
    /* Nylon */ 
/*
insert into queMePongo.material(id_material,nombre)
	values (6,'Nylon');
*/
    /* Jean */ 
/*
insert into queMePongo.material(id_material,nombre)
	values (7,'Jean');
*/
/* Fin Materiales */
/* Tipos de Prenda*/
/* Remera cuello rendondo manga corta*/
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (1);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (1,'Remera cuello rendondo manga corta',1,1);
/* Fin Remera cuello rendondo manga corta*/
/* Remera cuello rendondo manga larga*/
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (2);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (2,'Remera cuello rendondo manga larga',1,2);
/* Fin Remera cuello rendondo manga larga*/
/* Remera escote V manga corta */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (3);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (3,'Remera escote V manga corta' ,1,3);
/* Fin Remera escote V manga corta*/
/* Remera cuello rendondo manga larga*/
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (4);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (4,'Remera escote V manga larga',1,4);
/* Fin Remera escote V manga larga*/

/* Sueter */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (5);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (5,'Sueter',1,5);
/* Fin Sueter */

/* Campera */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (6);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (6,'Campera',1,6);
/* Fin Campera */

/* Pantalon Largo */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (7);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (7,'Pantalon Largo',2,7);
/* Fin Pantalon Largo */

/* Pantalon Corto */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (8);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (8,'Pantalon Corto',2,8);
/* Fin Pantalon Corto */

/* Bermuda */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (9);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (9,'Bermuda',2,9);
/* Fin Bermuda */

/* Pollera */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (10);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (10,'Pollera',2,10);
/* Fin Pollera */

/* Calza */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (11);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (11,'Calza',2,11);
/* Fin Calza */

/* Buzo */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (12);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (12,'Buzo',1,12);
/* Fin Buzo */

/* Musculoza */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (13);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (13,'Musculoza',1,13);
/* Fin Musculoza */

/* Zapatillas */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (14);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (14,'Zapatillas',3,14);
/* Fin Zapatillas */

/* Zapatos */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (15);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (15,'Zapatos',3,15);
/* Fin Zapatos */

/* Sandalias */
		/* Fotografo */
insert into queMePongo.fotografo(id_fotografo)
		values (16);
Insert into queMePongo.tipoPrenda(id_tipoPrenda,tipo,categoria,id_fotografo)
        values (16,'Sandalias',3,16);
/* Fin Sandalias */
/* Fin Tipos de Prenda*/
/* Materiales */
	/* 1 Remera cuello redondo manga corta */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (1,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (1,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (1,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (1,"Lycra");
	/* Fin 1 */
	/* 2 Remera cuello redondo manga larga */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (2,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (2,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (2,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (2,"Lycra");
	/* Fin 2 */
	/* 3 Remera escote V manga corta */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (3,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (3,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (3,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (3,"Lycra");
	/* Fin 3 */
	/* 4 Remera escote V manga larga */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (4,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (4,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (4,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (4,"Lycra");
	/* Fin 4 */
	/* 5 Sueter */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (5,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (5,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (5,"Poliester");
	/* Fin 5 */
	/* 6 Campera */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (6,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (6,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (6,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (6,"Cuero");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (6,"Nylon");
	/* Fin 6 */
	/* 7 Pantalon largo */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (7,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (7,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (7,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (7,"Nylon");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (7,"Jean");
	/* Fin 7 */
    
	/* 8 Pantalon corto */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (8,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (8,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (8,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (8,"Nylon");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (8,"Jean");
	/* Fin 8 */
	/* 9  Bermuda */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (9,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (9,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (9,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (9,"Nylon");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (9,"Jean");
	/* Fin 9 */
	/* 10  Pollera*/
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (10,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (10,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (10,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (10,"Nylon");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (10,"Jean");
	/* Fin 10 */
	/* 11 Calza */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (11,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (11,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (11,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (11,"Nylon");
	/* Fin 11 */
	/* 12 Buzo */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (12,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (12,"Seda");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (12,"Poliester");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (12,"Nylon");
	/* Fin 12 */
    /* 13 Musculoza */
Insert into queMePongo.materiales(id_tipoPrenda,material)
	values (13,"Algodón");
Insert into queMePongo.materiales(id_tipoPrenda,material)
	values (13,"Lycra");
    /* Fin 13*/
	/* 14  Zapatillas */
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (14,"Cuero");
Insert into queMePongo.materiales(id_tipoPrenda,material)
        values (14,"Nylon");
	/* Fin 14 */    
	/* 15 Zapatos */
Insert into queMePongo.materiales(id_tipoPrenda,material)
	values (15,"Cuero");
    /* Fin 15 */
	/* 16 Sandalias */
Insert into queMePongo.materiales(id_tipoPrenda,material)
	values (16,"Cuero");
	/* Fin 16 */
/* Fin Materiales*/
/* Superponibles */
	/* 1 Remera cuello redondo manga corta */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (1,5);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (1,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (1,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (1,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (1,9);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (1,10);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (1,11);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (1,12);
    /* Fin 1 */
	/* 2 Remera cuello redondo manga Larga */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (2,5);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (2,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (2,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (2,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (2,9);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (2,10);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (2,11);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (2,12);
    /* Fin 2 */
	/* 3 Remera escote V manga corta */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (3,5);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (3,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (3,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (3,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (3,9);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (3,10);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (3,11);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (3,12);
    /* Fin 3 */    
	/* 4 Remera escote V manga larga */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (4,5);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (4,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (4,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (4,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (4,9);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (4,10);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (4,11);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (4,12);
    /* Fin 4 */
	/* 5 Sueter */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (5,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (5,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (5,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (5,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (5,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (5,6);
    /* Fin 5 */
	/* 6 Campera */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,9);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,10);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (6,11);
    /* Fin 6 */
	/* 7 Pantalon largo */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (7,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (7,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (7,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (7,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (7,5);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (7,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (7,13);
    /* Fin 7 */
	/* 8 Pantalon corto */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (8,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (8,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (8,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (8,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (8,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (8,13);
    /* Fin 8 */
    
	/* 9 Bermuda */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (9,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (9,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (9,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (9,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (9,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (9,13);
    /* Fin 9 */
    
	/* 10 Pollera */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (10,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (10,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (10,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (10,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (10,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (10,13);
    /* Fin 10 */    
	/* 11 Calza */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (11,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (11,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (11,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (11,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (11,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (11,13);
    /* Fin 11 */
	/* 12 Buzo */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,9);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,10);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (12,13);
    /* Fin 12 */
    /* 13 Musculoza */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (13,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (13,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (13,10);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (13,11);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (13,12);
    /* Fin 13 */
    
	/* 14 Zapatillas */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,1);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,2);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,3);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,4);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,5);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,6);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,9);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,10);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,11);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,12);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,13);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,14);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,15);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (14,16);
    /* Fin 14 */
	/* 15 Zapatos */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (15,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (15,10);
    /* Fin 15*/
	/* 16 Sandalias */
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (16,7);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (16,8);
Insert into queMePongo.superponible(id_tipoPrenda,id_superponible)
	values (16,10);
    /* Fin 16*/
/* Fin Superponibles */
/* Estilo */
Insert into queMePongo.estilo(id_estilo,estilo)
	values (1,'Elegante');
Insert into queMePongo.estilo(id_estilo,estilo)
	values (2,'ElegantSport');
Insert into queMePongo.estilo(id_estilo,estilo)
	values (3,'Deportivo');
Insert into queMePongo.estilo(id_estilo,estilo)
	values (4,'Entrecasa');    
Insert into queMePongo.estilo(id_estilo,estilo)
	values (5,'Normal');
Insert into queMePongo.estilo(id_estilo,estilo)
	values (6,'Playero');
Insert into queMePongo.estilo(id_estilo,estilo)
	values (7,'Invernal');
/* Fin Estilo */