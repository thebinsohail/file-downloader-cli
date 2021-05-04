import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader implements Runnable{

    String link;
    File file;

    //Constructor
    public Downloader(String link, File file) {
        this.link = link;
        this.file = file;
    }

    @Override
    public void run() {
            try{
            URL url=new URL(link);

            //Establish the connection
                HttpURLConnection http=(HttpURLConnection) url.openConnection();

                //get the total file size
                double fileSize=(double)http.getContentLength();
                //create the buffer stream to get chunks of data
                BufferedInputStream bufferedinput=new BufferedInputStream(http.getInputStream());
                //passing the instance of the File
                FileOutputStream fileOutput=new FileOutputStream(this.file);
                //telling stream the size of the buffer
                BufferedOutputStream bufferedOutput=new BufferedOutputStream(fileOutput,1024);

                //defining our fields for client's end
                byte [] buffer=new byte[1024];
                double downloaded=0.00;
                int read=0;
                double percentDownloaded=0.00;

                //Read Buffer if the bufferedinput is greater than 0 or equal
                while((read = bufferedinput.read(buffer, 0,1024))>=0){
                 bufferedOutput.write(buffer,0,read);
                 downloaded+=read;
                 percentDownloaded=(downloaded*100)/fileSize;
                 String percent=String.format("%.2f",percentDownloaded);
                    System.out.println(percent+"% downloaded...");
                    System.out.flush();
                }
                //closing the streams
                bufferedOutput.close();
                bufferedinput.close();
                System.out.println("\nDownload Complete!");
            }catch(Exception e){
               e.printStackTrace();
            }
    }
}

