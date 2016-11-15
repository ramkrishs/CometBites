package edu.utdallas.cometbites;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by twinklesharma on 11/15/16.
 */
public class restClientTest {
    @Test
    public void getFoodJointsList() throws Exception {

        System.out.println(restClient.getItemsList("http://cometbites.com/uploads/panda.jpg"));

    }

}