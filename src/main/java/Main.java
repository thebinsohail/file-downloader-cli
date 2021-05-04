import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;
public class Main {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        System.out.println("Enter a link to download: ");

        String link=in.nextLine();
        URL url= null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            System.out.println("Please enter a valid link!");
            e.printStackTrace();
        }

        String fileName=FilenameUtils.getName(url.getPath());
        System.out.println("Where do you want to save this file? ");
       String filePath=in.nextLine();
       filePath=filePath+"/"+fileName;
        System.out.println("Selected Directory:"+filePath);
        File file=new File(filePath);
        System.out.println("Downloading...");
        new Thread(new Downloader(link,file)).start();
    }
}
