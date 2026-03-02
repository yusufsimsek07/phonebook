# 🚀 Spring Boot Adım Adım Çalışma Rehberi

Merhaba! Bu rehber, yazdığımız projenin nasıl çalıştığını "ezberlemeden", mantığını kavrayarak öğrenmeniz için bir öğretmen edasıyla hazırlandı. 

Güzel bir kahve alın, arkanıza yaslanın ve IntelliJ IDEA'da aşağıdaki adımları sırasıyla uygulayın.

---

## 📍 Adım 1: Veritabanı Ayarları (Temeli Atmak)
**Gitmeniz Gereken Dosya:** `src/main/resources/application.properties`

**Burada Ne Öğreneceksiniz?**
Spring Boot çok akıllıdır ama müneccim değildir. Hangi veritabanına bağlanacağını veya şifresini bilemez. İşte uygulamanın "Ayar Menüsü" burasıdır.
* **İnceleyin:** `spring.datasource.url` satırına bakın. Uygulamanın PostgreSQL'e o port (5432) üzerinden bağlandığını görün.
* **Sihirli Satır:** `spring.jpa.hibernate.ddl-auto=update` satırına dikkat edin. Bu satır Spring'e şunu der: *"Java sınıflarıma bak, eğer veritabanında öyle bir tablo yoksa benim yerime otomatik yarat."* Bu yüzden hiç SQL (CREATE TABLE vs.) yazmadık!

---

## 📍 Adım 2: Model (Entity) Katmanı - Kalıp Çıkarmak
**Gitmeniz Gereken Dosya:** `src/main/java/com/example/phonebook/entity/Contact.java`

**Burada Ne Öğreneceksiniz?**
Bu dosya uygulamanın kalbidir. PostgreSQL'deki "Kişiler" tablosunun Java'daki yansımasıdır (Kalıbıdır).
* **İnceleyin:** Sınıfın tepesindeki `@Entity` yazısını silerseniz Spring bu dosyanın veritabanı tablosu olduğunu anlayamaz ve sistem çöker.
* **İnceleyin:** Dosyada sadece `id`, `name`, `phoneNumber` tanımlı değil mi? Peki nasıl oluyor da dışarıdan birisi `kisi.getName()` diyerek adı alabiliyor? Çünkü `@Data` (Lombok) anotasyonu sizin göremediğiniz arka planda o uzun `Getter` ve `Setter` kodlarını otomatik yazıyor. Kodumuz bu yüzden bu kadar temiz!

---

## 📍 Adım 3: Repository Katmanı - Kütüphaneci / Arşiv Görevlisi
**Gitmeniz Gereken Dosya:** `src/main/java/com/example/phonebook/repository/ContactRepository.java`

**Burada Ne Öğreneceksiniz?**
Normalde Java'da veritabanına bağlanıp veri çekmek (SELECT * FROM...) ölümcül derecede uzun ve sıkıcı bir iştir (JDBC kodları).
* **İnceleyin:** Bu dosyanın içinde **hiçbir kod bloğu yok**, sadece bir tanım var. Nasıl çalışıyor?
* **Sihir:** `extends JpaRepository<Contact, Long>` yazdığımız an, Spring arkada bizim için koskoca bir sınıf yaratır. Ve o sınıfın içine veri kaydetme (`save`), veri okuma (`findAll`), id'ye göre arama (`findById`) gibi 20'den fazla hazır fonksiyonu otomatik ekler! İşte Spring Data JPA'nın gücü budur.

---

## 📍 Adım 4: Controller Katmanı - Ön Büro / Resepsiyon
**Gitmeniz Gereken Dosya:** `src/main/java/com/example/phonebook/controller/ContactController.java`

**Burada Ne Öğreneceksiniz?**
Yazdığımız harika kodların internete ve dış dünyaya (Postman, Tarayıcı vs.) açıldığı kapı burasıdır. 
* **İnceleyin:** `@RequestMapping("/api/contacts")` satırına bakın. Bu demek oluyor ki, dışarıdan biri `localhost:8080/api/contacts` adresine geldiğinde doğrudan bu dosyaya (resepsiyona) düşecek.
* **İnceleyin (`getAllContacts` metodu):** Adam sadece "Bana bilgileri ver" (`GET`) dediğinde `@GetMapping` tetiklenir. O da alttaki Arşiv Görevlisine (`contactRepository`) döner ve "Bana hepsini bul" (`findAll()`) emrini verir. Gelenleri de internete gönderir.

---

## 🎯 Adım 5: Kendi Kendine Pratik (Ödev)
Yukarıdaki 4 adımı okuyup anladıysanız, işin mantığını %90 çözdünüz demektir! Şimdi öğrendiklerinizi sınama vakti. Bakalım dışarıdan kopyalamadan mantık kurabilecek misiniz? 

1. `ContactController.java` dosyasına gidin.
2. Bu sınıfa **Tüm Kişileri Silmek** için yeni bir metod yazmayı deneyin.
3. *İpuçları:*
   * Veri getirmek için `@GetMapping` kullandıysak, veri silmek için hangi anotasyonu kullanırız? (Cevap: `@DeleteMapping`)
   * Repository (Arşiv Görevlisi) tüm verileri getirmek için `findAll()` metoduna sahipti. Acaba hepsini silmek için hangi metoda sahiptir? (Cevap: IDE'nizde `contactRepository.` yazdığınızda çıkan seçeneklere bir göz atın, `deleteAll` adında bir şey göreceksiniz!)

*Takıldığınız yerde kod denemeleri yapmaktan korkmayın. Hata alırsanız gelip bana sorabilirsiniz!*
