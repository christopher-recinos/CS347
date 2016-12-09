/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.NotesImageFile;
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
public class NewEmptyJUnitTest3 {
    
    public NewEmptyJUnitTest3() {
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
    public void testSplitPathName() {
        String path  = "/Users/greatwmc/NetBeansProjects/CS347/JMU-Share/uploads/2016-50-08-20-50-091597042521IMG_0956.JPG";
        String res = NotesImageFile.splitTheFilePathOnLastSlashToGetTheName(path);
        assertEquals(res, "2016-50-08-20-50-091597042521IMG_0956.JPG");
    }
    
}
