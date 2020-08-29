/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.VideoInformativo;
import br.com.moacir.ortopedia.repository.VideoInformativoRepository;
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
@Component(value = "videoInformativoController")
public class VideoInformativoController {

    @Autowired
    private VideoInformativoRepository repository;

    private VideoInformativo videoInformativo;
    private List<VideoInformativo> videoInformativos;

    public void abrir() {
        carregar();
        Util.redirect(Paginas.VIDEO_INFORMATIVO_LISTA);
    }

    public void carregar() {
        videoInformativos = repository.findAll();
    }

    public void novo() {
        videoInformativo = VideoInformativo.builder()
                .build();
        Util.redirect(Paginas.VIDEO_INFORMATIVO_EDITA);
    }

    public void edita(VideoInformativo videoInformativo) {
        this.videoInformativo = videoInformativo;
        Util.redirect(Paginas.VIDEO_INFORMATIVO_EDITA);
    }

    public void salva() {
        repository.save(videoInformativo);
        cancela();
    }

    public void deleta(VideoInformativo videoInformativo) {
        repository.delete(videoInformativo);
        carregar();
    }

    public void cancela() {
        videoInformativo = null;
        carregar();
        Util.redirect(Paginas.VIDEO_INFORMATIVO_LISTA);
    }

    public void upload(FileUploadEvent event) {
        File folder = new File(System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "webapp"
                + File.separator + "resources"
                + File.separator + "serenity-layout"
                + File.separator + "videos"
                + File.separator + "informativo");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try ( InputStream is = event.getFile().getInputStream()) {
            try ( OutputStream os = new FileOutputStream(folder.getAbsolutePath() + File.separator + event.getFile().getFileName())) {
                byte buf[] = new byte[1024];
                int len;
                while ((len = is.read(buf)) > 0) {
                    os.write(buf, 0, len);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(VideoInformativoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public VideoInformativo getVideoInformativo() {
        return videoInformativo;
    }

    public void setVideoInformativo(VideoInformativo videoInformativo) {
        this.videoInformativo = videoInformativo;
    }

    public List<VideoInformativo> getVideoInformativos() {
        return videoInformativos;
    }

    public void setVideoInformativos(List<VideoInformativo> videoInformativos) {
        this.videoInformativos = videoInformativos;
    }

}
