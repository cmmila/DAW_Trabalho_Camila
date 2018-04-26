/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daw.trabalho.camila.jpa.EntityManagerUtil;
import daw.trabalho.camila.model.Curso;
import daw.trabalho.camila.model.Disciplina;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author camila
 */
public class TestePersistirDisciplina {
    
    EntityManager em;
    
    public TestePersistirDisciplina() {
    }
    
    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    @After
    public void tearDown() {
        //System.out.println("erro"+ em);
        em.close();
    }
    
    @Test
    public void teste(){
        Boolean exception = false;
        System.out.println("erro"+ em);
        
        try {
            
            Disciplina d = new Disciplina();
            d.setNome("Desenvolvimento e Aplicacoes Web");
            d.setCargaHoraria(12.00);
            d.setDescricao("descricao disciplina teste ");
            d.setConhecimentosMinimos("java");
            d.setCurso(em.find(Curso.class, 4));
            
            
            
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
            
        } catch (Exception e){
            exception = true;
            e.printStackTrace();
        }
        // aqui comparo o resultado esperado com oque ocorreu
        Assert.assertEquals(false, exception);
        
    }
}
