package br.ufg.inf.integracao.util;

import br.ufg.inf.integracao.exception.DuplicateUserException;
import br.ufg.inf.integracao.exception.InvalidUserException;
import br.ufg.inf.integracao.service.SIADRegistrarService;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static br.ufg.inf.integracao.util.SIADDefaults.SIAD_TIMESTAMP_FORMAT;
import static org.junit.Assert.*;

public class UserUtilsTest {

    @Test
    public void testRegisterUserFromJSON() throws Exception {

        String aliasData = "Alias" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String enderecoData = "Endereço" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String jSonTeste = "{\n" +
                "            \"alias\": \"" + aliasData + "\",\n" +
                "                \"address\": \"" + enderecoData + "\"\n" +
                "        }";
        UserUtils.registerUserFromJSON(jSonTeste);

        String testeEndereco = SIADRegistrarService.getInstance().getAddressForAlias(aliasData);

        assertTrue(testeEndereco.equals(enderecoData));
    }

    @Test(expected = DuplicateUserException.class)
    public void testRegisterUserFromJSON_DuplicateUserExcep() throws Exception {

        String aliasData = "Alias" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String enderecoData = "Endereço" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String jSonTeste = "{\n" +
                "            \"alias\": \"" + aliasData + "\",\n" +
                "                \"address\": \"" + enderecoData + "\"\n" +
                "        }";
        UserUtils.registerUserFromJSON(jSonTeste);
        UserUtils.registerUserFromJSON(jSonTeste);

    }

    @Test(expected = InvalidParameterException.class)
    public void testRegisterUserFromJSON_InvalidParameterExcep() throws Exception {

        String aliasData = "Alias" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String enderecoData = "Endereço" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String jSonTeste = "{\n" +
                "            \"aliaz\": \"" + aliasData + "\",\n" +
                "                \"addresz\": \"" + enderecoData + "\"\n" +
                "        }";
        UserUtils.registerUserFromJSON(jSonTeste);

    }

    @Test(expected = InvalidUserException.class)
    public void testUnregisterUserFromJSON() throws Exception {

        String aliasData = "Alias" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String enderecoData = "Endereço" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String jSonTeste = "{\n" +
                "            \"alias\": \"" + aliasData + "\",\n" +
                "                \"address\": \"" + enderecoData + "\"\n" +
                "        }";
        UserUtils.registerUserFromJSON(jSonTeste);

        String jSonTeste2 = "{\n" +
                "            \"alias\": \"" + aliasData + "\",\n" +
                "        }";
        UserUtils.unregisterUserFromJSON(jSonTeste2);

        String testeEndereco = SIADRegistrarService.getInstance().getAddressForAlias(aliasData);

    }

    @Test(expected = InvalidParameterException.class)
    public void testUnregisterUserFromJSON_InvalidParameterExcep() throws Exception {
        String aliasData = "Alias" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String jSonTeste = "{\n" +
                "            \"aliaz\": \"" + aliasData + "\",\n" +
                "        }";
        UserUtils.unregisterUserFromJSON(jSonTeste);
    }

    @Test(expected = InvalidUserException.class)
    public void testUnregisterUserFromJSON_InvalidUserExcep() throws Exception {

        String aliasData = "Alias" + new SimpleDateFormat(SIAD_TIMESTAMP_FORMAT).format(new Date());
        String jSonTeste = "{\n" +
                "            \"alias\": \"" + aliasData + "\",\n" +
                "        }";
        UserUtils.unregisterUserFromJSON(jSonTeste);
    }
}