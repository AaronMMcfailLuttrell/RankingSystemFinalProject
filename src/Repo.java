import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;

public class Repo {
	private URL url;
	private String link;
	private String title;
	private String user;

	int responseCode = 0;

	public Repo(String link) {
		//check whether the link is null or empty
		if (link == null || link.isBlank()) {
			throw new IllegalArgumentException("Link string cannot be null or empty");
		}

		//check whether the link goes to a git repository
		//https://github.com/AaronMMcfailLuttrell/RankingSystemFinalProject
		if (link.endsWith(".git")) {
			this.link = link;
		} else {
			link = link + ".git";
		}

		try {
			url = URI.create(link).toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL");
		}

		try {
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			responseCode = connection.getResponseCode();

			if (responseCode != HttpsURLConnection.HTTP_OK) {
				throw new RuntimeException("Unable to connect to the URL");
			}

		} catch (Exception e) {
			throw new RuntimeException("Unable to connect to the URL");
		}

		//get the name based on web-scraping the link
		user = link.split("/")[3];
		title = link.split("/")[4];

	}

	public String getLink() {
		return link;
	}

	public String getTitle() {
		return title;
	}

	public String getUser() {
		return user;
	}

	public int getResponseCode() {
		return responseCode;
	}
}

