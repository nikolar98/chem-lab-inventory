package uns.ac.rs.chemlabinventory.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uns.ac.rs.chemlabinventory.model.ChemicalBatch;
import uns.ac.rs.chemlabinventory.model.User;
import uns.ac.rs.chemlabinventory.repository.UserRepository;

import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;

    public EmailService(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    public void sendCriticalQuantityAlert(ChemicalBatch batch) {
        String subject = "⚠ UPOZORENJE: Kritična količina hemikalije!";
        String text = String.format(
                "Poštovani,\n\nZalihe za hemikaliju '%s' su pale ispod definisanog minimuma.\n" +
                        "Trenutna količina: %s %s\nKritični minimum: %s %s\nLokacija: %s\n\nSistem za evidenciju hemikalija.",
                batch.getChemical().getName(),
                batch.getCurrentQuantity(), batch.getPackageUnit(),
                batch.getMinimumQuantityAlarm(), batch.getPackageUnit(),
                batch.getLocation() != null ? batch.getLocation().getName() : "Nedefinisana"
        );
        sendToAllAdmins(subject, text);
    }

    public void sendExpirationAlert(ChemicalBatch batch, long daysLeft) {
        String subject = "⏳ UPOZORENJE: Rok trajanja hemikalije!";
        String text = String.format(
                "Poštovani,\n\nHemikalija '%s' ističe za %d dana (Datum isteka: %s).\n" +
                        "Količina na stanju: %s %s\nLokacija: %s\n\nPreporučuje se provera i eventualni otpis ili hitan utrošak.\n\nSistem za evidenciju hemikalija.",
                batch.getChemical().getName(),
                daysLeft,
                batch.getExpirationDate(),
                batch.getCurrentQuantity(), batch.getPackageUnit(),
                batch.getLocation() != null ? batch.getLocation().getName() : "Nedefinisana"
        );
        sendToAllAdmins(subject, text);
    }

    private void sendToAllAdmins(String subject, String text) {
        List<User> admins = userRepository.findAllAdmins();

        for (User admin : admins) {
            if (admin.getEmail() != null && !admin.getEmail().isEmpty()) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(admin.getEmail());
                message.setSubject(subject);
                message.setText(text);
                mailSender.send(message);
            }
        }
    }
}