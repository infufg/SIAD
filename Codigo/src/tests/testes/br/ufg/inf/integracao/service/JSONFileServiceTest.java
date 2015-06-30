package br.ufg.inf.integracao.service;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Felipe on 29/06/2015.
 */
public class JSONFileServiceTest {

    @Test
    public void testGetInstance() throws Exception {
        JSONFileService testeInstancia= JSONFileService.getInstance();
        if(testeInstancia instanceof JSONFileService){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    @Test
    public void testGetFolderPathForReceiver() throws Exception {
        JSONFileService testeInstancia= JSONFileService.getInstance();
        String testePasta = testeInstancia.getFolderPathForReceiver("Destinatario");

        if(testePasta==null){
            assertTrue(false);
        }else if(testePasta.length()<=0){
            assertTrue(false);
        }else{
            assertTrue(true);
        }
    }

    @Test
    public void testGetFolderPathForData() throws Exception {
        JSONFileService testeInstancia= JSONFileService.getInstance();
        String testePasta = testeInstancia.getFolderPathForData();

        if(testePasta==null){
            assertTrue(false);
        }else if(testePasta.length()<=0){
            assertTrue(false);
        }else{
            assertTrue(true);
        }
    }

    @Test
    public void testSaveSingleMessageJSONObjectToFile() throws Exception {

    }

    @Test
    public void testSaveDataJSONObjectToFile() throws Exception {

    }

    @Test
    public void testReadDataJSONObjectFromFile() throws Exception {

    }
}