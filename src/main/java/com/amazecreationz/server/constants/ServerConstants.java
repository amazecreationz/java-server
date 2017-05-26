package com.amazecreationz.server.constants;

public interface ServerConstants {
	String projectId = "amazecreationz-web";
	String DATA_DIR = System.getenv("OPENSHIFT_DATA_DIR");
	String TMP_DIR = DATA_DIR+"/tmp";
	String serviceAccountJSON = DATA_DIR +"/service-account.json";
	String BUCKET_URL = "amazecreationz-web.appspot.com";
}
