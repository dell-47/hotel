package by.it.hotel.controller.command.util;

import by.it.hotel.entity.Invoice;
import by.it.hotel.entity.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
    private static final Logger logger = LogManager.getLogger(EmailUtil.class);

    public static void send(Invoice invoice, User user) {

        ResourceBundle rb = ResourceBundle.getBundle("smtp");
        final String username = rb.getString("smtp.username");
        final String password = rb.getString("smtp.password");
        final String mailFrom = rb.getString("smtp.from");
        final String host = rb.getString("smtp.host");
        final String port = rb.getString("smtp.port");
        final String auth = rb.getString("smtp.auth");
        final String SUBJECT_MESSAGE = "Thank you! Your booking is confirmed.";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.auth", auth);

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Map<String, String> rootMap = getRootMap(invoice, user);
        StringWriter out = new StringWriter();
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(EmailUtil.class, "/templates/");

        try {
            Template template = cfg.getTemplate("email.ftl");
            template.process(rootMap, out);
        } catch (IOException | TemplateException e) {
            logger.error(e);
        }

        String mailTo = user.getEmail();
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(mailFrom));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailTo, false)
            );

            message.setSubject(SUBJECT_MESSAGE);
            message.setContent(out.getBuffer().toString(), "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            logger.error(e);
        }
    }

    private static Map<String, String> getRootMap(Invoice invoice, User user) {
        Map<String, String> rootMap = new HashMap<>();
        rootMap.put("name", user.getFirstName());
        rootMap.put("apartType", invoice.getApartType());
        rootMap.put("inDate", invoice.getInDate().toString());
        rootMap.put("outDate", invoice.getOutDate().toString());
        rootMap.put("cost", invoice.getCost().toString());
        rootMap.put("subtotal", invoice.getSubtotalPrice().toString());
        rootMap.put("taxes", invoice.getTaxes().toString());
        rootMap.put("total", invoice.getTotalPrice().toString());
        return rootMap;
    }
}