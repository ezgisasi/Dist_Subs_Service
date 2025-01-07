# Dağıtık Abonelik Sistemi (Distributed Subscriber Service)

Bu proje, **Sistem Programlama** dersi kapsamında geliştirilmiş bir **Dağıtık Abonelik Sistemi** uygulamasıdır. Projede, üç adet çok iş parçacıklı sunucu (multithreaded server) ve bir istemci sistemi kullanılarak hata-toleranslı bir abonelik hizmeti sunulmaktadır. Sistem, aşağıda açıklanan **HASUP (Hata-Tolere Abonelik Servisi Üyelik Protokolü)** ile çalışmaktadır.

## Özgün Katkılar
Ela: Java Sunucu ve Dağıtık Sistem
Ezgi: Admin ve Plotter İstemcileri
Ayşenur: Client İşlemci ve Protobuf Tanımları

## Proje Özeti

Bu proje, TCP/IP soket programlama kullanılarak geliştirilmiştir ve dağıtık sistemlerin temel özelliklerini içermektedir:

- **Hata Toleransı:**
  - **Hata Toleransı 1:** Sistemin bir sunucu hatasında çalışmaya devam edebilmesini sağlar.
  - **Hata Toleransı 2:** İki sunucu hatasında da çalışabilirliği garanti eder.
- **Protokol Tabanlı Haberleşme:**
  - HASUP protokolü, sunucu ve istemciler arasında sınıf tabanlı bir haberleşme sağlar.
  - Protobuf ile veri aktarımı gerçekleştirilmiştir.
- **Thread-Safe İşlem:**
  - Sunucu listelerine erişim, eş zamanlı istemciler için güvenli hale getirilmiştir.

---

## ServerX.java özellikleri
- **Başlatma:** `admin.rb` aracılığıyla başlatılabilir.
- **Hata Toleransı:** Hata toleransı 1 prensibiyle çalışmaktadır (1 sunucu hata verdiğinde hizmet devam eder).
- **Protokol Desteği:** HASUP protokolü kullanılarak abonelik işlemleri gerçekleştirilir.
- **Protobuf Tabanlı Haberleşme:** 
  - **Subscriber Nesnesi:** Abonelik bilgilerini içerir.
  - **Capacity Nesnesi:** Sunucu doluluk bilgilerini içerir.
  - **Configuration Nesnesi:** Yönetici tarafından gönderilen başlangıç ayarlarını taşır.
- **Eşzamanlılık:** Eşzamanlı istemci erişimleri için thread-safe veri yönetimi sağlanır.
- **Port Kullanımı:** 
  - Sunucular arası haberleşme: 5001, 5002, 5003
  - İstemci ile haberleşme: 6001, 6002, 6003
  - Admin istemci ile haberleşme: 7001, 7002, 7003

## plotter.py Özellikleri
- **Grafiksel Görselleştirme:** Sunucu doluluk oranlarını anlık olarak grafik ile gösterir.
- **Veri Güncellemesi:** Kapasite bilgilerini 5 saniyede bir günceller.
- **Sunucu Renk Kodlaması:** Her sunucu için ayrı bir renk ile bilgi aktarımı sağlar.

## admin.rb Özellikleri
- **Konfigürasyon:** 
  - `dist_subs.conf` dosyasını okuyarak sunucuları başlatır.
  - Hata toleransı seviyesi ve başlangıç ayarlarını sunuculara iletir.
- **Kapasite Kontrolü:** 5 saniyede bir sunucuların doluluk oranlarını sorgular.
- **Yönetim:** Başarı ve hata durumlarını kontrol eder, gerekli işlemleri başlatır.

## Abonelik İşlemleri
1. **Abone Olma (SUBS):** İstemciden gelen abonelik taleplerini işler.
2. **Giriş Yapma (ONLN):** Abonenin çevrimiçi durumunu günceller.
3. **Çıkış Yapma (OFFL):** Abonenin çevrimdışı durumunu günceller.
4. **Silme (DEL):** Abonenin sistemden silinmesini sağlar.

## Ekip Üyeleri
- **22060360 Elanur İmirgi**
- **22060346, Ezgi Şaşı**
- **22060330, Ayşenur Sunay**


## Sunum Videosu Linki



