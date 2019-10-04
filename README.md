# 2019-ma-ma-group-07

Trabajo practico integrador

Nombre del trabajo practico:

     QueMePongo

Tema: 

Objetivo:

Grupo:
 
    7
    
Integrantes:

    1_ Nombre: Martin Alejandro Diaz Legajo: 153.353-8
    2_ Nombre: Gabriela              Legajo:
    3_ Nombre: Federico              Legajo:    
    4_-------------------------------------------------
    5_-------------------------------------------------

-----------------------------------------------------------------
    obviar faltas ortograficas
-----------------------------------------------------------------
Estatus:

Registro de desiciones:
   
Entrega 1:

    Decisiones de diseño:
        Guardaropa-) Lista de atuendos mostrados, nos permite de ver que atuendos ya fueron generados y no volver a repetirlo siempre.
        Atuendo -) Prendas que conforman un atuendo:

Entrega 2:

    Decisiones de diseño:
        Prenda-) disponibilidad:
                Opcion vigente: Elegimos usar un booleano en la disponibilidad de las prendas ya que es mas sencillo la validacion.
                Alternativa: Hacer un strategy crear una interfaz Estado que devuelva true, false dependiendo el estado.
                    Descartada por ser un auto para caminar hasta la esquina.
Entrega 3:


Entrega 4:

    Decisiones de DER:
        Por defecto realizamos las estrategia una tabla por una clase.
            -ya que presenta la comodidad de no estar inventando la polvora
            para persistir el modelo logico.
            -Los nombres de las tablas son en singular.
            -Las denotaciones de los ids se realizan con la siguiente nomenclatura
                    id_nombreDeLaTabla
                    Ej:  id_usuario
        En caso que el diagrama no siga las normativas, se especifica la decision
        tomada acontinuacion.
--------------------------------------------------------------------------------
    pedir recomendacion<-----------------------------------------------------
      1_ Problematica: NivelDeCalor se relaciona con muchas entidades por
         lo que no se puede agregar una columna id de cada entidad que la 
         utilize.
         Opciones: 
            a) Usar una unica columna id_asociado para que administre todos
            todos los ids de todas las entidades.
                Contras:
                    -Se pierde unicidad.
                    -
            b) Tenga unicamente su id y que el modelo se encarge de 
            enlazar los valores a las clases
                Contras:
                    -Puede generar mucho overhead.
         Decision:

------------------------------------------------------------------------------         
      2_ Problematica: La interfaz entidad posee 4 variantes que no 
         contienen muchos datos de forma propia. 
         Opciones:
            a)Realizar la estrategia tabla per class.
                Contras:
                    -Muchas tablas con un unico campo id.
            b)Realizar una unica tabla con los datos de todos los tipos
            de sensibilidades.
                Pros:
                    -Nos ahorramos el exceso de tablas con un unico 
                    campo id
                    -Mejoramos la performance.
                Contas:
                    -Campos vacios por los requerimientos de las distintas
                    sensibilidades
         Decision:
            Optamos por la alternativa B ya que nos facilita la administracion
            de la persistencia mejorando la performance.
------------------------------------------------------------------------------
      3_ Problematica: En el modelo logico, la clase fotografo se encuentra en
         varias clases simultaneamente como atributo.  
         Opciones:
            a)Crear una tabla fotografo.Agregar un campo a cada tabla con un
             id_fotografo. Crear una tabla fotos con el id_fotografo y id_foto.
            
            
            b)Crear una tabla fotografo con un id_asociado que segun la tabla
             referente su id_asociado sea primeras 3 letras abreviatura de la
             tabla referente siguiendo con el id de la tabla referente.
                Ej:
                
         Decision: 
------------------------------------------------------------------------------    
      4_ Problematica:
         Opciones:
            a)
            b)
         Decision: 
------------------------------------------------------------------------------    
      5_ Problematica:
         Opciones:
            a)
            b)
         Decision: 
------------------------------------------------------------------------------    
      6_ Problematica:
         Opciones:
            a)
            b)
         Decision: 
------------------------------------------------------------------------------    
      7_ Problematica:
         Opciones:
            a)
            b)
         Decision: 
------------------------------------------------------------------------------    
Entrega 5:



