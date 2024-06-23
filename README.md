Breve Descripción de los Procesos de Carga y Realización de Reportes

a. Carga de Datos:
  Para cargar los datos del CSV utilizamos Buffered Reader. Utilizamos un try catch para los distintos paths del dataset. 
  Luego va leyendo línea por línea, borrando las comillas dobles y separando las columnas teniendo en cuenta otros caracteres que puedan alterar dicha separación.
  Luego, dependiendo del reporte, extraemos las columnas necesarias para este. Por ejemplo, para el 3er reporte, extrae la fecha y los nombres de los artistas. Si la fecha está dentro del rango especificado, incrementa el contador del artista correspondiente en la tabla hash artists.
b. Realización de Reportes:
  •	1er reporte
  El método Top10Tree lee el CSV y filtra las canciones que coinciden con el país y la fecha dados en un rango de puesto del 1 al 10. Genera una lista con la canción, el artista y su puesto en el top que luego se almacena en un árbol binario de búsqueda.
  Luego, la función printTop10Songs imprime el top en orden descendente de puestos.
  •	2do reporte
  Este método lee el CSV y cuenta las apariciones de cada canción en la fecha dada. Luego utiliza un Hash para almacenar y contar las canciones. Finalmente, las inserta en un árbol binario de búsqueda e imprime, en orden descendente, las 5 canciones con mayor número de apariciones.
  •	3er reporte
  Lee el CSV y filtra las filas que coinciden con el rango de fechas dado. Separa los artistas en caso de que una canción tenga varios artistas y cuenta las apariciones de cada uno utilizando un hash. Luego, inserta los artistas en un árbol binario de búsqueda basándose en el numero de apariciones e imprime, en orden descendente, los 7 que más aparecen.
  •	4to reporte
  Lee el CSV y selecciona las filas que coinciden con la fecha dada. Separa los artistas en caso de que una canción tenga varios y cuenta las apariciones utilizando un hash. Finalmente, imprime el total de apariciones del artista en esa fecha.
  •	5to reporte
  Lee el CSV y filtra las filas que coinciden con el rango de fechas dado. Verifica que el tempo de cada canción este dentro del rango especificado y utiliza un hash para contar las canciones que cumplen con ambos criterios (contando solo una vez por canción). Finalmente, imprime el número total de canciones. 

Medición de Eficiencia de la Aplicación

a. Cantidad de memoria RAM consumida
Hicimos una clase MemoryMeasurement con la función getUsedMemory() donde utilizamos Runtime.getRunTime() para medir la memoria. También insertamos en nuestra clase Spotify una función measureMemoryUsage() que llama a la función getUsedMemory() antes y despues de cada proceso de carga y generación de reportes.
b. Tiempo de ejecución promedio
Hicimos una clase TimeMeasurement con la función getExecutionTime donde utilizamos System.currentTimeMIllis() para medir el tiempo de ejecución. También insertamos en nuestra clase Spotify una función measureExecutionTime() que llama a la función getExecutionTime () antes y despues de cada proceso.
Los resultados registrados fueron los siguientes:
  o	Memoria utilizada para Reporte 1: 3044360 bytes
  o	Tiempo de ejecución para Reporte 1: 4139 ms
  o	Memoria utilizada para Reporte 2: 83307048 bytes
  o	Tiempo de ejecución para Reporte 2: 4174 ms
  o	Memoria utilizada para Reporte 3: 88515256 bytes
  o	Tiempo de ejecución para Reporte 3: 3988 ms
  o	Memoria utilizada para Reporte 4: -107608296 bytes
  o	Tiempo de ejecución para Reporte 4: 3998 ms
  o	Memoria utilizada para Reporte 5: 34377480 bytes
  o	Tiempo de ejecución para Reporte 5: 4402 ms

	
