package com.example.phonebook.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Bu sınıf PostgreSQL veritabanımızdaki 'contact' (Kişiler) tablosunu temsil eder.
 *
 * @Entity: Spring Data JPA'ya (Hibernate) bunun bir veritabanı tablosu olduğunu söyler.
 *          Veritabanında "contact" adında bir tablo yoksa otomatik olarak oluşturur.
 *
 * @Data:  Lombok kütüphanesinin sihirli anotasyonudur. 
 *         Sınıftaki değişkenler (id, name, phoneNumber) için otomatik olarak:
 *         - Getter (getId(), getName()...)
 *         - Setter (setId(), setName()...)
 *         - toString(), equals() ve hashCode()
 *         metodlarını arka planda yazar. 
 *         Normalde Java'da bu metodlar 30-40 satır yer kaplar. 
 *         Lombok sayesinde kod okunaklı ve kısa kalır.
 */
@Entity
@Data
public class Contact {

    /**
     * Her kişinin veritabanında benzersiz bir kimliği olmalıdır (Primary Key).
     * @Id: Bu alanın Primary Key olduğunu belirtir.
     * @GeneratedValue: (GenerationType.IDENTITY) Id'nin 1, 2, 3.. şeklinde veritabanı
     *                  tarafından otomatik olarak artırılmasını sağlar. 
     *                  Kayıt eklerken id girmemize gerek kalmaz.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Kişinin adı ve soyadı
     */
    private String name;

    /**
     * Kişinin telefon numarası
     */
    private String phoneNumber;
}
