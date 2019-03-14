package bingo;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Juan Luis
 */
public class Bingo {
  public static void main (String[] args) {

    int[][] CartonBingo = new int[3][9]; // array de 3 filas por 6 columnas
    // 4 huecos por fila
    
    /*
    
    1-9   9 numeros posibles
    10-19 10 numeros posibles
    20-29
    80-90 11 numeros posibles
    
    */
    
    for (int i=0; i<3; i++) {  //filas
      for (int j=0; j<9; j++) {  //columnas
        boolean repetido;
        do {
          repetido = false;
          switch (j) {
            case 0: //columna1
              CartonBingo[i][j] = numeroAleatorio(1,9);
              break;
            case 8: // columna9
              CartonBingo[i][j] = numeroAleatorio(80,90);
              break;
            default:
              CartonBingo[i][j] = numeroAleatorio(j*10,j*10+9);
          }

          // acabamos de meter el elemento i,j
          for (int k=0; (k<i) && !repetido; k++) {
            if (CartonBingo[i][j] == CartonBingo[k][j]) {
              repetido = true;
            }
          }
        } while (repetido);
        
      }
      
    }
    // ya tenemos los números generados
    
    OrdenarPorColumnas(CartonBingo);
    
    HacerHuecos(CartonBingo);
    
    PintaCarton(CartonBingo);
    
    

  }
  
  public static int numeroAleatorio(int min, int max){
    return ((int)(Math.random()*(max-min+1)) + min);
  }
  
  public static void PintaCarton(int[][] carton) {
    for (int i=0; i<3; i++) {
      for (int j=0; j<9; j++) {
        if (carton[i][j]!=0) {
          System.out.printf("%3d",carton[i][j]);
        } else {
          System.out.printf("   ");
        }
      }
      System.out.println();
    }
  }
  
  public static void OrdenarPorColumnas (int[][] carton){
    ArrayList<Integer> columnaAux;
    for (int i=0; i<9; i++) {
      // pasar la columna a arrayList
      columnaAux = SacaColumna(carton,i);
      // ordenar el arrayList
      Collections.sort(columnaAux);
      // pasar el arrayList a la columna
      MeteColumna(carton,i,columnaAux);
    }
    
  }
  
  public static ArrayList<Integer> SacaColumna (int[][] carton, int nCol) {
    ArrayList<Integer> resultado = new ArrayList<Integer>();
    for (int i=0; i<3; i++) {
      resultado.add(carton[i][nCol]);
    }
    
    return resultado;
  }
  
  public static void MeteColumna (int[][] carton, int nCol, ArrayList<Integer> ordenado) {
    
    for (int i=0; i<3; i++) {
      carton[i][nCol] = ordenado.get(i);
    }
    

  }
  
   public static void HacerHuecos(int[][] carton) {
     for (int i=0; i<3; i++) {
       int huecos=0;
       do {
         // generar posicion del hueco
         int posicion = numeroAleatorio(0,8);
         if ((carton[i][posicion] != 0) && (NumerosPorColumna(carton,posicion)>1)) {
           carton[i][posicion] = 0;
           huecos++;
         }
       } while (huecos < 4);       
     }
   }
   
   public static int NumerosPorColumna(int[][] carton, int nCol) { // cuenta números por columna que no son huecos
     int numerosNoHuecos=0;
     for (int i=0; i<3; i++){
       if (carton[i][nCol] != 0) {
         numerosNoHuecos++;
       }
     }     
     return numerosNoHuecos;
   }
}
