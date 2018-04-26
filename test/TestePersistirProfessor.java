/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import daw.trabalho.camila.jpa.EntityManagerUtil;
import daw.trabalho.camila.model.Especialidade;
import daw.trabalho.camila.model.Professor;
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
public class TestePersistirProfessor {
    
    EntityManager em;
    
    public TestePersistirProfessor() {
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
            
            Professor p = new Professor();
            p.setNome("Joao da Silva");
            p.setEmail("joaodasilva@hotmail.com");
            p.setEspecialidades(em.find(Especialidade.class, 3));
            p.setTitulacao("Mestre");
            p.setTopicosInteresse("Testes Automatizados");
            
            Professor p2 = new Professor();
            p.setNome("Maria Torres");
            p.setEmail("mariatorres@hotmail.com");
            p.setEspecialidades(em.find(Especialidade.class, 2));
            p.setTitulacao("Mestre");
            p.setTopicosInteresse("Redes");
            
            Professor p3 = new Professor();
            p.setNome("Paulo ");
            p.setEmail("paulo@hotmail.com");
            p.setEspecialidades(em.find(Especialidade.class, 1));
            p.setTitulacao("Mestre");
            p.setTopicosInteresse("Sistemas Distribuidos");
            //p.setNascimento(Calendar.getInstance());
           
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            
        } catch (Exception e){
            exception = true;
            e.printStackTrace();
        }
        // aqui comparo o resultado esperado com oque ocorreu
        Assert.assertEquals(false, exception);
        
    }
}
