/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.trabalho.camila.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author camila
 */

@Entity
@Table(name = "disciplina")
public class Disciplina implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_disciplina", sequenceName = "seq_disciplina_id", 
            allocationSize = 1)
    @GeneratedValue(generator = "seq_disciplina", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    
    @NotBlank(message = "O nome da disciplina não pode estar em branco")
    @Length(max = 80, message = "O nome da disciplina não pode ter mais que {max} caracteres")
    @Column(name = "nome", length = 40, nullable = false) 
    private String nome;
    
    @NotNull(message = "A descricao não pode ser nula")
    @NotBlank(message = "A descricao não pode estar em branco")
    @Column(name = "descricao", length = 30, nullable = false) 
    private String descricao;
    
    @Min(value = 0, message = "A carga horaria não pode ser negativa")
    @NotNull(message = "A carga horaria deve ser informada")
    @Column(name = "carga_horaria", nullable = false, columnDefinition = "decimal(10,2)")
    private Double cargaHoraria;
    
    
    @Column(name = "conhecimentos_minimos", length = 30, nullable = false) 
    private String conhecimentosMinimos; 
    
    // Mapeamento muitos(ALUNO) para muitos (DISCIPLINA), cria-se uma nova tabela aluno_disciplina
    @ManyToMany
    @JoinTable(name = "aluno_disciplina", joinColumns = 
            @JoinColumn(name = "disciplina", referencedColumnName = "id", nullable = false),//nome da tabela
            inverseJoinColumns = 
                    @JoinColumn(name = "aluno", referencedColumnName = "id", nullable = false)) //classe da lista 
    
    //lista de alunos        
    List<Aluno> lista_alunos = new ArrayList<>();
    
    @ManyToOne // mapeamento muitos (DISCIPLINA) para um (CURSO) 
    //uma disciplina pode ter um curso 
    @NotNull(message = "O curso deve ser informado")
    @JoinColumn(name = "curso_id", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_disciplina_curso")
    private Curso curso;
    
    //Mapeamento de colecoes , uma DISCIPLINA contém uma colecao de NOTAS
    @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Nota> notas = new ArrayList<>();
    
    

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getConhecimentosMinimos() {
        return conhecimentosMinimos;
    }

    public void setConhecimentosMinimos(String conhecimentosMinimos) {
        this.conhecimentosMinimos = conhecimentosMinimos;
    }

    public List<Aluno> getLista_alunos() {
        return lista_alunos;
    }

    public void setLista_alunos(List<Aluno> lista_alunos) {
        this.lista_alunos = lista_alunos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
    
    public  void addNotas (Nota obj){
        obj.setDisciplina(this);
        this.notas.add(obj);
    }
    
    
     public  void removerNotas(int index){
        this.notas.remove(index);
     }
    
    public Disciplina() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Disciplina other = (Disciplina) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }       
    
}
