/* Usuarios */
/*
insert INTO queMePongo.usuario(nombre,apellido,mail, password, sexo, telefono, nombreUsuario, id_membrecia)
		values ('Alejandro','Roco','','123456','masculino',0,'aroco',1);
insert INTO queMePongo.usuario(nombre,apellido,mail, password, sexo, telefono, nombreUsuario, id_membrecia)
		values ('Julieta','Azul','','123456','femenino',0,'jazul',2);
*/
/* Fin usuarios */
/* Guardaropas */
insert INTO queMePongo.guardaropa(id_guardaropa,nombre)
		values (1,"Aroco");
insert INTO queMePongo.guardaropa(id_guardaropa,nombre)
		values (2,"jazul");
/* Fin Guardaropas */
/* Map_Usuario_guardaropa */
insert INTO queMePongo.map_usuario_guardaropa(id_usuario,id_guardaropa)
		values (1,1);
insert INTO queMePongo.map_usuario_guardaropa(id_usuario,id_guardaropa)
		values (2,2);
/* Fin Map_Usuario_guardaropa */
/* Prendas */
insert into queMePongo.fotografo(id_fotografo)
		values (1);
insert INTO queMePongo.prenda(id_prenda,disponibilidad,material,niveldecalor,nombrePrenda,sexo,id_fotografo,id_tipoPrenda,id_guardaropa,id_atuendo)
		values (1,1,1,5,"Remera cuello redondo manga corta","Masculino",1,1,1,);
/* Fin Prendas */