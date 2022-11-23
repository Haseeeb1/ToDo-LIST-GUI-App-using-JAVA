
 package MainCode;

 import java.io.File;
 import java.io.IOException;
 import java.util.Properties;
 import javax.mail.Address;
 import javax.mail.Message;
 import javax.mail.MessagingException;
 import javax.mail.Session;
 import javax.mail.Transport;
 import javax.mail.internet.AddressException;
 import javax.mail.internet.InternetAddress;
 import javax.mail.internet.MimeBodyPart;
 import javax.mail.internet.MimeMessage;
 import javax.mail.internet.MimeMultipart;

 public class EmailList {

     public static void send(String from, String pass, String to) throws IOException {
         Properties props = System.getProperties();
         String host = "smtp.gmail.com";
         // String host="localhost";
         props.put("mail.smtp.ssl.protocols", "TLSv1.2");
         props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
         props.put("mail.smtp.user", from);
         props.put("mail.smtp.password", pass);
         props.put("mail.smtp.port", 587);// 587
         props.put("mail.smtp.auth", "true");
         System.out.println("success point 1");

         Session session = Session.getDefaultInstance(props);
         MimeMessage message = new MimeMessage(session);

         try {
             // System.out.println("success point 2");

             message.setFrom(new InternetAddress(from));
             Address addressTo = new InternetAddress(to);
             message.setRecipient(Message.RecipientType.TO, addressTo);

             // System.out.println("success point 3");
            
             // Creating A Multpipart to Send Multiple Things
             message.setSubject("To Do List");
             MimeMultipart multipart = new MimeMultipart();

             // Adding Attachments That We Want To Send
             MimeBodyPart attachment = new MimeBodyPart();
             attachment.attachFile(new File("To_Do_List.txt"));
             multipart.addBodyPart(attachment);

             MimeBodyPart attachment2 = new MimeBodyPart();
             attachment2.attachFile(new File("To_Do_List_History.txt"));
             multipart.addBodyPart(attachment2);
             message.setContent(multipart);
            
             // System.out.println("success point 4");

             Transport transport = session.getTransport("smtp");
             // System.out.println("success point 5");

             transport.connect(host, from, pass);
             // System.out.println("success 6");
             transport.sendMessage(message, message.getAllRecipients());
             transport.close();
             // System.out.println("success 7");
         } catch (AddressException ae) {
             ae.printStackTrace();
         } catch (MessagingException me) {
             me.printStackTrace();
         }
     }

 }
