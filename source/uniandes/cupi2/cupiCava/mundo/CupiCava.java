/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_cupiCava
 * Autor: Equipo Cupi2 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.cupiCava.mundo;

import java.util.ArrayList;

/**
 * Clase que representa la Cupi-Cava. <br>
 * <b>inv: </b> <br>
 * TODO Parte1 PuntoC: Declare la invariante de la clase.
 */
public class CupiCava
{
    // -------------------------------------------------------------
    // Atributos
    // -------------------------------------------------------------

    /**
     * Lista de vinos en la cava.
     */
    private ArrayList<Vino> vinos;

    // -------------------------------------------------------------
    // Método Constructor
    // -------------------------------------------------------------

    /**
     * Construye una nueva cava sin vinos. <br>
     * <b>post:</b> La lista de vinos ha sido inicializada.
     */
    public CupiCava( )
    {
        vinos = new ArrayList<Vino>( );
    }

    // -------------------------------------------------------------
    // Métodos
    // -------------------------------------------------------------

    /**
     * Retorna la lista de vinos.
     * @return Lista de vinos.
     */
    public ArrayList<Vino> darVinos( )
    {
        return vinos;
    }

    /**
     * Busca un vino con el nombre dado por parámetro. <br>
     * <b>pre:</b> La lista de vinos está inicializada.
     * @param pNombre Nombre del vino. pNombre != null && pNombre != ""
     * @return Vino con el nombre dado, null en caso de no encontrarlo.
     */
    public Vino buscarVino( String pNombre )
    {
        Vino buscado = null;
        boolean encontre = false;

        for( int i = 0; i < vinos.size( ) && !encontre; i++ )
        {
            Vino vinoActual = ( Vino )vinos.get( i );
            if( vinoActual.darNombre( ).equalsIgnoreCase( pNombre ) )
            {
                buscado = vinoActual;
                encontre = true;
            }
        }
        return buscado;
    }

    /**
     * Busca un vino utilizando una búsqueda binaria. <br>
     * <b>pre: </b> La lista de vinos está inicializada y se encuentra ordenada por nombre.
     * @param pNombre Nombre del vino que se va a buscar. pNombre != null && pNombre != "".
     * @return Vino con el nombre dado, null en caso de no encontrarlo.
     */
    public Vino buscarBinarioPorNombre( String pNombre )
    {
    	int inicio = 0;
        int fin = vinos.size() - 1;
        Vino encontrado = null;
        
        while (inicio <= fin && encontrado == null) {
            int medio = (inicio + fin) / 2;
            Vino vinoMedio = vinos.get(medio);
            int comparacion = vinoMedio.darNombre().compareToIgnoreCase(pNombre);
            
            if (comparacion == 0) {
                encontrado = vinoMedio;
            } else if (comparacion < 0) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return encontrado;
    }

    /**
     * Busca el vino más dulce (con mayor contenido en azúcar) de la cava. <br>
     * <b>pre:</b> La lista de vinos está inicializada.
     * @return Vino más dulce de la cava. Si la cava no tiene vinos se retorna null. Si existen varios vinos con el contenido en azúcar más alto, se retorna el primer vino
     *         encontrado.
     */
    public Vino buscarVinoMasDulce( )
    {
    	if (vinos.isEmpty()) {
            return null;
        }
        
        Vino masDulce = vinos.get(0);
        
        for (int i = 1; i < vinos.size(); i++) {
            Vino vinoActual = vinos.get(i);
            if (vinoActual.darContenidoAzucar() > masDulce.darContenidoAzucar()) {
                masDulce = vinoActual;
            }
        }
        
        return masDulce;
    }

    /**
     * Busca el vino más seco (con menor contenido en azúcar) de la cava. <br>
     * <b>pre:</b> La lista de vinos está inicializada.
     * @return Vino más seco de la cava. Si la cava no tiene vinos se retorna null. Si existen varios vinos con el contenido en azúcar más bajo, se retorna el primer vino
     *         encontrado.
     */
    public Vino buscarVinoMasSeco( )
    {
    	if (vinos.isEmpty()) {
            return null;
        }
        
        Vino masSeco = vinos.get(0);
        
        for (int i = 1; i < vinos.size(); i++) {
            Vino vinoActual = vinos.get(i);
            if (vinoActual.darContenidoAzucar() < masSeco.darContenidoAzucar()) {
                masSeco = vinoActual;
            }
        }
        
        return masSeco;
    }

    /**
     * Busca los vinos del tipo dado por parámetro. <br>
     * <b>pre:</b> La lista de vinos está inicializada.
     * @param pTipo Tipo de vino de acuerdo a su contenido en azúcar.pTipo != null && pTipo != "" && (pTipo == SECO || pTipo == ABOCADO || pTipo == SEMI_SECO || pTipo ==
     *        SEMI_DULCE || pTipo == DULCE).
     * @return Lista de vinos del tipo dado.
     */
    public ArrayList<Vino> buscarVinosDeTipo( String pTipo )
    {
    	ArrayList<Vino> vinosDelTipo = new ArrayList<Vino>();
        
        for (int i = 0; i < vinos.size(); i++) {
            Vino vinoActual = vinos.get(i);
            if (vinoActual.darTipo().equals(pTipo)) {
                vinosDelTipo.add(vinoActual);
            }
        }
        
        return vinosDelTipo;
    }
    
    public boolean buscarVinosConNombreRepetido() {
        for (int i = 0; i <vinos.size(); i++) {
            for (int j = i + 1; j < vinos.size(); j++) {
                if (vinos.get(i).darNombre().equals(vinos.get(j).darNombre())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Agrega un nuevo vino a la cava si no existe actualmente un vino en la cava con el mismo nombre.<br>
     * <b>pre:</b> La lista de vinos está inicializada.<br>
     * <b>post:</b> Se agregó un nuevo vino a la lista de vinos.<br>
     * @param pNombre Nombre del vino. pNombre != null && pNombre != "".
     * @param pPresentacion Presentación del vino. pPresentacion != null && pPresentacion != "" && (pPresentacion == BOTELLA || pPresentacion == BARRIL).
     * @param pAnhoElaboracion Año de elaboración del vino. pAnhoElaboracion > 0.
     * @param pContenidoAzucar Contenido en azúcar del vino. pContenidoAzucar >= 0
     * @param pTipo Tipo de vino de acuerdo a su contenido en azúcar. pTipo != null && pTipo != "" && (pTipo == SECO || pTipo == ABOCADO || pTipo == SEMI_SECO || pTipo ==
     *        SEMI_DULCE || pTipo == DULCE).
     * @param pColor Color del vino. pColor != null && pColor != "" && (pColor == TINTO || pColor == ROSADO || pColor == BLANCO).
     * @param pLugarOrigen Lugar de origen del vino. lugarElaboracion != null y lugarElaboracion != "".
     * @param pImagen Imagen del vino. pImagen != null && pImagen != "".
     * @return True si el vino es agregado, false de lo contrario.
     */
    public boolean agregarVino( String pNombre, String pPresentacion, int pAnhoElaboracion, double pContenidoAzucar, String pTipo, String pColor, String pLugarOrigen, String pImagen )
    {
        Vino buscado = buscarVino( pNombre );
        boolean agregada = false;

        if( buscado == null )
        {
            Vino vino = new Vino( pNombre, pPresentacion, pAnhoElaboracion, pContenidoAzucar, pTipo, pColor, pLugarOrigen, pImagen );
            vinos.add( vino );
            agregada = true;
        }

        return agregada;
    }

    /**
     * Ordena ascendentemente la lista de vinos por nombre usando el algoritmo de burbuja. <br>
     * <b>pre:</b> La lista de vinos está inicializada. <br>
     * <b>post:</b> La lista de vinos está ordenada por nombre (orden ascendente).
     */
    public void ordenarVinosPorNombre( )
    {
    	for (int i = 0; i < vinos.size() - 1; i++) {
            for (int j = 0; j < vinos.size() - 1 - i; j++) {
                Vino vino1 = vinos.get(j);
                Vino vino2 = vinos.get(j + 1);
                
                if (vino1.darNombre().compareToIgnoreCase(vino2.darNombre()) > 0) {
                    vinos.set(j, vino2);
                    vinos.set(j + 1, vino1);
                }
            }
        }
    }

    /**
     * Ordena descendentemente la lista de vinos por año de elaboración usando el algoritmo de selección. <br>
     * <b>pre:</b> La lista de vinos está inicializada. <br>
     * <b>post:</b> La lista de vinos está ordenada por año de elaboración (orden descendente).
     */
    public void ordenarVinosPorAnhoElaboracion( )
    {
    	for (int i = 0; i < vinos.size() - 1; i++) {
            int indiceMayor = i;
            
            for (int j = i + 1; j < vinos.size(); j++) {
                if (vinos.get(j).darAnhoElaboracion() > vinos.get(indiceMayor).darAnhoElaboracion()) {
                    indiceMayor = j;
                }
            }
            
            if (indiceMayor != i) {
                Vino temp = vinos.get(i);
                vinos.set(i, vinos.get(indiceMayor));
                vinos.set(indiceMayor, temp);
            }
        }
    }

    /**
     * Ordena ascendentemente la lista de vinos por lugar de origen usando el algoritmo de inserción. <br>
     * <b>pre:</b> La lista de vinos está inicializada.<br>
     * <b> post: </b>La lista de vinos está ordenada por lugar de origen (orden ascendente).
     */
    public void ordenarVinosPorLugarOrigen( )
    {
    	for (int i = 1; i < vinos.size(); i++) {
            Vino vinoActual = vinos.get(i);
            int j = i - 1;
            
            while (j >= 0 && vinos.get(j).darLugarOrigen().compareToIgnoreCase(vinoActual.darLugarOrigen()) > 0) {
                vinos.set(j + 1, vinos.get(j));
                j--;
            }
            
            vinos.set(j + 1, vinoActual);
        }
    }
    
    /**
     * Ordena ascendentemente la lista de vinos por presentación usando el algoritmo de burbuja.
     * La comparación se realiza de forma sensible a mayúsculas y minúsculas.
     * <br>
     * <b>pre:</b> La lista de vinos está inicializada.<br>
     * <b>post:</b> La lista de vinos está ordenada por presentación (orden ascendente).
     */
    public void ordenarVinosPorPresentacion() {
        for (int i = 0; i < vinos.size() - 1; i++) {
            for (int j = 0; j < vinos.size() - 1 - i; j++) {
                if (vinos.get(j).darPresentacion().compareTo(vinos.get(j + 1).darPresentacion()) > 0) {
                    Vino temp = vinos.get(j);
                    vinos.set(j, vinos.get(j + 1));
                    vinos.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Ordena ascendentemente la lista de vinos por color usando el algoritmo de burbuja.
     * La comparación se realiza de forma sensible a mayúsculas y minúsculas.
     * <br>
     * <b>pre:</b> La lista de vinos está inicializada.<br>
     * <b>post:</b> La lista de vinos está ordenada por color (orden ascendente).
     */
    public void ordenarVinosPorColor() {
        for (int i = 0; i < vinos.size() - 1; i++) {
            for (int j = 0; j < vinos.size() - 1 - i; j++) {
                if (vinos.get(j).darColor().compareTo(vinos.get(j + 1).darColor()) > 0) {
                    Vino temp = vinos.get(j);
                    vinos.set(j, vinos.get(j + 1));
                    vinos.set(j + 1, temp);
                }
            }
        }
    }
    
    /**
     * Ordena ascendentemente la lista de vinos por contenido de azúcar usando el algoritmo de burbuja.
     * Los vinos con menor contenido de azúcar aparecerán primero en la lista.
     * <br>
     * <b>pre:</b> La lista de vinos está inicializada.<br>
     * <b>post:</b> La lista de vinos está ordenada por contenido de azúcar (orden ascendente).
     */
    public void ordenarVinosPorContenidoAzucar() {
        for (int i = 0; i < vinos.size() - 1; i++) {
            for (int j = 0; j < vinos.size() - 1 - i; j++) {
                if (vinos.get(j).darContenidoAzucar() > vinos.get(j + 1).darContenidoAzucar()) {
                    Vino temp = vinos.get(j);
                    vinos.set(j, vinos.get(j + 1));
                    vinos.set(j + 1, temp);
                }
            }
        }
    }
    
    /**
     * Ordena ascendentemente la lista de vinos por tipo usando el algoritmo de selección.
     * La comparación se realiza ignorando diferencias entre mayúsculas y minúsculas.
     * <br>
     * <b>pre:</b> La lista de vinos está inicializada.<br>
     * <b>post:</b> La lista de vinos está ordenada por tipo (orden ascendente, insensible a mayúsculas).
     */
    public void ordenarPorTipo() {
        for (int i = 0; i < vinos.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < vinos.size(); j++) {
                String tipoActual = vinos.get(j).darTipo();
                String tipoMinimo = vinos.get(minIndex).darTipo();
                if (tipoActual.compareToIgnoreCase(tipoMinimo) < 0) {
                    minIndex = j;
                }
            }
            
            Vino temp = vinos.get(i);
            vinos.set(i, vinos.get(minIndex));
            vinos.set(minIndex, temp);
        }
    }
    
    
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    // TODO Parte1 PuntoD: Documente e implemente el método verificarInvariante. Si lo desea puede crear métodos privados en esta parte.

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1.
     * @return Respuesta 1.
     */
    public String metodo1( )
    {
        return "Respuesta 1.";
    }

    /**
     * Método para la extensión 2.
     * @return Respuesta 2.
     */
    public String metodo2( )
    {
        return "Respuesta 2.";
    }
}