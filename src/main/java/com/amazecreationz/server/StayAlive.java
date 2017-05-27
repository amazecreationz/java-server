package com.amazecreationz.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "API to keep Openshift Catridge in working state.", urlPatterns = { "/stayAlive" })
public class StayAlive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StayAlive() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "https://mail-amazecreationz.rhcloud.com/stayAlive";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		response.getWriter().append("Called with response code: ").append(Integer.toString(con.getResponseCode()));
	}

}
