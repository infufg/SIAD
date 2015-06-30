package br.ufg.inf.integracao.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Felipe on 29/06/2015.
 */
public class SIADRegistrarServiceTest {

    @Test
    public void testGetInstance() throws Exception {
        SIADRegistrarService testeInstancia= SIADRegistrarService.getInstance();
        if(testeInstancia instanceof SIADRegistrarService){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    @Test
    public void testGetUsers() throws Exception {

    }

    @Test
    public void testRegisterUser() throws Exception {

    }

    @Test
    public void testUnregisterUser() throws Exception {

    }

    @Test
    public void testGetAddressForAlias() throws Exception {

    }
}