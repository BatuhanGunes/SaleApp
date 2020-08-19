**Dil :** [ingilizce](https://github.com/BatuhanGunes/SaleApp) / Türkçe

# Satış Uygulaması
- Android istemci ve Java masaüstü sunucu uygulaması

### Ön Söz

Bu proje, java ile oluşturulmuş bir sunucu uygulamasından alınan bilgiler ile android bir istemci uygulamasından, kullanıcıya çeşitli ürünlerin satışı için tasarlanmıştır. Yani bu proje sunucu ve istemci şeklinde iki farklı uygulamanın birbirleri ile haberleşmesinden ve bu haberleşme neticesinde oluşan işlemlerden oluşmaktadır. 
  
Sunucu uygulaması, istemci uygulamasında kullanılacak ürünlerin oluşturulması, güncellenmesi ve raporlanması için tasarlanmıştır. Sunucu uygulaması bu ürünleri hafızasında tutar. İstemci ile karşılıklı haberleşerek hafızasındaki ürünleri istemciye ulaştırılmasını sağlar. Hangi verilerin iletildiğine istemciden gelen mesajlar ile karar verir. Aynı zamanda istemci tarafından oluşturulmuş yeni verilerin kaydedilmesi görevinide üstlenir. Yine bu işlemde de hangi verinin nasıl kaydedileceğini istemciden gelen mesajlar ile belirler. Mesaj ile birlikte gelen verileri, veri tabanına kaydeder.

İstemci uygulaması, sunucu uygulaması için gerekli bağlatı değerlerini kullanıcıdan alır. Alınan bilgiler ile iletişim kurulmaya çalışılır. İşlemin sorunsuz gerçekleşmesi durumunda kullanıcı yeni bir sayfaya geçer. Yeni sayfa üzerinde kullanıcı sunucuda bulunan verilerin istemciye iletilmesini isteyebilir. Bu istek sonrasında sunucuya bir mesaj gönderir ve bu mesajın cevabını bekler. İstemci, sunucu tarafından gönderilen cevapları dinleyerek önceden kaydedilmiş ürünleri hafızasına alır. Bu ürünleri kullanıcıya sunarak kullanıcının istemci uygulaması üzerinden alış-veriş yapmasına olanak sağlar. Kullanıcı var olan ürünlerden dilediğini dilediği miktarda seçebilir. Kullanıcının seçimleri doğrultusunda satın alınacak olan ürünlerin toplam fiyatı belirlenir. Kullanıcı ödemeyi nakit veya kredi kartı ile gerçekleştirebilir. Ödeme işlemlerinin gerçek bir karşılığı yoktur, kullanıcı tarafından ödendiği varsayılır. Kullanıcı bir ödeme gerçekleştirdikten sonra satın aldığı ürünler ile bir fiş oluşturulur. Kullanıcı dilerse alış-verişine kaldığı yerden devam edebilmek için var olan fişin üzerinde yeni bir fiş oluşturabilmektedir. İstemci, kullanıcının alış-veriş işlemleri sonlandığında, gerçekleştirmiş olduğu alış-veriş ile ilgili verileri toplar. Toplanan bu veriler bir düzen içerisinde sunucuya gönderilir. Sunucuya gelen bu veriler ayrılıştırılarak veri tabanına kaydeder.

### Verilerin Kaydedildiği Tablolar

<img align="center" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/tableProduct.png"> <img align="center" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/tableSale.png"> <img align="center" src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/tableSaleDetails.png">

## Akış Diyagramı

<img align="center" width="220" height="350" src="">

## Ekran Görüntüleri
### Sunucu Uygulaması

<img align="center" width="220" height="350" src="">  <img align="center" width="220" height="350" src=""> <img align="center" width="220" height="350" src="">  <img align="center" width="220" height="350" src="">

### İstemci Uygulaması

<img align="center" width="220" height="350" src="">  <img align="center" width="220" height="350" src=""> <img align="center" width="220" height="350" src="">  <img align="center" width="220" height="350" src="">

## Başlangıç

Projeyi çalıştırabilmek için proje dosyalarının bir kopyasını yerel makinenize indirin. Gerekli ortamları edindikten sonra projeyi bu ortamda açarak çalıştırabilirsiniz. İstemci uygulamasını android bir cihaz üzerinde de çalıştırabilirsiniz.

### Gereklilikler

* Android Studio (Linux, Mac veya Windows makinesine yüklenmiş)

* [`Geliştirici modu`](https://developer.android.com/studio/debug/dev-options) ve USB hata ayıklama seçeneği etkin olan android cihaz

* USB kablosu (cihazı bilgisayarınıza bağlamak için)

* Java IDE (Linux, Mac veya Windows makinesine yüklenmiş)

* Java Development Kit (JDK)


## İnşa et ve Çalıştır

### Adım 1. SaleApp kaynak kodunu kopyalayın

Uygulamayı almak için GitHub deposunu bilgisayarınıza kopyalayın.

```
git clone https://github.com/BatuhanGunes/SaleApp
```

Java IDE'de İstemci kaynak kodunu açın. Bunu yapmak için Java IDE'i açın ve `SaleApp/ProductProgramming/` yolunu izleyin.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build8.png" />

Android Studio'da İstemci kaynak kodunu açın. Bunu yapmak için Android Studio'yu açın ve `SaleApp/SaleClient/` yolunu izleyin.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build1.png" />

### Adım 2. Java Uygulaması projesini oluşturma

Proje dosyasını çalışma alanınıza kaydettikten sonra, projenin üzerine sağ tıklayarak `Run As -> Maven Build` yolunu izleyin. Açılan pencere içerisindeki 'Goal' alanına `clean verify` etiketini girin. Ardından `Run` seçeneği ile çalıştırın. Gerekli yapılandırmaları yaptıktan sonra `pom.xml` dosyası eksik olan kütüphaneleri otomatik olarak indirir.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build9.png" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build10.png" />

### Step 3. Java Uygulamasını yükleme ve çalıştırma
`Run -> Run` yolunu izlemeniz yeterli olacaktır. 

### Adım 4. Android Uygulaması projesini oluşturma

`Build -> Make Project` ı seçin ve projenin başarıyla oluşturulduğunu kontrol edin. Android SDK sürümünü projenin ayarlarını yapılandırarak elde etmeniz gerekecek. Gerekli yapılandırmaları yaptıktan sonra `build.gradle` dosyası eksik olan kütüphaneleri indirmenizi ister.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build2.png" style="width: 40%" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build3.png" style="width: 60%" />

### Step 5. Android Uygulamasını yükleme ve çalıştırma

Android cihazı bilgisayara bağlayın ve telefonunuzda görünen ADB izin istemlerini onayladığınızdan emin olun. `Run -> Run app.` seçin. Bağlı cihazlarda uygulamanın yükleneceği cihaza dağıtım hedefini seçin. Bu, uygulamayı cihaza yükleyecektir. Uygulamanın doğru çalışması için iki uygulamanında aynı anda açık olması gerekmektedir.

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build4.png" style="width: 60%" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build5.png" style="width: 70%" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build6.png" style="width: 40%" />

<img src="https://github.com/BatuhanGunes/SaleApp/blob/master/images/build7.png" style="width: 80%" />

Uygulamayı test etmek için Android cihazınızda `SaleClient` adlı uygulamayı açın. Uygulamanın yeniden yüklenmesi, önceki yüklemelerin kaldırılmasını gerektirebilir.

## Yazarlar

* **Batuhan Güneş**  - [BatuhanGunes](https://github.com/BatuhanGunes)

Ayrıca, bu projeye katılan ve katkıda bulunanlara [contributors](https://github.com/BatuhanGunes/SaleApp/graphs/contributors) listesinden ulaşabilirsiniz.

## Lisans

Bu proje Apache lisansı altında lisanslanmıştır - ayrıntılar için [LICENSE.md](https://github.com/BatuhanGunes/SaleApp/blob/master/LICENSE) dosyasına bakabilirsiniz.
