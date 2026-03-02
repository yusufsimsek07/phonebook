# ⚛️ React Frontend Çalışma Rehberi

Tıpkı Java Backend'de yaptığımız gibi, şimdi de yazdığımız "Kaporta ve Direksiyon" (React) kısmının nasıl çalıştığını mantığıyla anlayacağız. Lütfen `phonebook-frontend/src/App.jsx` dosyasını açıp bu adımları takip edin.

---

## 📍 Adım 1: JSX (HTML'in Zeki Hali)
**Öğrenilecek Kısım:** `return ( ... )` bloğunun içi.
React dosyalarına baktığınızda içinde düz HTML yazıyormuşuz gibi görünür ama aslında bunlar **JSX**'tir.
* **Neden `className`?:** HTML'de etiketlere isim vermek için `class="kutucu"` yazarız. Ama JavaScript dünyasında `class` kelimesi yasaklı/özel bir kelime olduğu için, React geliştiricileri buna bir çare bulup `className="kutucu"` yazmayı zorunlu kılmıştır.
* **Sihirli Parantezler `{ }`:** JSX'in en büyük gücü budur. HTML kodlarının arasına süslü parantez açtığınız an, orası artık bir HTML değil canlı bir JavaScript alanı olur. Oraya değişken koyabilirsiniz `<h1>{isim}</h1>` gibi.

---

## 📍 Adım 2: State (Durum) Yönetimi - React'in Kalbi
**Öğrenilecek Kısım:** Dosyanın başındaki `const [isim, setIsim] = useState('')` kodları.

Normal HTML'de bir kutucuğa yazı yazdığınızda o yazı orada ölü gibi durur. Ancak React'te her şey "Canlı" olmalıdır.
* **State Nedir?:** State, o an uygulamanın aklında tuttuğu anlık durumdur. 
* **Nasıl Çalışır?:** Siz kutucuğa bir harf yazdığınızda (`onChange` tetiklendiğinde), `setIsim` uzaktan kumandası çalışır ve yeni değeri `isim` değişkenine kaydeder. React çok akıllıdır, bu değişikliği anladığı an ekranı saniyeden daha kısa sürede (sayfayı hiç yenilemeden) günceller!

---

## 📍 Adım 3: useEffect() - Sayfa Açılır Açılmaz
**Öğrenilecek Kısım:** `useEffect(() => { kisileriGetir(); }, [])` kod bloğu.

Veritabanındaki kişileri göstermek istiyoruz ama bunu "ne zaman" yapacağız?
* **Çalışma Mantığı:** Bu kanca (hook), bizim bileşenimiz (sayfamız) ekrana ilk çizildiği anda otomatik olarak devreye girer.
* **Sondaki Boş Dizi `[]`:** Bu çok kritik bir kuraldır. Eğer o boş diziyi koymazsanız React o kodu sürekli çalıştırarak sonsuz bir döngüye girer. Boş dizi `[]` koyarak, *"Ekranda gördüğün an bu kodu SADECE BİR KERE çalıştır, bir daha dokunma"* demiş oluyoruz. 

---

## 📍 Adım 4: Axios ile İnternete (Backend'e) Çıkmak
**Öğrenilecek Kısım:** `kisileriGetir` ve `kisiKaydet` fonksiyonlarının içindeki kodlar.

Biz normalde verileri eklerken veya listelerken Postman programını kullanıyorduk. İnternet sitesi olduğumuzda ise bunu yapmak için `axios` adlı kütüphaneyi kullanırız.
* **POST (Veri Gönderme):** `axios.post('http://localhost...`, { name: isim, phoneNumber: telefon })` kodu ile "Al bu JSON verilerini, gidip şu adrese kaydet" diyoruz.
* **GET (Veri Alma):** `axios.get('http://localhost...')` dediğimizde arka plana bir ajan gönderip "Git o adresten ('/api/contacts') bana bütün kişilerin json listesini getir" diyoruz. 

---

## 📍 Adım 5: Gelen Verileri Listelemek (.map Mucizesi)
**Öğrenilecek Kısım:** Sayfanın en altındaki `.map` ile başlayan kısım.

Backend'den örneğin 500 tane kişi geldi. Bunları alt alta 500 tane `<div>` açıp tek tek yazacak mıyız? Tabii ki hayır!
* **Döngü Mantığı:** Gelen dizinin (array) içindeki eleman sayısı kadar o kod bloğunu çoğaltmak için JavaScript'in dizi döngü fonksiyonu olan `.map()`i kullanırız. 
* **`key={kisi.id}` kuralı:** React 500 tane kutucuk kopyaladığında, ileride onlardan birini silmek veya güncellemek istersek hangisi olduğunu bilemez ve kafası karışır. Bu yüzden her elemana eşsiz bir TC Kimlik Numarası (`key`) basmamız zorunludur.

Tebrikler! Artık bir web sayfasının ön yüzünün modern dünyada nasıl çalıştığının tüm temel mekaniklerine hakimsiniz. Geriye kalan tek şey CSS öğrenip bu mantıkları süslemekten ibaret! 🚀
