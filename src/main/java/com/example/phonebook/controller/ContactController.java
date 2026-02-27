package com.example.phonebook.controller;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.repository.ContactRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CONTROLLER KATMANI (Dış Dünya ile İletişim Noktası / Ön Büro)
 *
 * Bu sınıf, uygulamamızın "Resepsiyonu" gibidir. Dış dünyadan (İnternetten, Telefondan, Postman'dan)
 * gelen bağlantı isteklerini (HTTP Requests) karşılar, içerideki arşiv görevlilerine (Repository)
 * gönderir ve arşivden gelen yanıtı dış dünyaya (JSON formatında) geri döndürür.
 *
 * @RestController Anotasyonu:
 * Bu sihirli kelime Spring Boot'a şunu söyler:
 * "Ey Spring! İçinde bulunduğum sınıf bir Web Sunucusu Resepsiyonudur. Bana gelen tüm isteklerin
 * cevabını ekranda HTML sayfası göstermek yerine, doğrudan veri (JSON) olarak geri yollayacağım."
 * (Örneğin telefondaki bir uygulamaya sadece verinin kendisi lazımdır, süslü bir sayfa değil).
 *
 * @RequestMapping("/api/contacts") Anotasyonu:
 * Bu resepsiyonun adresini veya kapı numarasını belirler. Yani dışarıdan biri bizim uygulamamıza:
 * "http://localhost:8080/api/contacts" adresi üzerinden bağlanacak demektir.
 * Bu sınıfın içindeki tüm adresler bu "/api/contacts" adresiyle başlar.
 */
@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    /**
     * BAĞINTI (DEPENDENCY INJECTION) KISMI
     *
     * Resepsiyon (Controller) gelen isteklere sadece cevap verir ama veritabanındaki 
     * dosyaları aslında göremez. Dosyaları görebilen tek bir birim vardı: Arşiv Görevlisi (Repository).
     *
     * Bu yüzden Resepsiyonun içine bir adet Arşiv Görevlisi (ContactRepository) almalıyız. 
     * Bunu "Constructor (Kurucu Metod)" adı verilen yöntemle aşağıda yapıyoruz.
     * Spring ayağa kalkarken otomatik olarak gidip oluşturduğu Arşiv Görevlisini buraya bağlar.
     */
    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * 1. LİSTELEME İŞLEMİ (READ - Müşterinin "Tüm kişileri ver" demesi)
     *
     * @GetMapping Anotasyonu:
     * Bu metod dış dünyadan bir 'GET' (Bana Veri Ver) isteği gelirse çalışır.
     * Hangi adrese? Sınıfın üstünde tanımladığımız "@RequestMapping("/api/contacts")" adresine.
     *
     * Mantığı nedir?
     * Birisi "http://localhost:8080/api/contacts" adresine girdiğinde (veya istek attığında);
     * 1. Bu metod devreye girer.
     * 2. Hemen Arşiv Görevlisine (contactRepository) "Bana veritabanındaki her şeyi bul (findAll)" emrini verir.
     * 3. Gelen tüm veri listesi (List<Contact>) dışarıya JSON formatında fırlatılır.
     */
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * 2. YENİ KİŞİ EKLEME İŞLEMİ (CREATE - Müşterinin "Bu kişiyi kaydet" demesi)
     *
     * @PostMapping Anotasyonu:
     * Bu metod dış dünyadan bir 'POST' (Bana Veri Gönderiliyor, bunu kaydet) isteği gelirse çalışır.
     * İnternet sitelerinde formu doldurup "Gönder/Kaydet" butonuna bastığınızda yapılan işlem POST'tur.
     *
     * @RequestBody Anotasyonu:
     * Dışarıdan gelen veriyi (Sadece bir metin parçası olan JSON'u) alır ve bizim akıllı Java Sınıfımıza (Contact nesnesine)
     * sihirli bir şekilde dönüştürür.
     * Yani dışarıdan {"name": "Yusuf", "phoneNumber": "555-1234"} şeklinde bir metin gelir,
     * Spring bunu alır, Contact sınıfından yeni bir nesne üretip Contact contact parametresine doldurur.
     *
     * Mantığı nedir?
     * 1. Dış dünya bize kaydedilecek kişinin bilgilerini gönderdi.
     * 2. Spring onu yukarıdaki RequestBody sayesinde Contact nesnesine çevirdi.
     * 3. Biz de Arşiv Görevlisine dönüp "Al bunu veri tabanına kaydet (save) ve id falan atanmış
     *    son halini bana geri ver ki müşteriye kaydettim diye gösterebileyim" diyoruz.
     */
    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }
}
