/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daw.trabalho.camila.jpa.EntityManagerUtil;
import daw.trabalho.camila.model.Aluno;
import daw.trabalho.camila.model.Disciplina;
import daw.trabalho.camila.model.Nota;
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
public class TestePersistirNota {
    
    EntityManager em;
    
    public TestePersistirNota() {
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
            
            Nota n = new Nota();
            n.setNota01(8.0);
            n.setNota02(7.5);
            n.setDisciplina(em.find(Disciplina.class, 8));
            n.setAluno(em.find(Aluno.class, 1));
            n.setMedia(8.00 ,7.50);
           
            em.getTransaction().begin();
            em.persist(n);
            em.getTransaction().commit();
            
        } catch (Exception e){
            exception = true;
            e.printStackTrace();
        }
        // aqui comparo o resultado esperado com oque ocorreu
        Assert.assertEquals(false, exception);
        
    }
}
