/*
 * Copyright 2020 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.moacir.ortopedia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class VideoInformativo extends Video {

    @Column
    private Boolean status;

    @lombok.Builder(builderClassName = "Builder")
    public VideoInformativo(Boolean status, Long id, String titulo, String tipo, String qualificacao, String nomeArquivo) {
        super(id, titulo, tipo, qualificacao, nomeArquivo);
        this.status = status;
    }

}
