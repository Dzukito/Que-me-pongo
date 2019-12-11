package ar.utn.dds.server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Spark.port(9091);
		Router.init();
		DebugScreen.enableDebugScreen();
	}
}
