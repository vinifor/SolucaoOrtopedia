/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.controller;

import br.com.moacir.ortopedia.model.Medico;
import br.com.moacir.ortopedia.model.VideoAula;
import br.com.moacir.ortopedia.repository.MedicoRepository;
import br.com.moacir.ortopedia.repository.VideoAulaRepository;
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
@Component(value = "videoAulaController")
public class VideoAulaController {

    @Autowired
    private VideoAulaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    private VideoAula videoAula;
    private List<VideoAula> videoAulas;

    public void abrir() {
        carregar();
        Util.redirect(Paginas.VIDEO_AULA_LISTA);
    }

    public void carregar() {
        videoAulas = repository.findAll();
    }

    public void novo() {
        videoAula = VideoAula.builder()
                .build();
        Util.redirect(Paginas.VIDEO_AULA_EDITA);
    }

    public void edita(VideoAula videoAula) {
        this.videoAula = videoAula;
        Util.redirect(Paginas.VIDEO_AULA_EDITA);
    }

    public void salva() {
        repository.save(videoAula);
        cancela();
    }

    public void deleta(VideoAula videoAula) {
        repository.delete(videoAula);
        carregar();
    }

    public void cancela() {
        videoAula = null;
        carregar();
        Util.redirect(Paginas.VIDEO_AULA_LISTA);
    }

    public List<Medico> medicos(String filter) {
        return medicoRepository.findByNomeStartsWithIgnoreCase(filter);
    }

    public void upload(FileUploadEvent event) {
        System.out.println(event.getFile().getContentType());
        System.out.println(event.getFile().getFileName());
        File folder = new File(System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "webapp"
                + File.separator + "resources"
                + File.separator + "serenity-layout"
                + File.separator + "videos"
                + File.separator + "aula");
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
            Logger.getLogger(VideoAulaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public VideoAula getVideoAula() {
        return videoAula;
    }

    public void setVideoAula(VideoAula videoAula) {
        this.videoAula = videoAula;
    }

    public List<VideoAula> getVideoAulas() {
        return videoAulas;
    }

    public void setVideoAulas(List<VideoAula> videoAulas) {
        this.videoAulas = videoAulas;
    }

}
