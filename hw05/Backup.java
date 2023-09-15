package hw05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Backup
 */
public class Backup {
    public static void main(String[] args) throws IOException{
        try {
            backup("./hw05");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

    }

    /**
     * Резервное сохранение файлов
     * @param sourceDirName каталог откуда сохраняем
     */

    private static void backup(String sourceDirName) throws IOException {
        File sourceDir = new File(sourceDirName);   
        File destDir = new File(String.format("%s/backup",sourceDirName));
        destDir.mkdir();
        File[] files = sourceDir.listFiles();
       
        for (File file: files) {
            if (!file.isDirectory()){
                File newFile = new File(String.format("%s/%s", destDir, file.getName()));
                Files.copy(file.toPath(),newFile.toPath(),StandardCopyOption.REPLACE_EXISTING);
                System.out.printf("Файл %s скопирован в backup.\n",file.getName());
            }
        }
    }
        
        
}
