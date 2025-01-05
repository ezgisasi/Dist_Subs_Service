# Dağıtık Abonelik Sistemi (Distributed Subscriber Service)

Bu proje, **Hata-Tolere Abonelik Servisi Uyelik Protokolü (HASUP)** adı verilen sınıf tabanlı ilkel bir protokol kullanılarak, dağıtık ve hata toleranslı bir abonelik sistemi geliştirmeyi hedefler. Proje kapsamında sunucular arası haberleşmede **Protobuf** teknolojisi ile veri alışverişi sağlanmıştır. Özellikle hata tolerans seviyesine dayalı özgün sunucu tercihleri ve işleyiş yöntemleri ile sistemin güvenilirliği artırılmıştır.

## Sunucu Özellikleri (ServerX.java)
- **Başlatma:** `admin_client.rb` aracılığıyla başlatılabilir.
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

## admin_client.rb Özellikleri
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
- **22060346, Ezgi Şaşı**
- **22060360 Elanur İmirgi**
- **22060330, Ayşenur Sunay**


## Sunum Videosu Linki



