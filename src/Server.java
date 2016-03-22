import java.net.*;
import java.io.*;
import java.util.*;


import javax.swing.JOptionPane; //Pour les fenÍtres
//La d√©claration du Serveur de Serveur
public class Server
{
    public static final int PORT_DEFAUT = 9000;
    // le num√©ro de Port et le nb maximum de r√©pertoires par d√©faut
    private static int nbConnexion_ = 4;

     private static int NoPort_;
    private static String Adresse_;

    public static LinkedList<Joueur> ListeJoueur_;

    public Server()
    {
        ListeJoueur_ = new LinkedList<Joueur>();
    }

	public void setPort( int NoPort )
   {
      NoPort_ = NoPort;
   }

   public int getPort()
   {
      return NoPort_;
   }

   public void setAdresse( String Adresse )
   {
      Adresse_ = Adresse;
   }

   public String getAdresse()
   {
      return Adresse_;
   }

	public void setNbConnections( int nbConnexion )
	{
      nbConnexion_ = nbConnexion;
	}

   public int getNbConnections()
   {
      return nbConnexion_;
   }

   public static void AjoutJoueur(Joueur joueurs)
   {
      ListeJoueur_.add(joueurs);
   }

   public static void EnleverJoueur(Joueur joueurs)
   {
      ListeJoueur_.remove(joueurs);
   }

   public static LinkedList<Joueur> getJoueur()
   {
      return ListeJoueur_;
   }

	// D√©marrage du serveur J√©r√¥me Othot
   public void OuvrirServeur(int NoPort, String Adresse)
	{
      setPort(NoPort);
      setAdresse(Adresse);
	   System.out.println("Bonjour !");
		ServerSocket Serveur;
      //InetAddress AdresseServeur;
      try
      {
         //Threat qui attend kon pese sur Q pour terminer le programme
         InetAddress AdresseServeur = InetAddress.getByName(Adresse);
         System.out.println(AdresseServeur);
         Serveur = new ServerSocket( NoPort, 0, AdresseServeur );
         GestionConnexions oGestion = new GestionConnexions(this, Serveur);
         Thread ThreadGestionConnexions = new Thread( oGestion );
         ThreadGestionConnexions.start();

         while( ThreadGestionConnexions.isAlive() )
	     {
			    //Doit a voir 2 joueur de connecter pour commencer la partie
             if(ListeJoueur_.size() == 1)
             {
                 System.out.println("OK");
                 System.out.println(ListeJoueur_.get(0).getConnection());
                 //ListeJoueur_.get(0).Reader_.readLine();
                 ListeJoueur_.get(0).Writer_.println("GO!");
                 ListeJoueur_.get(0).Writer_.flush();
                 break;

                //On fait jouer les joueur chaqu'un leur tour
                /*
                for(int cptJoueur =0; ListeJoueur_.size() < 1; cptJoueur++)
                {

                   //ListeJoueur.get(0).Writter_.flush();
                }*/
             }
		}

		//Serveur.close();
	  	}
		catch( Exception ioe )
		{
			System.out.println( ioe );
			System.out.println("une erreur");
		}
	}

	//Mon super de beau MAIN adorer !!!
   public static void main( String[] args )
   {
      Server oServeur = new Server();

      //DÈclaration des variables
      int NoPort = PORT_DEFAUT;
      String Adresse = "127.0.0.1"; //Localhost

      try
      {
         Adresse = JOptionPane.showInputDialog( null, "Entrez l'adresse du serveur :" );
         NoPort = Integer.parseInt( JOptionPane.showInputDialog( null, "Entrez le no du port du serveur :" ));
         //VÈrifie si le port est valide
         if(NoPort > 0 && NoPort < 65535)
         {
            if(! Adresse.trim().equals("") || Adresse != null)
            {
               oServeur.OuvrirServeur(NoPort, Adresse);
            }
            else
            {
               JOptionPane.showMessageDialog( null,
                                                 "Votre adresse est invalide !",
                                                 "Adresse invalide",
                                                 JOptionPane.ERROR_MESSAGE );
            }
         }
         else
         {
            JOptionPane.showMessageDialog( null,
                                  "Le Numero du port est invalide, doit Ítre comprit entre 0 et 65535",
                                  "Numero de port invalide",
                                  JOptionPane.ERROR_MESSAGE );
         }
      }
      catch( NumberFormatException e )
      {
         JOptionPane.showMessageDialog( null,
                                  "Le Numero du port est invalide, doit Ítre comprit entre 0 et 65535",
                                  "Numero de port invalide",
                                  JOptionPane.ERROR_MESSAGE );
      }
   }

}