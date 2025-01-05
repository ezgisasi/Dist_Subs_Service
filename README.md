# Dağıtık Abonelik Sistemi (Distributed Subscriber Service)

Bu projede, Ruby, Python ve Java programlama dilleri kullanılarak, Taşıma (Transport) Katmanı üzerinde dağıtık, hata toleransına sahip bir abonelik sistemi geliştirmemiz beklenmektedir. Bu sistem, soket üzerinden ödeve özgü olarak tasarlanmış, sınıf tabanlı ve ilkel bir protokol olan HASUP (Hata-Tolere Abonelik Servisi Üyelik Protokolü) ile çalışacaktır. HASUP protokolü, belirlenen sıraya uygun şekilde işlem gerçekleştirmelidir. Dağıtık mimaride tasarlanan bu sistem, yük dengeleme ve hata toleransı gibi gereksinimlere yanıt vermelidir. Her bir sunucu, kendi bünyesinde abone bilgilerini tutarken, istemcilerin bir sunucudan abone olup, başka bir sunucudan sisteme giriş yapabilmesine olanak tanıyacaktır. Eşzamanlı istemci erişimlerinde, her bir sunucunun thread-safe bir yapıyla çalışarak, kendi veri yapılarına güvenli erişim sağlaması gerekmektedir.

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
- **22060346, Ezgi Şaşı**
- **22060360 Elanur İmirgi**
- **22060330, Ayşenur Sunay**


## Sunum Videosu Linki



