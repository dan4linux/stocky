/**
 * 
 */
package org.swansonstuff.stocky;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dan
 *
 */
public class Stocky {

	private static final Logger log = LoggerFactory.getLogger(Stocky.class);


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Document doc = null;

		try {
			// Hit this URL to authenticate
			Connection loginConn = Jsoup.connect("http://");
			loginConn.header("Accept-Encoding", "");
			loginConn.get();

			fetch("https://api.robinhood.com/oauth2/token/", {"credentials":"omit","headers":{},"referrer":"https://robinhood.com/login","referrerPolicy":"no-referrer-when-downgrade","body":"{\"grant_type\":\"password\",\"scope\":\"internal\",\"client_id\":\"c82SH0WZOsabOXGP2sxqcj34FxkvfnWRZBKlBjFS\",\"expires_in\":86400,\"password\":\"mypss\",\"username\":\"myuser\"}","method":"POST","mode":"cors"});
			doc = Jsoup.connect("http://")
					.header("Accept-Encoding", "")
					.cookies(loginConn.response().cookies())
					.data("interval","daily")
					.post();
		} catch(Exception e) {
			log.error("Error getting report: {}", e.getMessage());
		}

		if (doc != null) {
			// Create field map
			Map<String, Integer> fieldMap = new HashMap<>();
			int fieldCount = 0;
			for (Element header : doc.select("#reporttable thead tr th")) {
				fieldMap.put(header.text(), fieldCount++);
			}
		}

	}
	
	
	public void fetch(String url, Object postObj){
		
	}
}