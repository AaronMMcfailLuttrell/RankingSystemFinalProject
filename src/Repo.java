import javax.net.ssl.HttpsURLConnection;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URI;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayDeque;
import java.util.Deque;

public class Repo {
	//instance variables
	private URL url;
	private String link;
	private String title;
	private String user;
	private String repoPath;

	private int responseCode = 0;

	private boolean downloaded;

	//constructor
	//I was thinking about adding multiple to support custom names or titles, but I don't people want to waste time doing that
	public Repo(String linkIn) {
		//set the link to the input link
		link = linkIn;

		checkLink();

		//get the name and title based on the link
		user = link.split("/")[3];
		title = link.split("/")[4].split("\\.")[0];

		this.downloaded = false;
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

	//download the repository
	public void downloadRepo() {
		try {
			//create a process builder to run the git clone command and download the repository to the repos folder under the title of the repository
			repoPath = "./repos/" + title;
			ProcessBuilder builder = new ProcessBuilder("git", "clone", link, repoPath);
			builder.redirectErrorStream(true);
			//start the process
			Process process = builder.start();

			//check for errors
			localErrors(process);

			//program waits for our process to finish
			process.waitFor();

			//set a flag that the repo has been downloaded
			this.downloaded = true;
		} catch (Exception e) {
			throw new RuntimeException("Unable to download the repository");
		}
	}

	//compile the repository
	public void compileRepo() {
		try {
			//create a process builder to run the javac command and compile the repository
			ProcessBuilder builder = new ProcessBuilder("javac", "-d", repoPath + "/bin", repoPath + "/src/*.java");
			builder.redirectErrorStream(true);
			//start the process
			Process process = builder.start();

			//check for errors
			localErrors(process);

			//program waits for our process to finish
			process.waitFor();
		} catch (Exception e) {
			throw new RuntimeException("Unable to compile the repository");
		}
	}

	//THIS IS CURRENTLY SET TO ONLY RUN A MAIN CLASS
	//We can look into using regex or something to find runnable classes or have the user define the main class if an error occurs

	//run the repository
	public void runRepo() {
		try {
			//create a process builder to run the java command and run the repository
			ProcessBuilder builder = new ProcessBuilder("java", "-cp", repoPath + "/bin", "Main");
			builder.redirectErrorStream(true);
			//start the process
			Process process = builder.start();

			//check for errors
			localErrors(process);

			//program waits for our process to finish
			process.waitFor();
		} catch (Exception e) {
			throw new RuntimeException("Unable to run the repository");
		}
	}

	private void localErrors(Process p) throws IOException {
		//check for errors
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		while ((line = errorReader.readLine()) != null) {
			System.err.println(line);
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

	public boolean isDownloaded() {
		return this.downloaded;
	}

	public void structureProgram() {
		if (!this.downloaded) {
			downloadRepo();
		}
		compileRepo();
		runRepo();
	}

	public DefaultMutableTreeNode walkDirectory() {
		if (!this.downloaded) {
			downloadRepo();
		}

		DefaultMutableTreeNode fileNames = new DefaultMutableTreeNode();
		Deque<DefaultMutableTreeNode> dirStack = new ArrayDeque<DefaultMutableTreeNode>();

		dirStack.push(fileNames);

        try {
			Files.walkFileTree(Path.of(repoPath), new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					// Create a new node for the current directory
					DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(dir.getFileName());

					dirStack.peek().add(dirNode);
					dirStack.push(dirNode);

					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					// Create a node for the file
					DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode(file.getFileName().toString());

					dirStack.peek().add(fileNode);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
					dirStack.pop();
					return FileVisitResult.CONTINUE;
				}
			});

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

		return fileNames;
    }

}

