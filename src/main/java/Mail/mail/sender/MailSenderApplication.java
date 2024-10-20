package Mail.mail.sender;

import entities.EmailSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MailSenderApplication {

	public static void main(String[] args) {

		SpringApplication.run(MailSenderApplication.class, args);
		String[] recipients = {"elhallamimane4@gmail.com", "soumirihssane85@gmail.com"};

		String subject = "Candidature Stage PFE 2025";
		String body = "Bonjour, \nJe vous soumets ma candidature pour un stage PFE. Veuillez trouver mon CV en pièce jointe.\nCordialement.";
		String filePath = "C:/Users/asus/Downloads/CV_ELKAROUITI_CHAIMAA_FR_VF.pdf";

		// Appel de la méthode pour envoyer l'e-mail
		EmailSender.sendEmails(recipients, subject, body, filePath);
	}

}
