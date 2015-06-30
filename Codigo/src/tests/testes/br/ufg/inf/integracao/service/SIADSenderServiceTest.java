package br.ufg.inf.integracao.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Felipe on 29/06/2015.
 */
public class SIADSenderServiceTest {

    @Test
    public void testGetInstance() throws Exception {
        SIADSenderService testeInstancia= SIADSenderService.getInstance();
        if(testeInstancia instanceof SIADSenderService){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    @Test
    public void testMarkAsFinished() throws Exception {

    }

    @Test
    public void testRequestStop() throws Exception {

    }
}