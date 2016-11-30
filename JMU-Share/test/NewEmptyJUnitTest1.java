/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ajax.UpVoting;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DBHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author greatwmc
 */
public class NewEmptyJUnitTest1 {
    
    public NewEmptyJUnitTest1() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void increaseScore() 
    {
        DBHandler db = new DBHandler();
        try {
            assertEquals(6, db.increaseScore(5));
        } catch (SQLException ex) {
            Logger.getLogger(NewEmptyJUnitTest1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
