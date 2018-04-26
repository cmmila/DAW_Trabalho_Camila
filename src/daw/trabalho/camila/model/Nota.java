/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author camila
 */
@Entity
@Table(name = "nota")
public class Nota implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_nota", sequenceName = "seq_nota_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_nota",strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Min(value = 0, message = "A primeira nota não pode ser negativa")
    @NotNull(message = "A primeira nota deve ser informada")
    @Column(name = "nota_01", nullable = false, columnDefinition = "decimal(10,2)")
    private Double nota01;
    
    @Min(value = 0, message = "A segunda nota não pode ser negativa")
    @NotNull(message = "A segunda nota deve ser informada")
    @Column(name = "nota_02", nullable = false, columnDefinition = "decimal(10,2)")
    private Double nota02;
    
    @Min(value = 0, message = "A media não pode ser negativa")
    @Column(name = "media", nullable = false, columnDefinition = "decimal(10,2)")
    private Double media;

    @ManyToOne // mapeamento muitos(NOTAS) para um (ALUNO) / varias notas podem ter um aluno
    @NotNull(message = "O aluno deve ser informado")
    @JoinColumn(name = "aluno", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_nota_aluno_id")
    private Aluno aluno; 
    
    
    @ManyToOne // mapeamento muitos(NOTAS) para um (DISCIPLINA) / varias notas podem ter uma disciplina
    @NotNull(message = "A disciplina deve ser informada")
    @JoinColumn(name = "disciplina_id", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_nota_disciplina")
    private Disciplina disciplina;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota01() {
        return nota01;
    }

    public void setNota01(Double nota01) {
        this.nota01 = nota01;
    }

    public Double getNota02() {
        return nota02;
    }

    public void setNota02(Double nota02) {
        this.nota02 = nota02;
    }

    public Double getMedia() {
        return media;
    }

    public Double setMedia(Double nota01, Double nota02) {
        
        return calculaMedia(nota01, nota02);
    }

   
    
    public Double calculaMedia (Double nota01, Double nota02)
    {
        //media = ((nota01 * 10 + nota02 *10) / 2)/10;
        media = ((nota01 + nota02 )  / 2);
        return media;
    }

    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
       
    public Nota() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nota other = (Nota) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
      
}
