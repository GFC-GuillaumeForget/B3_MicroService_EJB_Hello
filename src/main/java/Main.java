import demo.mydemo;


public class Main  {

    /* Objectif de ce pilote
  - integrer un JAR comme lib et l'exploiter
  - EJB statefull et stateless
  -> creation d'un client avec jquery ajax
   */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--BEGIN--Date : ");
        //static call
        System.out.println(mydemo.testDate());

        //instance test
        String myname = "Guillaume";
        mydemo d = new mydemo(myname,25);

        //tests appels objets etudiant
        System.out.println (d.giveAllEtudiant() );
        // attendre 3 sec
        Thread.sleep(3000);
        System.out.println (d.giveAllEtudiantBachelor3());
    }
}