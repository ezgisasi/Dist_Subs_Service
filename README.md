# Dağıtık Abonelik Sistemi (Distributed Subscriber Service)

Bu proje, dağıtık bir abonelik sistemi oluşturmak amacıyla geliştirilmiştir. Sistem, sunucular, istemciler, yönetim paneli ve Protobuf kullanılarak tasarlanmış olup, işlevsellik ve dosya detayları aşağıda açıklanmıştır.

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

