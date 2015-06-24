package br.ufg.inf.integracao.main;

import br.ufg.inf.integracao.handler.SIADMessageReceiverHandler;
import br.ufg.inf.integracao.handler.SIADRegisterUserHandler;
import br.ufg.inf.integracao.handler.SIADUnregisterUserHandler;
import br.ufg.inf.integracao.service.SIADRegistrarService;
import br.ufg.inf.integracao.service.SIADSenderService;
import org.apache.http.ExceptionLogger;
import org.apache.http.impl.nio.bootstrap.ServerBootstrap;
import org.apache.http.impl.nio.bootstrap.HttpServer;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static br.ufg.inf.integracao.util.SIADDefaults.NEW_MESSAGE_ENDPOINT;
import static br.ufg.inf.integracao.util.SIADDefaults.NEW_USER_ENDPOINT;
import static br.ufg.inf.integracao.util.SIADDefaults.DELETE_USER_ENDPOINT;
import static br.ufg.inf.integracao.util.SIADDefaults.SIAD_SERVER_STRING;

public class SIADServer {

	private static final Logger logger = Logger.getLogger(SIADServer.class.getName());


	public static void main(String[] args) throws Exception {

		logger.info("Preparing SIADServer...");
		SIADSenderService.getInstance().start();

		int port = 8080;
		final HttpServer server = ServerBootstrap.bootstrap()
				.setListenerPort(port)
				.setServerInfo(SIAD_SERVER_STRING)
				.setExceptionLogger(ExceptionLogger.STD_ERR)
				.registerHandler(NEW_MESSAGE_ENDPOINT, new SIADMessageReceiverHandler())
				.registerHandler(NEW_USER_ENDPOINT, new SIADRegisterUserHandler())
				.registerHandler(DELETE_USER_ENDPOINT, new SIADUnregisterUserHandler())
				.create();

		server.start();
		server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

		logger.info("Endpoint for new messages: " + NEW_MESSAGE_ENDPOINT);
		logger.info("Endpoint for new users: " + NEW_USER_ENDPOINT);
		logger.info("Endpoint for deleting users: " + DELETE_USER_ENDPOINT);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				logger.info("Stopping SIADServer...");
				SIADSenderService.getInstance().requestStop();
				server.shutdown(5, TimeUnit.SECONDS);
			}
		});
	}
}
