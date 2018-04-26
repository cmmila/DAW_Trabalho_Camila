/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author camila
 */
@Entity
@Table(name = "instituicao")

public class Instituicao implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_instituicao", sequenceName = "seq_instituicao_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_instituicao",strategy = GenerationType.SEQUENCE)
    private Integer id; 
    
    @NotNull(message = "O nome da Instituicao não pode ser nulo")
    @NotBlank(message = "O nome da Instituicao não pode estar em branco")
    @Length(max = 50, message = "O nome da Instituicao não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 30, nullable = false)
    private String nome; 
    
    @Temporal(TemporalType.DATE)
    @Column(name = "ano_fundacao")
    private Calendar anoFundacao;
       

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(Calendar anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public Instituicao() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Instituicao other = (Instituicao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
