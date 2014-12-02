/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oficina.classes;

import java.io.IOException;

/**
 *
 * @author sahudy
 */
public class BackupPgSQL {

    public void run() {
//        try {
//            System.out.println("iniciando Bakup...");
//            
//            String str = "C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe Oficina -U postgres -h localhost -p 5432 -Fp -f C:\\Users\\Filipe\\Desktop\\OficinaBackup.txt";
//
//            Runtime runtime = Runtime.getRuntime();
//            Process process = runtime.exec(str); 
//
//        } catch (IOException e) {
//            System.out.println("ERROR " + e.getMessage());
//        }
//        System.out.println("acabou...");
//        
//        
        try{
            ProcessBuilder pb;  
            Process p;  
            pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe ", "-i", "-h", "localhost", "-p", "5432","-U", "postgres", "-F", "p", "-b", "-v" ,"-f", "C:\\Users\\Administrator\\Desktop\\OficinaBackup1.sql", "Oficina");  
            pb.environment().put("PGPASSWORD", "asdasd");  
            pb.redirectErrorStream(true);
            p = pb.start();
        }catch(Exception e){  
            System.out.println("ERROR " + e.getMessage());
        }
    }
//    
//    public void realizarBackup(){
//        try{  
//            ProcessBuilder pb;  
//            Process p;  
//            pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe ", "-i", "-h", "localhost", "-p", "5432","-U", "postgres", "-F", "c", "-b", "-v" ,"-f", "C:\\Users\\Filipe\\Desktop\\Oficina.backup");
//            pb.environment().put("postgres", "darkspock13");  
//            pb.redirectErrorStream(true);  
//            p = pb.start();
//        }
//        catch(Exception ex){  
//            ex.printStackTrace();
//        }   
//    }
}