import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;

public class Repo {
	//instance variables
	private URL url;
	private String link;
	private String title;
	private String user;

	int responseCode = 0;

	//constructor
	//I was thinking about adding multiple to support custom names or titles, but I don't people want to waste time doing that
	public Repo(String linkIn) {
		//set the link to the input link
		link = linkIn;

		checkLink();

		//get the name and title based on the link
		user = link.split("/")[3];
		title = link.split("/")[4].split("\\.")[0];
	}

	//check to see if the link is a valid git repository
	private void checkLink() {
		//check whether the link is null or empty
		if (link == null || link.isBlank()) {
			throw new IllegalArgumentException("Link string cannot be null or empty");
		}

		//In the future we might want to expand this to allow for more variations of git repositories
		//check whether the link goes to a git repository
		//https://github.com/AaronMMcfailLuttrell/RankingSystemFinalProject
		if (link.endsWith(".git")) {
			this.link = link;
		} else {
			link = link + ".git";
		}

		//create a URL object from the link
		try {
			url = URI.create(link).toURL();
		} catch (MalformedURLException e) {
			throw new RuntimeException("Invalid URL");
		}

		//check whether the URL is a valid URL
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
	}

	public void downloadRepo() {
		//download the repository
		try {
			//create a process builder to run the git clone command and download the repository to the repos folder under the title of the repository
			ProcessBuilder builder = new ProcessBuilder("git", "clone", link, "repos/" + title);
			builder.redirectErrorStream(true);
			//start the process
			Process process = builder.start();
			//program waits for our process to finish
			process.waitFor();
		} catch (Exception e) {
			throw new RuntimeException("Unable to download the repository");
		}
	}

	//getters
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

