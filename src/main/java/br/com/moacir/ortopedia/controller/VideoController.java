/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.Video;
import br.com.moacir.ortopedia.repository.VideoRepository;
import br.com.moacir.ortopedia.util.Paginas;
import br.com.moacir.ortopedia.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "videoController")
public class VideoController {

    @Autowired
    private VideoRepository repository;

    private final String diretorio = System.getProperty("user.dir")
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "webapp"
            + File.separator + "resources"
            + File.separator + "serenity-layout"
            + File.separator + "videos"
            + File.separator + "aula";

    private Video video;
    private List<Video> videos;

    public void abrir() {
        carregar();
        Util.redirect(Paginas.VIDEO_LISTA);
    }

    public void carregar() {
        videos = repository.findAll();
    }

    public void novo() {
        video = Video.builder()
                .build();
        Util.redirect(Paginas.VIDEO_EDITA);
    }

    public void edita(Video video) {
        this.video = video;
        Util.redirect(Paginas.VIDEO_EDITA);
    }

    public void salva() {
        if (video.getNomeArquivo() != null && !video.getNomeArquivo().isEmpty()) {
            repository.save(video);
            cancela();
        } else {
            Util.showErrorMessage("Faça upload do video antes de salvar", "");
        }
    }

    public void deleta(Video video) {
        repository.delete(video);
        new File(diretorio).delete();
        carregar();
    }

    public void cancela() {
        video = null;
        carregar();
        Util.redirect(Paginas.VIDEO_LISTA);
    }

    public void upload(FileUploadEvent event) {
        File folder = new File(diretorio);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (InputStream is = event.getFile().getInputStream()) {
            try (OutputStream os = new FileOutputStream(folder.getAbsolutePath() + File.separator + event.getFile().getFileName())) {
                byte buf[] = new byte[1024];
                int len;
                while ((len = is.read(buf)) > 0) {
                    os.write(buf, 0, len);
                }

                video.setNomeArquivo(event.getFile().getFileName());
                video.setTipo(event.getFile().getContentType());
            }
        } catch (IOException ex) {
            Logger.getLogger(VideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

}
