
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class TSPlibReader {
    
    public Punto[] nodeptr;
    private String name;
    private int n;
    
    public TSPlibReader (String tsp_file_name)
  /*
   * FUNCTION: Constructor clase TSPlibReader class
   * INPUT: Ruta al archivo de la instancia
   */
  {
    try {
      /* leer instancia desde un archivo */
      nodeptr = read_etsp(tsp_file_name); 
    } catch (IOException e) {
      System.err.println("Error: No se pudo leer el archivo. " + e.getMessage());
      //System.exit(1);
    }
  }
    
    private Punto[] read_etsp(String tsp_file_name) throws IOException
    /*
     * FUNCTION: read_etsp: lectura y parsing de instancia TSPlib
     * INPUT: ruta al archivo de instancia
     * OUTPUT: arreglo de coordenadas
     * COMMENTS: archivo de instancia debe estar en formato TSPLIB
     */
    {
    String buf;
    int i=0;
    Punto[] nodeptr = null;
    boolean found_coord_section = false;
        
    if (tsp_file_name == null) {
      System.err.println("Error: Instacia no especificada, abortando...");
      //System.exit(1);
    }
        
    if (!new File(tsp_file_name).canRead()) {
      System.err.println("Error: No se puede leer el archivo " + tsp_file_name);
      //System.exit(1);
    }
        
    //System.out.println("\n# Leyendo archivo TSPlib " + tsp_file_name + " ... ");
    Reader reader = new InputStreamReader(new FileInputStream(tsp_file_name), "UTF8");
    BufferedReader bufferedReader = new BufferedReader(reader);
    String line = bufferedReader.readLine();
    while (line != null) {
      if (line.trim().startsWith("EOF"))
        break;
      if (!found_coord_section) {
        if (line.startsWith("NAME")) {
          name = line.split(":")[1].trim();
        } else if (line.startsWith("COMMENT")) {
        } else if (line.startsWith("TYPE") && !line.contains("TSP")) {
          System.err.println("Instancia no esta en el formato TSPLIB !!");
          System.exit(1);
        } else if (line.startsWith("DIMENSION")) {
          n = Integer.parseInt(line.split(":")[1].trim());
          nodeptr = new Punto[n];
          assert (n > 2 && n < 6000);
        } else if (line.startsWith("DISPLAY_DATA_TYPE")) {
        } else if (line.startsWith("EDGE_WEIGHT_TYPE")) {
          buf = line.split(":")[1].trim();
          if (buf.equals("EUC_2D")) {
            //distance_type = Distance_type.EUC_2D;
          } else if (buf.equals("CEIL_2D")) {
            //distance_type = Distance_type.CEIL_2D;
          } else if (buf.equals("GEO")) {
            //distance_type = Distance_type.GEO;
          } else if (buf.equals("ATT")) {
            //distance_type = Distance_type.ATT;
          } else {
            System.err.println("EDGE_WEIGHT_TYPE " + buf + " no implementado en la clase.");
            System.exit(1);
          }
        }
      } else {
        String[] city_info = line.trim().split("\\s+");
        nodeptr[i] = new Punto();
        nodeptr[i].setEtiqueta(Integer.parseInt(city_info[0]));
        nodeptr[i].setx(Double.parseDouble(city_info[1]));
        nodeptr[i].sety(Double.parseDouble(city_info[2]));
        i++;
      }
            
      if (line.startsWith("NODE_COORD_SECTION"))
        found_coord_section = true;
      line = bufferedReader.readLine();
    }
    if (!found_coord_section) {
      System.err.println("Error ocurrio al buscar el inicio de las coordenadas !!");
      System.exit(1);
    }
    bufferedReader.close();
    return (nodeptr);
  };
    
    public int getNum(){
        return n;
    }
    
}
