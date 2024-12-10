# Dağıtık Abonelik Sistemi (Distributed Subscriber Service)

Bu proje, dağıtık bir abonelik sistemi oluşturmak amacıyla geliştirilmiştir. Sistem, sunucular, istemciler, yönetim paneli ve Protobuf kullanılarak tasarlanmış olup, örnek bir akış, sistem görselleri ve dosya çalıştırma detayları aşağıda açıklanmıştır.

## Örnek Bir Akış
Server1.java, Server2.java ve Server3.java sırasıyla koşturulup, kendi aralarında TCP soketleri ile “connection” oluşturmalıdır. Bu olaydan 6 adet connection meydana gelmelidir. (TCP connection oluşturmak için herhangi bir gönderime gerek olmadığını hatırlayınız.)

admin.rb, kendisi ile aynı dizinde bulunan “dist_subs.conf” dosyasını okuyup; Server1.java, Server2.java, Server3.java’ ya başlama “STRT” emrini verir. dist_subs.conf dosyasının içerisinde şimdilik sadece tek satırlık “fault_tolerance_level = X” değişkeni bulunmalıdır. Dosyadan hata tolerans seviyesi okunup Configuration sınıfından bir nesne construct edilmelidir. Configuration sınıfından türetilecek nesnenin setlenecek özellikleri:

fault_tolerance_level = 1 
method = STRT

Server1.java, Server2.java, Server3.java sunucuları, admin.rb tarafından gelen bu isteğin başarılı ve başarısız sonuçlandığına dair Message tipinde bir nesne göndermelidir. Message nesnesine ait alanların setlenmesi:

demand = STRT 
response = YEP

veya

	demand = STRT 
response = NOP

admin.rb, 3. adımdaki başlat emrine karşın sunuculardan gelen dönütlere yani Message nesnelerine bakar. “response = YEP” olarak dönen sunuculara 5 sn’ de bir kapasite doluluklarını sorar. Sorgu işlemi için de Message tipinde bir nesne, sunuculara gönderilmelidir. Message nesnesine ait alanların setlenmesi:

İstek atılan Message nesnesi:
demand = CPCTY 
response = null

Server1’ den gelebilecek Capacity nesnesi cevabı:
server1_status : 1000
timestamp : 1729807522

Server2’ den gelebilecek Capacity nesnesi cevabı:
server2_status : 1000
timestamp : 1729807524

Server3’ ten gelebilecek Capacity nesnesi cevabı:
server3_status : 1000
timestamp : 1729807526

Server1.java, Server2.java, Server3.java sunucularına, admin.rb tarafından 5 sn.’ de bir atılan kapasite isteklerine karşın cevap aldığı Capacity tipindeki bir nesneleri plotter.py sunucusuna göndermelidir. plotter.py sunucusu anlık olarak doluluk miktarlarını aşağıdaki gibi veya benzer bir şekilde plot etmelidir. Grafikte hangi rengin kaç numaralı sunucuya ait olduğu bilgisi de gösterilmelidir. 


İlk aşamada Server1.java, Server2.java ve Server3.java sunucuları “fault_tolerance_level = 1” olarak seçildiğinde hizmet verebiliyor olmalıdır. İstemcilerden gelebilecek talepler Subscriber sınıfından inşa edilmiş bir nesne şeklinde olacaktır. Abone olma ve abonelikten çıkma Subscriber nesnesi örneği:

demand = SUBS //demand types SUBS, DEL, UPDT, ONLN, OFFL
ID : 12 
name_surname : “Jane DOE” 
start_date : 1729802522
last_accessed : 1729806522 
interests : [“sports”, “lifestyle”, “cooking”, “psychology”]
isOnline : boolean 

demand = DEL
ID : 12 
name_surname : // null olabilir
start_date : // null olabilir
last_accessed : // null olabilir
interests : // null olabilir
isOnline : // null olabilir

İlk aşamada demand tiplerinden abone olmak anlamına gelen SUBS; abonelikten çıkmak anlamına gelen DEL aşamalarını gerçekleyiniz.


## Derleme ve Çalıştırma Aşamaları

### Protobuf Derleme

**Sunucular (Java) için:**
```bash
cd dist_servers
protoc --java_out=. <Proto Dosya İsmi>.proto

Yönetim Paneli (Ruby) için:
cd panel
protoc --ruby_out=. <Proto Dosya İsmi>.proto

Java Dosyalarını Derleme
cd dist_servers
javac -cp ".;com/google/protobuf/protobuf-java-4.28.3.jar" *.java

Java Dosyalarını Çalıştırma
cd dist_servers
java -cp ".;com/google/protobuf/protobuf-java-4.28.3.jar" <Server adı>

Ruby Admin Panelini Çalıştırma
cd panel
ruby admin.rb

Java Client Çalıştırma
cd Clients
javac -cp ".;com/google/protobuf/protobuf-java-4.28.3.jar" <Client adı>.java
java -cp ".;com/google/protobuf/protobuf-java-4.28.3.jar" <Client adı>


