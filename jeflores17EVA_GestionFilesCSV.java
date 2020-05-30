package practica01;
import java.io.*;
import java.util.*;
public class jeflores17EVA_GestionFilesCSV{
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Random rd = new Random();
        String cadena = "";
        ArrayList<String> lista_nombres = new ArrayList<>();
        lista_nombres.add("TENE S. MARIA");
        lista_nombres.add("FAGGIONI L. GIOVANNI");
        lista_nombres.add("LOPEZ Q. JAIME");
        lista_nombres.add("AVILA D. GREYS");
        lista_nombres.add("LUZURIAGA C. DIANA");
        lista_nombres.add("MENDEZ C. JANETH");
        lista_nombres.add("CAMPOS D. FRANCISCA");
        lista_nombres.add("BORJA A. ANDREA");
        lista_nombres.add("MENDOZA J. FATIMA");
        lista_nombres.add("SANCHEZ B. CARLA");
        lista_nombres.add("CASTILLO H. NESTOR");
        lista_nombres.add("SOLORZANO T. MARIBEL");
        ArrayList<String[]> archivo_est = new ArrayList<>();
        ArrayList<String> notas_est = new ArrayList<>();
        ArrayList<String> promos = new ArrayList<>();

        Formatter archivo_nombres = new Formatter("jeflores17BdEst_OrdenNomb.csv", "UTF-8");
        archivo_nombres.format("NOMBRES;FOR1;CHA1;VID1;DIS1;PRE1;FOR2;CHA2;VID2;DIS2;PRE2;FIN1;FIN2;TOTAL;" +
                "ALERTA;PROMOCIÓN\n");
        Formatter archivo_total = new Formatter("jeflores17BdEst_OrdenTOTAL.csv", "UTF-8");
        archivo_total.format("NOMBRES;FOR1;CHA1;VID1;DIS1;PRE1;FOR2;CHA2;VID2;DIS2;PRE2;FIN1;FIN2;TOTAL;" +
                "ALERTA;PROMOCIÓN\n");
        Formatter archivo_promo = new Formatter("jeflores17BdEst_OrdenPromo.csv", "UTF-8");
        archivo_promo.format("NOMBRES;FOR1;CHA1;VID1;DIS1;PRE1;FOR2;CHA2;VID2;DIS2;PRE2;FIN1;FIN2;TOTAL;" +
               "ALERTA;PROMOCIÓN\n");

        Collections.sort(lista_nombres);
        System.out.println("_____________________________________________________________________________");
        System.out.println("|BIENVENIDO AL SISTEMA DE CALIFICACIÓN DE LA MODALIDAD ABIERTA Y A DISTANCIA|");
        System.out.println("|---------------------------------------------------------------------------|");
        System.out.println("|Generando archivo_nombres de archivo_est...                                              |");
        System.out.println("|___________________________________________________________________________|");
        int acum = 0;
        try{
            while (acum < lista_nombres.size()){
                String nombre;
                nombre = lista_nombres.get(acum);
                System.out.print("Leyendo nombre de archivo_nombres txt...\n");
                String alerta = "", promocion;
                double nota_acre, nota_par_final1, nota_par_final2, fin1 = 0, fin2 = 0;
                rd.nextFloat();
                double foro1 = rd.nextDouble();
                double foro2 = rd.nextDouble();
                double chat1 = rd.nextDouble();
                double chat2 = rd.nextDouble();
                double video1 = rd.nextDouble();
                double video2 = rd.nextDouble();
                // Con esto determinamos el rango requerido para obtener los números aleatorios
                double eval_dis_par1 = rd.nextDouble() * 6;
                double eval_dis_par2 = rd.nextDouble() * 6;
                double eval_pres1 = rd.nextDouble() * 14;
                double eval_pres2 = rd.nextDouble() * 14;
                nota_par_final1 = foro1 + video1 + chat1 + eval_dis_par1 + eval_pres1;
                nota_par_final2 = foro2 + video2 + chat2 + eval_dis_par2 + eval_pres2;
                if (eval_pres1 < 8 && eval_pres2 < 8) {
                    fin1 = rd.nextDouble() * 20;
                    fin2 = rd.nextDouble() * 20;
                    alerta = "Rendir Final 1 y 2";
                } else{
                    if (eval_pres1 < 8) {
                        fin1 = rd.nextDouble() * 20;
                        foro1 = 0;
                        chat1 = 0;
                        video1 = 0;
                        eval_dis_par1 = 0;
                        nota_par_final1 = eval_pres1 + fin1;
                        alerta = "Rendir Final 1";
                    }
                    if (eval_pres2 < 8) {
                        fin2 = rd.nextDouble() * 20;
                        foro2 = 0;
                        chat2 = 0;
                        video2 = 0;
                        eval_dis_par2 = 0;
                        nota_par_final2 = eval_pres2 + fin2;
                        alerta = "Rendir Final 2";
                    }
                }
                nota_acre = nota_par_final1 + nota_par_final2;
                if (nota_acre < 28) {
                    if (nota_par_final1 < nota_par_final2) {
                        fin1 = rd.nextDouble() * 20;
                        alerta = "Rendir Final 1";
                        nota_par_final1 = fin1 + foro1 + video1 + chat1 + eval_dis_par1 + eval_pres1;
                    } else if (nota_par_final2 < nota_par_final1) {
                        fin2 = rd.nextDouble() * 20;
                        nota_par_final2 = fin2 + foro2 + video2 + chat2 + eval_dis_par2 + eval_pres2;
                        alerta = "Rendir Final 2";
                    }
                    if (nota_par_final1 == nota_par_final2) {
                        if (eval_pres1 >= 8 && eval_pres2 >= 8) {
                            fin2 = rd.nextDouble() * 20;
                            nota_acre = fin2 + nota_acre;
                            alerta = "Rendir Final 2";
                        }
                    }
                }
                if (nota_acre >= 28) {
                    promocion = "APROBADO";
                }else{
                    promocion = "REPROBADO";
                }
                if (foro1 == 0 && video1  == 0 &&  chat1 == 0 && eval_dis_par1 == 0 && eval_pres1 == 0 &&
                        promocion.equals("REPROBADO")){
                    alerta = "Reprobado falta de trabajo";
                }else if(foro2 == 0 && video2  == 0 &&  chat2 == 0 && eval_dis_par2 == 0 && eval_pres2 == 0 &&
                        promocion.equals("REPROBADO")){
                    alerta = "Reprobado falta de trabajo";
                }
                // Con esto se generan arreglos independientes de cada estudiante para de esta manera facilitar la
                // creación de los archivos ordenados por notas de acreditaccion y promociones
                String g = String.format("%.2f", nota_acre);
                String[] a = {nombre, String.valueOf(foro1), String.valueOf(chat1), String.valueOf(video1),
                        String.valueOf(eval_dis_par1), String.valueOf(eval_pres1), String.valueOf(foro2),
                        String.valueOf(chat2), String.valueOf(video2), String.valueOf(eval_dis_par2),
                        String.valueOf(eval_pres2), String.valueOf(fin1), String.valueOf(fin2),
                        g, alerta, promocion};
                archivo_est.add(a);
                promos.add(promocion);
                notas_est.add(g);
                // SE CAMBIÓ LA ETIQUETA de TRA por DIS para evitar confusiones acerca de la correspondencia
                // de la nota, la etiqueta DIS corresponde a el examen a distancia
                System.out.println("Grabando inforamción en el archivo_nombres csv.");
                cadena = String.format("%s%s;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;%.2f;" +
                                "%.2f;%s;%s\n",
                        cadena, nombre, foro1, chat1, video1, eval_dis_par1, eval_pres1, foro2, chat2, video2,
                        eval_dis_par2, eval_pres2, fin1, fin2, nota_acre, alerta, promocion);
                acum ++;
                }
            archivo_nombres.format("%s;",cadena);
            archivo_nombres.close();

            // Generación del csv correspondiente al ordenamiento por la salido de TOTAL

            Collections.sort(notas_est);
            cadena = "";
            ArrayList<String[]> archivo_est1 = new ArrayList<>();

            for (int i = 0; i < notas_est.size(); i++) {
                for (int j = 0; j < notas_est.size(); j++) {
                    if (Double.parseDouble(notas_est.get(i)) == Double.parseDouble(archivo_est.get(j)[13])){
                        archivo_est1.add(archivo_est.get(j));
                    }
                }
            }
            for (String [] a: archivo_est1) {
                cadena = String.format("%s%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        cadena, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8],
                        a[9], a[10], a[11], a[12], a[13], a[14], a[15]);
            }
            archivo_total.format("%s;",cadena);
            archivo_total.close();

           // Generación del csv correspondiente al ordenamiento por la salido de PROMOCION

            Collections.sort(promos);
            archivo_est1.clear();
            cadena = "";
            String [] vaciar = {" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
            for (int i = 0; i < notas_est.size(); i++) {
                for (int j = 0; j < notas_est.size(); j++) {
                    if (promos.get(i).equals(archivo_est.get(j)[15])){
                        archivo_est1.add(archivo_est.get(j));
                        archivo_est.set(j, vaciar);
                        promos.set(i, "vaciado");
                    }
                }
            }
            archivo_est1.removeIf(a -> (a[1] == null) || (a[1].equals("")));
            for (String [] b: archivo_est1) {
                cadena = String.format("%s%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        cadena, b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7], b[8],
                        b[9], b[10], b[11], b[12], b[13], b[14], b[15]);
            }
            archivo_promo.format("%s;",cadena);
            archivo_promo.close();

            System.out.println("ARCHIVO CSV GENERADO CON ÉXITO.");
            System.out.println("SALIENDO DEL PROGRAMA...");
            }catch (Exception e2){
                e2.printStackTrace();
            }
    }
}
