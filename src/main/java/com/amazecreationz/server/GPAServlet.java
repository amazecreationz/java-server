package com.amazecreationz.server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazecreationz.gpa.services.NITCService;
import com.amazecreationz.server.constants.ServerConstants;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class GPAServlet
 */
@WebServlet(description = "Servlet for GPA Calculator.", urlPatterns = { "/gpa" })
public class GPAServlet extends HttpServlet implements ServerConstants {
	private static final long serialVersionUID = 1L;
       
    public GPAServlet() {
        super();
    }
    
	@SuppressWarnings("null")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Storage storage = StorageOptions.newBuilder()
				.setProjectId(projectId)
				.setCredentials(GoogleCredentials.fromStream(new FileInputStream(serviceAccountJSON)))
				.build()
				.getService();
		String userId = request.getParameter("userId");
		String fileName = userId +".pdf";
		String outputFileName = TMP_DIR +"/"+ fileName;
		Blob blob = storage.get(BUCKET_URL, "appData/gradecards/" +fileName);
		JsonObject output = null;
		if(blob !=null) {
			ReadChannel readChannel = blob.reader();
			FileOutputStream fileOuputStream = new FileOutputStream(outputFileName);
			fileOuputStream.getChannel().transferFrom(readChannel, 0, Long.MAX_VALUE);
			fileOuputStream.close();
			output.add("studentData", new NITCService(outputFileName).process().getAsJSON());
		}
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		response.getWriter().append(output.getAsString());
	}
}
