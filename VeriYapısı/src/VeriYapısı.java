import java.util.Scanner;
import java.io.*;
public class VeriYapısı {

    
    public static void main(String[] args) {
        
        AvlTree nesne = new AvlTree();
        Scanner rum=new Scanner(System.in);
        boolean cıkış=true;
        
        while(cıkış==true){
            System.out.println("\n");
            System.out.println("1) Ekle \n 2) Sil \n 3) Ata \n 4)Çıkış");
            System.out.println("Yapmak istediğiniz işlem numarasını giriniz.");
            int deger=rum.nextInt();
            
            switch(deger){
                case 1:
                    System.out.println("Eklemek istediğiniz veriyi girebilirsini.");
                    String veri=rum.next();
                    nesne.ekle(nesne.getKök(),veri);
                    nesne.yaz();
                    break;
                case 2:
                    System.out.println("Silmek istediğiniz veriyi girebilirsini.");
                    String silinecek=rum.next();
                    nesne.delete(nesne.getKök(),silinecek);
                    nesne.yaz();
                    break;
                case 3:
                    System.out.println("Atasını ögrenmek istediğiniz veriyi giriniz.");
                    String ata=rum.next();
                    nesne.ataBul(nesne.getKök(),ata);
                  
                    break;
                case 4:
                    cıkış=false;
                    break;
                     
                default:
                    System.out.println("yanlış işlem");
                    
                   
                    
            }
        }
     
    }

}
