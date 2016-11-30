/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import model.Comment;
import model.DBHandler;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author greatwmc
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    static DBHandler db;

    @BeforeClass
    public static void setUpClass() {
        DBHandler.createTables();
        db = new DBHandler();

    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        db.close();
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
    // @Test
    // public void hello() {}
    @Test
    public void getNameFromUserTable()
    {
        assertTrue("name".equals(db.getAuthorName(1)));
    }
}
