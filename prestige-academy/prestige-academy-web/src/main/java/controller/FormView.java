package controller;

import com.isika.prestigeacademy.model.dto.MyFile;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@ManagedBean
public class FormView {

    private final List<MyFile> files = new ArrayList<MyFile>();
    private StreamedContent streamedContent;
    private String selectedFileName;

    @PostConstruct
    public void init() {

        MyFile myFile1 = new MyFile(1, "1.pdf");
        MyFile myFile2 = new MyFile(2, "2.pdf");
        files.add(myFile1);
        files.add(myFile2);

    }

    public List<MyFile> getFiles() {
        return files;
    }

    public String getSelectedFileName() {
        return selectedFileName;
    }

    public void setSelectedFileName(String selectedFileName) {
        this.selectedFileName = selectedFileName;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public void onRowSelect(SelectEvent event) {
        String fileName = ((MyFile) event.getObject()).getName();

        // we use this for refill the stream
        selectedFileName = fileName;

        createStream(fileName);

    }

    private StreamedContent createStream(String fileName) {
        streamedContent = new DefaultStreamedContent(getData(fileName), "application/pdf", "downloaded_" + fileName);
        return streamedContent;
    }

    private InputStream getData(String fileName) {

        // pdf files under src\main\resources
        File file = new File("D:\\GitLab\\Projet3-PrestigeAcademy\\prestige-academy\\prestige-academy-web\\src\\main\\webapp\\resources\\CV.pdf");


        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return is;

    }


    public void openFile(File file) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            // Open file.
            input = new BufferedInputStream(new FileInputStream(file), 10240);
            File file2 = new File(System.getProperty("user.dir") + "\\src\\main\\webapp\\resources\\" + "1.pdf");
            // Init servlet response.
            response.reset();
            // lire un fichier pdf
            response.setHeader("Content-type", "application/pdf");
            response.setContentLength((int) file.length());

            response.setHeader("Content-disposition", "inline; filename=\\home\\seme7-lenovo\\Desktop\\Projet 3 ISIKA-CDi5\\Projet 3\\prestige-academy\\prestige-academy-web\\src\\main\\webapp\\resources\\" + "1.pdf");
            response.setHeader("pragma", "public");
            output = new BufferedOutputStream(response.getOutputStream(), 10240);

            // Write file contents to response.
            byte[] buffer = new byte[10240];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            // Finalize task.
            output.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Gently close streams.

            output.close();
            input.close();
        }
    }

    // refill the stream
    public void refreshStream() {
        createStream(selectedFileName);
    }

    public String generateRandomIdForNotCaching() {
        return java.util.UUID.randomUUID().toString();
    }

}
