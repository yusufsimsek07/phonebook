import { useState, useEffect } from 'react'
import axios from 'axios'
import './App.css'

function App() {
  const [isim, setIsim] = useState('')
  const [telefon, setTelefon] = useState('')

  // YENİ STATE: Veritabanından gelen kişileri liste (array) olarak tutacak state
  const [kisiler, setKisiler] = useState([])

  // SAYFA AÇILDIĞINDA ÇALIŞACAK FONKSİYON (useEffect)
  // Backend'e GET isteği atıp veritabanındaki tüm kişileri çeker
  const kisileriGetir = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/contacts');
      setKisiler(response.data); // Gelen veriyi (Ahmet, Yusuf vb.) State'e kaydet
    } catch (error) {
      console.error("Kişiler çekilirken hata oluştu:", error);
    }
  }

  // useEffect: Sayfa (App bileşeni) ilk yüklendiğinde içindeki kodu SADECE 1 KERE çalıştırır.
  useEffect(() => {
    kisileriGetir();
  }, []) // Sondaki boş dizi [] "sadece ilk açılışta çalıştır" demektir.

  const kisiKaydet = async () => {
    if (!isim || !telefon) {
      alert("Lütfen isim ve telefon boş bırakmayın!");
      return;
    }

    try {
      await axios.post('http://localhost:8080/api/contacts', {
        name: isim,
        phoneNumber: telefon
      });

      alert("Kişi başarıyla kaydedildi!");
      setIsim('');
      setTelefon('');

      // Kayıt başarılı olduktan sonra listeyi GÜNCELLE (Yeni eklenen kişiyi ekranda görmek için)
      kisileriGetir();

    } catch (error) {
      console.error("Kayıt hatası:", error);
      alert("Kayıt sırasında bir hata oluştu!");
    }
  }

  return (
    <div className="container">
      <h1>Telefon Rehberi</h1>

      {/* KİŞİ EKLEME FORMU */}
      <div className="form-group">
        <input
          type="text"
          placeholder="Kişi Adı"
          value={isim}
          onChange={(e) => setIsim(e.target.value)}
        />
        <input
          type="text"
          placeholder="Telefon Numarası"
          value={telefon}
          onChange={(e) => setTelefon(e.target.value)}
        />
        <button onClick={kisiKaydet}>
          Kaydet
        </button>
      </div>

      <hr style={{ margin: '30px 0' }} />

      {/* KİŞİLERİ LİSTELEME ALANI (.map Fonksiyonu) */}
      <h2>Kayıtlı Kişiler</h2>
      <div className="liste-container">
        {/*
          MAP MANTIĞI: Veritabanından gelen 10 kişi varsa, div'i 10 kere kopyalayıp alt alta basmazsınız.
          JS Array.map() fonksiyonu listenin içinde tek tek dolaşır ve her bir kişi için ekrana aşağıdaki HTML kalıbını basar.
        */}
        {kisiler.length === 0 ? (
          <p>Rehberinizde henüz kimse yok.</p>
        ) : (
          kisiler.map((kisi) => (
            <div key={kisi.id} className="kisi-karti">
              <strong>{kisi.name}</strong>
              <span> - {kisi.phoneNumber}</span>
            </div>
          ))
        )}
      </div>

    </div>
  )
}

export default App
