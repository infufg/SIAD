package br.ufg.inf.integracao.exception;

public class DuplicateUserException extends Exception {
	public DuplicateUserException(String message) {
		super(message);
	}
}
