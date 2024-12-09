package servers;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import src.main.java.subscriber.SubscriberOuterClass; 

public class Client1 {
    private static final String HOST = "localhost"; 
    private static final int PORT = 5001; 

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Bağlantı başarılı: " + HOST + ":" + PORT);

            System.out.println("Seçim yapınız: Abone Ol (1) ya da Aboneliği İptal Et (2):");
            String choice = scanner.nextLine().trim();

            if ("1".equalsIgnoreCase(choice)) {
                SubscriberOuterClass.Subscriber newSubscriber = SubscriberOuterClass.Subscriber.newBuilder()
                        .setID(1)
                        .setNameSurname("Elanur İmirgi")
                        .setStartDate(System.currentTimeMillis() / 1000L) 
                        .setLastAccessed(System.currentTimeMillis() / 1000L) 
                        .addInterests("teknoloji")
                        .addInterests("spor")
                        .addInterests("müzik")
                        .setIsOnline(true)
                        .setDemand(SubscriberOuterClass.Subscriber.Demand.SUBS)
                        .build();

                newSubscriber.writeTo(outputStream);
                outputStream.flush();
                System.out.println("Abonelik isteği gönderildi:\n" + newSubscriber);

            } else if ("2".equalsIgnoreCase(choice)) {

                SubscriberOuterClass.Subscriber cancelRequest = SubscriberOuterClass.Subscriber.newBuilder()
                        .setID(1001)
                        .setDemand(SubscriberOuterClass.Subscriber.Demand.DEL)
                        .build();

                cancelRequest.writeTo(outputStream);
                outputStream.flush();
                System.out.println("Abonelik iptali isteği gönderildi:\n" + cancelRequest);

            } else {
                System.out.println("Geçersiz seçim yaptınız. Lütfen '1' ya da '2' tuşlayınız.");
            }
        } catch (IOException ex) {
            System.err.println("Sunucuya bağlanırken bir hata oluştu: " + HOST + ":" + PORT);
            ex.printStackTrace();
        }
    }
}
