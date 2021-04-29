package com.example.Topic;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

class Mailer extends Thread{
    String from="bharathikavi1902@gmail.com",sub="Subject",msg="testing java smtp",password="@Kavitha@80988",to;
    Mailer(String to){
        this.to=to;
    }
    public void run(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            System.out.println("message sent successfully to "+to);
            // Runnable Thread called to acknowledge the mail
        }
        catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
class SendTheMail extends Thread {
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println("Thread for sending mail is running");
            String smail="avinashkumarairforce@gmail.com";
            Mailer m=new Mailer(smail);
            m.start();
            System.out.println("Mail sent successfully");

            // send confirmation mail
            ConfirmationMail confirmationMail = new ConfirmationMail();
            confirmationMail.start();
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
class ConfirmationMail extends Thread {
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println("Thread for confirmation mail is running");
            String smail="bharathikavi1902@gmail.com";
            Mailer m=new Mailer(smail);
            m.start();
            System.out.println("Confirmation Mail sent successfully");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}

public class SendMail {
    public static void main(String[] args) {
        // Multi thread call run method to execute the mail
        SendTheMail sendTheMail = new SendTheMail();
        sendTheMail.start();
    }
}
