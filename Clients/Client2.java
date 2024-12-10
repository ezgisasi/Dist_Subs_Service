import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;
import src.main.java.subscriber.SubscriberOuterClass;

public class Client2 {
    private static final String LOCAL_HOST = "localhost";
    private static final int PORT = 5002;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(LOCAL_HOST, PORT);
             InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Seçiminizi yapın: (1 - Abonelik Başlat, 2 - Abonelik İptal)");
            String choice = scanner.nextLine().trim();

            if ("1".equalsIgnoreCase(choice)) {
                SubscriberOuterClass.Subscriber newSubscriber = SubscriberOuterClass.Subscriber.newBuilder()
                        .setID(2)
                        .setNameSurname("Ezgi Şaşı")
                        .setStartDate(System.currentTimeMillis() / 1000L)
                        .setLastAccessed(System.currentTimeMillis() / 1000L)
                        .addAllInterests(Arrays.asList("sports", "lifestyle", "technology"))
                        .setIsOnline(true)
                        .setDemand(SubscriberOuterClass.Subscriber.Demand.SUBS)
                        .build();

                newSubscriber.writeTo(outputStream);
                outputStream.flush();
                System.out.println("Abonelik başlatma isteği gönderildi:\n" + newSubscriber);

            } else if ("2".equalsIgnoreCase(choice)) {
                SubscriberOuterClass.Subscriber cancelRequest = SubscriberOuterClass.Subscriber.newBuilder()
                        .setID(2)
                        .setDemand(SubscriberOuterClass.Subscriber.Demand.DEL)
                        .build();

                cancelRequest.writeTo(outputStream);
                outputStream.flush();
                System.out.println("Abonelik iptali isteği gönderildi:\n" + cancelRequest);

            } else {
                System.out.println("Geçersiz seçim yaptınız. Lütfen '1' ya da '2' tuşlayınız.");
            }
        } catch (IOException ex) {
            System.err.println("Sunucuya bağlanırken bir hata oluştu: " + LOCAL_HOST + ":" + PORT);
            ex.printStackTrace();
        }
    }
}
