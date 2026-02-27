package com.example.phonebook.repository;

import com.example.phonebook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * REPOSITORY KATMANI (Veritabanı İletişim Noktası)
 *
 * Bu dosya, bir "Interface" (Arayüz) türündedir. Interface'lerin içinde kod gövdeleri olmaz, sadece tanımlamalar olur.
 * "Peki kod yoksa veritabanı işlemleri nasıl çalışacak?" diye düşünebilirsiniz. İşte Spring Boot'un sihri burada başlıyor!
 *
 * 1. @Repository Anotasyonu: 
 *    Spring arka planda çalışırken bu dosyayı gördüğünde "Hımm, bu bir veritabanı repository'si, bunu 
 *    sisteme kaydedeyim ve ihtiyaç olduğunda başka yerlere vereyim" der.
 *
 * 2. JpaRepository<Contact, Long> Mirası (extends):
 *    En önemli kısım burası. "extends" kelimesi, JpaRepository adındaki devasa bir Spring kütüphanesinin 
 *    tüm özelliklerini bu dosyanın içine kopyalar (miras alır).
 *    
 *    Peki "Contact" ve "Long" ne anlama geliyor?
 *    - Contact: Hangi tablonun verileri üzerinde işlem yapılacağını belirtiyoruz. "Ben Contact (Kişiler) modelimle çalışacağım" diyoruz.
 *    - Long: O tablodaki Primary Key'in (Yani benzersiz ID'nin) veri tipini belirtiyoruz. Hatırlarsanız Contact modelinde id alanını "Long" yapmıştık.
 *
 * SONUÇ OLARAK:
 * Biz buraya TEK SATIR (save, delete, findById gibi) sql veya java kodu yazmasak bile;
 * Spring Boot, extends ettiğimiz bu JpaRepository sayesinde projemizi çalıştırdığımızda arka planda 
 * ContactRepository için gerçek bir sınıf oluşturacak ve o sınıfın içine:
 * - Bir Contact kaydetme SQL kodu
 * - Tüm Contact'leri listeleme SQL kodu
 * - Sadece belli bir ID'ye sahip Contact'i bulma SQL kodu
 * - Bir Contact'i silme SQL kodu
 *
 * Gibi en temel CRUD (Oluştur, Oku, Güncelle, Sil) işlemlerinin kodlarını bizim yerimize otomatik yazıp çalıştıracak!
 * Biz birazdan (Controller katmanında) sadece "contactRepository.save(yeniKisi);" diyeceğiz, o veritabanına ekleyecek.
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    // Şu an için içine hiçbir şey yazmamıza gerek yok. 
    // JpaRepository bizim ihtiyacımız olan %90 SQL işlemini otomatik hallediyor.
    // Eğer ilerde "İsmi Ali olanları bul" (findBy... gibi) özel sorgular yazmak istersek buraya ekleyeceğiz.
}
