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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author camila
 */

@Entity
@Table(name = "curso")
public class Curso implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_curso", sequenceName = "seq_curso_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_curso",strategy = GenerationType.SEQUENCE)
    private Integer id; 
    
    @NotNull(message = "O nome do curso não pode ser nulo")
    @NotBlank(message = "O nome do curso não pode estar em branco")
    @Length(max = 200, message = "O nome do curso não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 30, nullable = false) 
    private String nome; 
    
    @NotNull(message = "A sigla do curso não pode ser nula")
    @NotBlank(message = "A sigla do curso não pode estar em branco")
    @Length(max = 20, message = "A sigla do curso não pode ter mais que {max} caracteres")
    @Column(name = "sigla", length = 30, nullable = false) 
    private String sigla; 
    
    
    @NotBlank(message = "A descricao do curso não pode estar em branco")
    @Column(name = "descricao", length = 30, nullable = false) 
    private String descricao; 
    
    
    private Boolean ativo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "inicioAtividades")
    private Calendar inicioAtividades;
    
    //Mapeamento de colecoes , uma CURSO contém uma colecao de DISCIPLINAS
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas = new ArrayList<>();
    
    
    @ManyToOne // mapeamento muitos (CURSO) para um (INSTITUICAO)
    @NotNull(message = "A instituicao deve ser informada")
    @JoinColumn(name = "instituicao", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_curso_instituicao")  
    private Instituicao instituicao;
    
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Calendar getInicioAtividades() {
        return inicioAtividades;
    }

    public void setInicioAtividades(Calendar inicioAtividades) {
        this.inicioAtividades = inicioAtividades;
    }
    
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    
    public  void addDisciplinas(Disciplina obj){
        obj.setCurso(this);
        this.disciplinas.add(obj);
    }
    
    
     public  void removerDisciplinas(int index){
        this.disciplinas.remove(index);
     }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
      
    public Curso() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
   
    
}
