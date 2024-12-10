import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import src.main.java.subscriber.SubscriberOuterClass;

public class Client3 {
    private static final String Lokal_host = "localhost"; 
    private static final int port = 5003; 

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(Lokal_host, port);
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream();
             Scanner scanner = new Scanner(System.in)) {

            String choice = scanner.nextLine().trim();

            if ("1".equalsIgnoreCase(choice)) {
                SubscriberOuterClass.Subscriber newSubscriber = SubscriberOuterClass.Subscriber.newBuilder()
                        .setID(3)
                        .setNameSurname("Ayşenur Sunay")
                        .setStartDate(System.currentTimeMillis() / 1000L)
                        .setLastAccessed(System.currentTimeMillis() / 1000L)
                        .addAllInterests(Arrays.asList("Hiking", "Coding", "Cooking"))
                        .setIsOnline(true)
                        .setDemand(SubscriberOuterClass.Subscriber.Demand.SUBS)
                        .build();

                newSubscriber.writeTo(outputStream);
                outputStream.flush();

            } else if ("2".equalsIgnoreCase(choice)) {
                SubscriberOuterClass.Subscriber cancelRequest = SubscriberOuterClass.Subscriber.newBuilder()
                        .setID(3)
                        .setDemand(SubscriberOuterClass.Subscriber.Demand.DEL)
                        .build();

                cancelRequest.writeTo(outputStream);
                outputStream.flush();
                System.out.println("Abonelik iptali isteği gönderildi:\n" + cancelRequest);

            } else {
                System.out.println("Geçersiz seçim yaptınız. Lütfen '1' ya da '2' tuşlayınız.");
            }
        } catch (IOException ex) {
            System.err.println("Sunucuya bağlanırken bir hata oluştu: " + Lokal_host + ":" +port);
            ex.printStackTrace();
        }
    }
}
