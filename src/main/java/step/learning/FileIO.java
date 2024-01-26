package step.learning;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileIO {
    public void run() {
        // Робота з файлами
        // традиційно поділяється на дві групи:
        // - створення, пошук, копіювання (робота з файловою системою)
        // fsDemo() ;
        // listDemo() ;
        // - збереження та відновлення даних
        ioDemo() ;
        readStreamDemo();
    }

    private String readStreamToEnd(InputStream inputStream) throws IOException{
/*        int sym;
        StringBuilder res = new StringBuilder();
        while ((sym = inputStream.read()) != -1){
            res.append((char)sym);
        }

        return  res.toString();*/
        byte[] buffer = new byte[32*1024];
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        int len;
        while((len = inputStream.read(buffer)) > -1){
            byteBuilder.write(buffer, 0,len);
        }
        return byteBuilder.toString();
    }
    private void readStreamDemo(){
        try(InputStream fileStream = new FileInputStream("file.txt")){
            System.out.println(readStreamToEnd( fileStream ));
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    private void ioDemo(){
        try (OutputStream writeStream = new FileOutputStream("file.txt");)
        {
            writeStream.write("Hello,world".getBytes(StandardCharsets.UTF_8));
            //writeStream.close();
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }

        try (FileWriter writer = new FileWriter("file2.txt"))
        {
            writer.write("hello world");
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
        try(DataOutputStream dos = new DataOutputStream(Files.newOutputStream(Paths.get("file3.txt"))))
        {
            dos.writeDouble(0.1);
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }


        try(Scanner scanner = new Scanner(new FileInputStream("file.txt"))){
            while(scanner.hasNext()){
                System.out.println(scanner.next());
            }
            System.out.println();
        }
        catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    private void listDemo() {
        // одержання переліку файлів у директорії
        String filename = "." + File.separator ;  // ".\" - current directory
        File f = new File( filename ) ;
        // f.list() - перелік імен файлів у директорії (String[])
        String[] list = f.list() ;
        if( list == null ) {
            System.err.println("Access denied");
        }
        else {
            for (String name : list) {
                System.out.println(name);
            }
        }
        // f.listFiles() - перелік файлових об'єктів (File[])
        File[] files = f.listFiles() ;
        if( files == null ) {
            System.err.println("Access denied");
        }
        else {
            for( File file : files ) {
                System.out.println( file.getName() ) ;
            }
        }
        /*
        Д.З. Реалізувати відображення файлів поточної директорії
        за зразком команди ls / dir
        Mode                 LastWriteTime     Length   Name
        ----                 -------------     ------   ----
        d-----        24.01.2024     18:51              .idea
        d-----        22.01.2024     17:51              src
        d-----        22.01.2024     18:08              target
        -a----        22.01.2024     17:50        490   .gitignore
        -a----        22.01.2024     17:50        490   .gitignore
        -a----        22.01.2024     17:51        780   pom.xml

        ------ (d-directory, a-archive, h-hidden, r/R - canRead, w/W - canWrite, x/X - canExec)
        -a-rwX - архівний файл з дозволом на читання/запис, але заборонений для виконання

        Викласти у репозиторії, додати скріншоти результатів виконання
         */
    }

    private void fsDemo() {
        String filename = "." + File.separator ;  // ".\" - current directory
        File f = new File( filename ) ;
        // ! створення об'єкту НЕ створює/відкриває файл (ніяк не впливає на файлову систему)
        // для впливу на ФС викликаються методи даного об'єкту
        if( f.exists() ) {
            System.out.printf( "Path '%s' exists\n", filename ) ;
        }
        else {
            System.out.printf( "Path '%s' does not exist\n", filename  ) ;
        }
        // !!! у файловій системі є різні об'єкти, за які відповідає File:
        // це директорії, файли, сімлінки (де вони існують) тощо
        // f.exists() визначає існування незалежно від виду об'єкта
        if( f.isDirectory() ) {
            System.out.printf( "Path '%s' exists as directory\n", filename ) ;
        }
        if( f.isFile() ) {
            System.out.printf( "Path '%s' exists as file\n", filename ) ;
        }
        filename = "." + File.separator + "subdir" ;
        File subDir = new File(filename) ;
        if( subDir.exists() ) {
            System.out.printf( "Path '%s' exists \n", filename ) ;
            if( subDir.delete() ) {
                System.out.printf( "Path '%s' deleted \n", filename ) ;
            }
            else {
                System.out.printf( "Path '%s' deletion error \n", filename ) ;
            }
        }
        else {
            if(subDir.mkdir())   // створення директорії
                System.out.printf( "Path '%s' created \n", filename ) ;
            else
                System.out.printf( "Path '%s' creation error \n", filename ) ;
        }
    }
}
