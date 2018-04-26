/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author camila
 */

@Entity
@Table(name = "aluno")
@Inheritance(strategy = InheritanceType.JOINED) //heranca 
public class Aluno implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_aluno", sequenceName = "seq_aluno_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_aluno",strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "O nome do aluno não pode ser nulo")
    @NotBlank(message = "O nome do aluno não pode estar em branco")
    @Length(max = 30, message = "O nome não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 30, nullable = false) 
    private String nome;
    
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode estar em branco")
    @Length(max = 60, message = "O email não pode ter mais que {max} caracteres")
    @Column(name = "email", length = 30, nullable = false) 
    private String email; 
    
    @Temporal(TemporalType.DATE)
    @Column(name = "nascimento")
    private Calendar nascimento;
    
    
    //nova tabela aluno_disciplina, mapemento muitos (ALUNO) para muitos(DISCIPLINA)
    //um aluno pode ter muitas disciplinas
    @ManyToMany
    @JoinTable(name = "aluno_disciplina", joinColumns = 
            @JoinColumn(name = "aluno", referencedColumnName = "id", nullable = false),//nome da tabela
            inverseJoinColumns = 
                    @JoinColumn(name = "disciplina", referencedColumnName = "id", nullable = false)) //classe da lista 
    
    //lista de disciplinas         
    List<Disciplina> lista_disciplinas = new ArrayList<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public List<Disciplina> getLista_disciplinas() {
        return lista_disciplinas;
    }

    public void setLista_disciplinas(List<Disciplina> lista_disciplinas) {
        this.lista_disciplinas = lista_disciplinas;
    }
    

    public Aluno() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
