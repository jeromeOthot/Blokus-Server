import java.net.*;  //Pour les sockets
import java.io.*;   //Pour les buffer reader et writter
import java.text.*;
import java.util.*;


public class Joueur
{
	public static final int NB_CATEGORIE = 4;
   // private BD laConnexion_;
    private Socket ConnexionJoueur_;
    private String nomDuJoueur_ = "";
    private int NbQuestionReussi[] = new int[NB_CATEGORIE]; //Case 0= rouge  1= vert  2= bleu  3= jaune
    private int NbQuestionRater[] = new int[NB_CATEGORIE]; //Case 0= rouge  1= vert  2= bleu  3= jaune
    private boolean PointObtenu[] = new boolean[NB_CATEGORIE]; //Case 0= rouge  1= vert  2= bleu  3= jaune
    public BufferedReader Reader_;
    public PrintWriter Writer_;

	//Constructeur paramétrique qui initialise les variables Client et Repertoire
	Joueur( Socket UneConnexion, String NomJoueur, int NoConnexion )
	{
	   try
	   {
         ConnexionJoueur_ = UneConnexion;
         nomDuJoueur_ = NomJoueur;
         System.out.println( NomJoueur + " vient de se connecter au serveur");
         Reader_ = new BufferedReader( new InputStreamReader( ConnexionJoueur_.getInputStream() ) );
         Writer_ = new PrintWriter( new OutputStreamWriter(ConnexionJoueur_.getOutputStream()), true );

         // Connexion a la base de données.
         /*
         laConnexion_ = new BD();
         laConnexion_.SetConnection("othotjer", "oracle", "172.17.200.251", "1521", "orcl");
         laConnexion_.Connecter();
         laConnexion_.GetConnection();
         laConnexion_.AjouterJoueur(NoConnexion, NomJoueur, 300, 300); */
        // System.out.println(laConnexion_.GetMessage());
	   }
	   catch (IOException ioe)
	   {
	     System.err.println(ioe);
	   }
	}

   public void setNom(String nomJoueur)
   {
      nomDuJoueur_ = nomJoueur;
   }

   public String getNomJoueur()
   {
      return nomDuJoueur_;
   }

   public void setConnection(Socket Serveur)
   {
      ConnexionJoueur_ = Serveur;
   }

   public Socket getConnection()
   {
      return ConnexionJoueur_;
   }

   /*
   public void DeconnexionJoueur()
   {
      NbConnexions_--;
      System.out.println( nomDuJoueur_ + " est déconnecter");
   }*/

}