package donnee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Mouton;

public class MoutonDAO {
	
	private List<Mouton> simulerListerMoutons()
	{
		List listeMoutonsTest = new ArrayList<Mouton>();
		listeMoutonsTest.add(new Mouton("Dolly", "Grise", "20 kg", "5 juin 2015"));
		listeMoutonsTest.add(new Mouton("Molly", "Rousse", "20 kg", "5 mai 2016"));
		listeMoutonsTest.add(new Mouton("Arthurus", "Noire", "20 kg", "5 mars 2017"));
		listeMoutonsTest.add(new Mouton("Cheese", "Jaune", "20 kg", "5 septembre 2015"));
		return listeMoutonsTest;
	}
	public List<Mouton> listerMoutons()
	{
		
		String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
		String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/bergerie";
		String BASEDEDONNEES_USAGER = "postgres";
		String BASEDEDONNEES_MOTDEPASSE = "test";
		
		try {
			Class.forName(BASEDEDONNEES_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Mouton> listeMoutons =  new ArrayList<Mouton>();
		try {
			Connection connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
			
			Statement requeteListeMoutons = connection.createStatement();
			ResultSet curseurListeMoutons = requeteListeMoutons.executeQuery("SELECT * FROM mouton");
			while(curseurListeMoutons.next())
			{
				String nom = curseurListeMoutons.getString("nom");
				String couleur = curseurListeMoutons.getString("couleur");
				String poids = curseurListeMoutons.getString("poids");
				String naissance = curseurListeMoutons.getString("naissance");
				System.out.println("Mouton " + nom + " n�e le " + naissance + " : " + poids + "kg " + couleur);
				Mouton mouton = new Mouton(nom, couleur, poids, naissance);
				listeMoutons.add(mouton);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//return this.simulerListerMoutons();
		return listeMoutons;
	}
}