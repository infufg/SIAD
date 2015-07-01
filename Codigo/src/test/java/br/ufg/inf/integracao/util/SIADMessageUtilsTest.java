package br.ufg.inf.integracao.util;

import br.ufg.inf.integracao.domain.SIADMessage;
import br.ufg.inf.integracao.exception.InvalidPayloadException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class SIADMessageUtilsTest {

    @Test
    public void testConvertJSONToSIADMessage() throws Exception {

        String jSonTeste = "{\n" +
                "  \"sender\": \"felipe\",\n" +
                "  \"receivers\": [\n" +
                "    \"mauro\",\n" +
                "    \"sergio\"\n" +
                "  ],\n" +
                "  \"content\": {\n" +
                "    \"chave\": \"valor\"\n" +
                "  }\n" +
                "}";

        SIADMessage message = SIADMessageUtils.convertJSONToSIADMessage(jSonTeste);

        assertTrue(message.getSender().equals("felipe"));
    }

    @Test(expected = InvalidPayloadException.class)
    public void testConvertJSONToSIADMessage_InvalidPayloadExcep() throws Exception {
        String jSonTeste = "{\n" +
                "  \"sender\": \"felipe\",\n" +
                "  \"content\": {\n" +
                "    \"chave\": \"valor\"\n" +
                "  }\n" +
                "}";

        SIADMessageUtils.convertJSONToSIADMessage(jSonTeste);
    }

    @Test
    public void testConvertSIADMessageToSingleRecipientJSON() throws Exception {

        String jSonTeste = "{\n" +
                "  \"sender\": \"felipe\",\n" +
                "  \"receivers\": [\n" +
                "    \"mauro\",\n" +
                "    \"sergio\"\n" +
                "  ],\n" +
                "  \"content\": {\n" +
                "    \"chave\": \"valor\"\n" +
                "  }\n" +
                "}";

        SIADMessage message = SIADMessageUtils.convertJSONToSIADMessage(jSonTeste);

        Map<String, JSONObject> jsonPerRecipient = SIADMessageUtils.convertSIADMessageToSingleRecipientJSON(message);

        boolean receiverMauro = jsonPerRecipient.containsKey("mauro");
        boolean receiverSergio = jsonPerRecipient.containsKey("sergio");

        assertTrue(receiverMauro && receiverSergio);
    }
}