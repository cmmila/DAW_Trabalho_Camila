/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daw.trabalho.camila.jpa.EntityManagerUtil;
import daw.trabalho.camila.model.Aluno;
import java.util.Calendar;
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
public class TestePersistirAluno {
    
    EntityManager em;
    
    public TestePersistirAluno() {
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
            
            Aluno a = new Aluno();
            a.setNome("Camila");
            a.setEmail("camila_cmarques@hotmail.com");
            a.setNascimento(Calendar.getInstance());
            
            Aluno a2 = new Aluno();
            a.setNome("Mauricio");
            a.setEmail("mauriciokarrei@hotmail.com");
            a.setNascimento(Calendar.getInstance());
            
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            
        } catch (Exception e){
            exception = true;
            e.printStackTrace();
        }
        // aqui comparo o resultado esperado com oque ocorreu
        Assert.assertEquals(false, exception);
        
    }
}
