//Thread qui roule tant qu'on ne pèse pas sur 'Q'.
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane; //Pour les fenêtres de dialogue

class GestionConnexions implements Runnable
{
	private static final int NB_JOUEURS = 4;
   private Server Serveur_;
   private ServerSocket ServeurSocket_;
   private BufferedReader reader_;
   public GestionConnexions(Server oServeur, ServerSocket serveurSocket)
   {
      Serveur_ = oServeur;
      ServeurSocket_ = serveurSocket;
   }

   public void run()
   {
      try
      {
         //boolean EstEnService = true;
         int cptJoueurs = 0;
         String nomJoueur ="";

         System.out.println("Bienvenue sur le serveur Trival Pursuit fonctionnel sur l'adresse " + Serveur_.getAdresse() +" et sur le port no : " + Integer.toString( Serveur_.getPort()));
         System.out.println("Le serveur attend des connexions de joueurs !");
         System.out.println("le nombre maximales de connections autoriser est de : " + Serveur_.getNbConnections() );
         System.out.println("");
         //Le serveur se ferme quand tout les joueurs sont déconnecter
         do
         {
            if(Serveur_.getJoueur().size() <= NB_JOUEURS)
            {
               Socket sServeur = ServeurSocket_.accept();
               reader_ = new BufferedReader( new InputStreamReader( sServeur.getInputStream() ) );
                System.out.println("UNE CONNEXION !");
               nomJoueur = reader_.readLine();
               cptJoueurs++;
               Serveur_.AjoutJoueur(new Joueur(sServeur, nomJoueur, cptJoueurs));
            }
            else
            {
               Socket sServeur = ServeurSocket_.accept();
               System.out.println("Le serveur a atteint le nb maximal de joueurs ");
            }
         }
         while( Serveur_.getJoueur().size() > 0 );

         //reader.close();
         JOptionPane.showMessageDialog( null,
                                        "Tout les joueurs se sont déconnecté. La partie est terminée !",
                                        "Partie terminée",
                                        JOptionPane.WARNING_MESSAGE );
         System.out.println("Fermeture du Serveur");
         System.exit( 1 );
      }
      catch( IOException e )
      {
         System.err.println( e );
         System.exit( 1 );
      }
   }
}