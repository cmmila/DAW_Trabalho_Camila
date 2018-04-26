/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author camila
 */

@Entity
@Table(name = "professor")
public class Professor extends Aluno implements Serializable {
    
    
    @NotBlank(message = "A titulacao não pode estar em branca")
    @Column(name = "titulacao", length = 30, nullable = false)
    private String titulacao;
    
   
    @NotBlank(message = "Os topicos de interesse nao podem  email estar em branco")
    @Column(name = "topicos_interesse", length = 30, nullable = false) 
    private String topicosInteresse;
    
    //mapeamento de muitos (PROFESSOR) para um (ESPECIALIDADE)
    // varios professores podem ter uma especialidade
    @ManyToOne
    @NotNull(message = "A especialidade deve ser informada")
    @JoinColumn(name = "especialidades", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_professor_especialidades")  
    private Especialidade especialidades;

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    public String getTopicosInteresse() {
        return topicosInteresse;
    }

    public void setTopicosInteresse(String topicosInteresse) {
        this.topicosInteresse = topicosInteresse;
    }
    
    public Especialidade getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(Especialidade especialidades) {
        this.especialidades = especialidades;
    }
    
    public Professor() {
    }
    
    //nao tem equals nem hashcode pq herda de aluno 

    
    
    
    
    
}
