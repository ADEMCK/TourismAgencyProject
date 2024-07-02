Patika Turizm Acentesi Turizm Acente Sistemi
Bu proje, Patika Turizm Acentesi'nin günlük operasyonlarını dijitalleştirmek için geliştirilmiş bir yazılım çözümüdür. Yazılım, otel yönetimi, oda yönetimi, dönem yönetimi, fiyat yönetimi, oda arama ve rezervasyon işlemleri gibi çeşitli özellikleri kapsar.

Kullanılan Teknolojiler
Java
Java Swing (GUI)
PostgreSQL
Kurulum
Bu projeyi klonlayın.
PostgreSQL veritabanında turizmacentesistemi adında bir veritabanı oluşturun.
turizmacentesistemi.sql dosyasını kullanarak veritabanı tablolarını oluşturun. Veritabanı bağlantısını yapılandırmak için DatabaseConnection.java dosyasını düzenleyin.
Proje Yapısı
business: İş mantığını gerçekleştiren servis sınıflarını içerir.
core: Veritabanı oluşturma dosyasının bulunduğu dizindir.
dao: Veritabanı işlemlerini gerçekleştiren DAO (Data Access Object) sınıflarını içerir.
entity: Veritabanı tablolarını temsil eden model sınıflarını içerir.
views: Kullanıcı arayüzünü oluşturan Swing GUI sınıflarını içerir.
Diğer Özellikler
Otel Ekleme ve Çıkarma
Turizm acente sistemi, çalışanların sisteme yeni oteller eklemesine veya mevcut otelleri kaldırmasına olanak tanır. Bu özellik sayesinde acente, işbirliği yaptığı otelleri güncel tutabilir ve müşterilerine geniş bir otel yelpazesi sunabilir. Otel ekleme ve çıkarma işlemleri, kullanımı kolay bir arayüz ile gerçekleştirilir, böylece çalışanlar hızlı ve hatasız bir şekilde operasyonlarını yürütebilirler.

Rezervasyon Yapma
Sistem, çalışanların müşteri taleplerine göre otel odası rezervasyonu yapabilmesini sağlar. Kullanıcı dostu arayüzü sayesinde çalışanlar, müşterilerin istediği tarih aralığında ve belirlenen kriterlere uygun odaları kolayca arayabilir ve rezerve edebilirler. Rezervasyon işlemi, müşterinin bilgileri ve rezervasyon detayları girilerek tamamlanır ve veritabanına kaydedilir.

Oda Yönetimi
Çalışanlar, mevcut otellerdeki odaları yönetebilir, oda bilgilerini güncelleyebilir ve oda durumlarını takip edebilirler.

Dönem Yönetimi
Fiyatlandırma ve oda müsaitliği gibi bilgileri dönemsel olarak ayarlayabilirler.

Fiyat Yönetimi
Otel odalarının fiyatlarını belirleyebilir ve gerektiğinde güncelleyebilirler.

Oda Arama
Müşterilerin taleplerine uygun oda aramaları gerçekleştirebilirler.
