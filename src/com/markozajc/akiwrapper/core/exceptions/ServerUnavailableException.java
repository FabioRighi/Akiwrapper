package com.markozajc.akiwrapper.core.exceptions;

import java.util.Collection;
import java.util.stream.Collectors;

import com.markozajc.akiwrapper.core.entities.Server;
import com.markozajc.akiwrapper.core.entities.impl.immutable.StatusImpl;

/**
 * An exception representing that the currently used {@link Server} has gone
 * offline.
 */
public class ServerUnavailableException extends StatusException {

	private String serverUrl;

	/**
	 * Creates a new {@link ServerUnavailableException} instance for a single
	 * server.
	 * 
	 * @param server
	 */
	public ServerUnavailableException(Server server) {
		super(new StatusImpl("KO - SERVER DOWN"));
		this.serverUrl = server.getBaseUrl();
	}

	/**
	 * Creates a new {@link ServerUnavailableException} instance for multiple
	 * servers.
	 * 
	 * @param servers
	 */
	public ServerUnavailableException(Collection<Server> servers) {
		super(new StatusImpl("KO - SERVER DOWN"));
		this.serverUrl = servers.stream().map(Server::getBaseUrl).collect(Collectors.joining(", "));
	}

	/**
	 * Returns the URL of the API server that went down
	 * 
	 * @return API server's URL
	 */
	public String getServerUrl() {
		return serverUrl;
	}

}
