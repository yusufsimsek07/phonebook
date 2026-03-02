# Phonebook (Telefon Rehberi) Projesi - Kurulum ve Çalıştırma Rehberi

Bu proje Spring Boot, Spring Data JPA, PostgreSQL ve Lombok kullanılarak adım adım geliştirilmiştir.
Projeyi ileride tekrar çalıştırmak veya test etmek isterseniz aşağıdaki adımları izleyebilirsiniz.

## 1. Veritabanının Çalıştığından Emin Olun
Projemiz veritabanı olarak **PostgreSQL** kullanmaktadır. Mac bilgisayarınızda veritabanını başlatmak için terminali açıp şu komutu yazın:

```bash
brew services start postgresql@14
```

> **Not:** Eğer işiniz biterse ve veritabanının arka planda boşuna çalışmasını istemiyorsanız şu komutla durdurabilirsiniz:
> `brew services stop postgresql@14`

## 2. Projeyi Çalıştırma (Run)
Projeyi başlatmak için Terminal'de (veya IntelliJ IDEA'nın altındaki terminal sekmesinde) proje klasörünüzde (`/Users/yusuf/IdeaProjects/phonebook`) olduğunuzdan emin olun ve şu komutu yazın:

```bash
./gradlew bootRun
```
*(Eğer Java versiyonu ile ilgili sorun yaşarsanız şu komutu kullanın:)*
`./gradlew bootRun -Dorg.gradle.java.home=/opt/homebrew/opt/openjdk@17`

Terminalde **"Started PhonebookApplication in ..."** yazısını gördüğünüzde uygulama başarıyla ayağa kalkmış demektir.

## 3. Projeyi Test Etme (API Kullanımı)
Uygulamanız çalışırken, dışarıdan (Postman gibi programlar veya internet tarayıcınız/terminaliniz üzerinden) istek atarak test edebilirsiniz.

### A. Yeni Bir Kişi Ekleme (POST Metodu)
Terminali yeni bir pencerede açarak aşağıdaki komutla projenize yeni bir kişi kaydedebilirsiniz:

```bash
curl -X POST http://localhost:8080/api/contacts \
-H "Content-Type: application/json" \
-d '{"name": "Yusuf", "phoneNumber": "0555 123 45 67"}'
```

### B. Kayıtlı Kişileri Listeleme (GET Metodu)
Veritabanındaki tüm kişileri görmek için tarayıcınızdan şu adrese girebilir veya terminalden şu komutu yazabilirsiniz:

```bash
curl http://localhost:8080/api/contacts
```

Tarayıcınızda veya terminalde yazdığınız veriyi JSON formatında (köşeli parantezler içinde) göreceksiniz.

---

## 4. Frontend (React) Kısmını Çalıştırma
Uygulamamızın bir de web arayüzü (Frontend) bulunmaktadır. Bunu çalıştırmak için **yeni bir terminal penceresinde** şu komutları sırasıyla yazın:

```bash
cd phonebook-frontend
npm run dev
```

Bu komutlar Vite sunucusunu başlatacak ve size bir adres (genellikle `http://localhost:5173`) verecektir. O linke tıklayarak veya tarayıcınıza yapıştırarak siteye girebilirsiniz.

---

## ⚡ PROJEYİ NASIL DURDURURUM? (ÇOK ÖNEMLİ)

Eğer çalışmanız bittiyse ve bilgisayarınızın fanları yorulmasın diyorsanız şu iki adımı uygulamanız yeterlidir:

1. **Terminal Penceresini Kapatmak (veya CTRL+C Yapmak)**
Projeyi (ister Frontend `npm run dev` olsun, ister Backend `./gradlew bootRun` olsun) başlattığınız terminal ekranına (siyah pencereye) gidin.
Klavyenizden **`Control`** tuşuna basılı tutarken **`C`** tuşuna basın. Ekranda `^C` yazacak ve koşan yazılar durup tekrar komut girilebilir hale gelecektir. (Veya o terminal sekmesini direkt çarpıdan kapatabilirsiniz).

2. **Veritabanını Kapatmak**
Yine terminale şu kodu yazarsanız arka planda sürekli dönen PostgreSQL motorunu da tamamen kapatmış ve bilgisayarınızı tam istirahate çekmiş olursunuz:
```bash
brew services stop postgresql@14
```
