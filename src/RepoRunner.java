public class RepoRunner {
	public static void main(String[] args) {
		Repo repo = new Repo("https://github.com/AaronMMcfailLuttrell/RankingSystemFinalProject");

		System.out.println(repo.getLink());
		System.out.println(repo.getTitle());
		System.out.println(repo.getUser());
		System.out.println(repo.getResponseCode());
	}
}
