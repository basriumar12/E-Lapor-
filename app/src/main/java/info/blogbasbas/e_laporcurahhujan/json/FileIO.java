package info.blogbasbas.e_laporcurahhujan.json;

import android.content.Context;
import android.os.Environment;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Shiburagi on 01/07/2016.
 */
public class FileIO {

    // Step 1: private static variable of INSTANCE variable
    private static volatile FileIO INSTANCE;

    // Step 2: private constructor
    private FileIO() {

    }

    // Step 3: Provide public static getInstance() method returning INSTANCE after checking
    public static FileIO getInstance() {

        // double-checking lock
        if(null == INSTANCE){

            // synchronized block
            synchronized (FileIO.class) {
                if(null == INSTANCE){
                    INSTANCE = new FileIO();
                }
            }
        }
        return INSTANCE;
    }
   
    


    public void writeError(String filename, Exception ex) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), errorLogFilename(filename));
            StringBuilder builder = read(file);
            PrintWriter writer = new PrintWriter(
                    file
            );

            ex.printStackTrace(writer);
            writer.write("\n");
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private StringBuilder read(File file) {
        StringBuilder builder = new StringBuilder();
        if (file.exists())
            try {
                BufferedReader reader = new BufferedReader(new FileReader(
                        file
                ));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append(System.getProperty("line.separator"));
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return builder;
    }



    public List<File> getFiles(Context context, String folder,
                               String uuid) {

        List<File> files = new ArrayList<>();
        if (uuid == null)
            return files;

        File from = new File(context.getFilesDir(), folder);
        File[] listFiles = from.listFiles();

        String regex = String.format(
                Locale.getDefault(),
                "(%s).*",
                uuid
        );
        if (listFiles != null)
            for (File file : listFiles) {
                if (file.getName().matches(regex))
                    files.add(file);
            }
        return files;
    }

    /**
     * This method use to read data from Asset Resource Directory (Local).
     *
     * @param path path of the local REST
     * @return plain name from file
     * @throws IOException
     */
    public String readFromAsset(Context context, String path) throws IOException {

        byte[] buffer;
        InputStream inputStream;
        inputStream = context.getAssets().open(path);

        int size = inputStream.available();

        if (size == 0) {
        }

        buffer = new byte[size];

        inputStream.read(buffer);
        inputStream.close();


        return new String(buffer);

    }

    public String errorLogFilename(String dateString) {
        return "ErrorLog_" + dateString + ".txt";
    }

    public <T> T readFromRaw(Context context, int id, Class<T> aClass) throws IOException {
        byte[] buffer;
        InputStream inputStream;
        inputStream = context.getResources().openRawResource(id);

        int size = inputStream.available();

        if (size == 0) {
            return null;
        }

        buffer = new byte[size];

        inputStream.read(buffer);
        inputStream.close();


        return new Gson().fromJson(new String(buffer), aClass);

    }
}
