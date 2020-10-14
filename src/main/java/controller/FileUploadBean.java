package controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@ManagedBean(name = "fileupload")
@RequestScoped
public class FileUploadBean {
    private Part uploadedFile;
    private String folder = "C:\\files";

    public Part getUploadedFile() {

        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {

        this.uploadedFile = uploadedFile;
    }


    public void saveFile(){

        try (InputStream input = uploadedFile.getInputStream())
        {
            String fileName = uploadedFile.getSubmittedFileName();
            Files.copy(input, new File(folder, fileName).toPath(),REPLACE_EXISTING);
            leerArchivo(folder+"\\"+fileName);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void leerArchivo(String ruta){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File (ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea="",cod="",xml="";
            while((linea=br.readLine())!=null){
                xml+=linea;
                cod+=linea;
            }
            System.out.print(xml);
            System.out.print(cod);

            int PRETTY_PRINT_INDENT_FACTOR = 4;
            try {
                JSONObject xmlJSONObj = XML.toJSONObject(cod);
                String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
                System.out.println(jsonPrettyPrintString);
            } catch (JSONException je) {
                System.out.println(je.toString());
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

    }
}
