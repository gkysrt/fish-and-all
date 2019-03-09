package com.example.android.fishall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class FishDetail extends AppCompatActivity {
    private final int totalFishCount = 20;
    ArrayList<Fish> allFish = new ArrayList<>();
    private int i;
    private int index;

    private String[] fishNames = {
            "Barbunya", "Çipura", "Hamsi", "İstavrit", "Kefal", "Kupes", "Levrek", "Lüfer", "Mezgit", "Orkinos",
            "Palamut", "Sardalya", "Uskumru", "Kalkan", "Mercan", "Kılıç Balığı", "Tekir", "Trança", "Kırlangıç", "Sinarit"
    };
    private String[] fishLatin = {
            "Mullus Barbutus", "Sparus Aurata", "Engraulis Encrasicolus", "Trachurus Mediterraneus", "Mugilidae",
            "Boops Boops", "Dicentrarchus", "Pomatomus Saltator", "Merlangius Merlangus", "Thunnus Thynnus", "Sarda Sarda",
            "Sardina Pilchardus", "Scomber Scombrus", "Scophthalmus Maximus", "Sparus Pagrus", "Xiphias Gladius", "Mullus Surmuletus",
            "Pagru Ehrenbergi", "Trigla Lucerna", "Dentex Dentex"
    };
    private String[] descriptions = {"Bir dip balığı olan barbunyanın boyu 40 cm’e kadar ulaşır. Kış aylarında sahile göçer. " +
            "Ekonomik değeri yüksek, lezzetli bir balıktır. " +
            "Kaya barbunyası, kum barbunyası, ot barbunyası ve paşa barbunu olmak üzere 4 çeşidi vardır.\n\n" +
            "Bunların içinde en makbulu kaya barbunyasıdır. Sırtı kırmızı ve karın kısmı beyaz olan" +
            " kaya barbununun sırtında hiç gri leke bulunmaz. Kum ve ot barbunyasında ise sırt gri ile kırmızı" +
            " renklerin karmaşası halindedir. Paşa barbununun her iki yanında çeneden kuyruğa doğru sarı" +
            " bir şerit bulunur. Tekir balığı ile çok karıştırılır. Tekir çene altı bıyıklarının uzunluğu," +
            " küt kafası ve birinci sırt yüzgeçindeki sarı siyah benekleri ile barbunyadan ayrılır." +
            " Barbunyanın balığın en lezzetli zamanı Ağustos ile Ekim ayları arasıdır." +
            " Ekim en yağlı ve lezzetli olduğu aydır.",

            "Yarım kiloya yakın olanlarına lidaki denir." +
                    " Akdeniz ve Ege’de yaygındır. Kültür balıkçılığına uygun olduğu için genellikle" +
                    " balıkçı tezgahlarında çiftlik çipurası bulunur. Tezgahlarda deniz çipurası çiftlik çipurasına" +
                    " göre daha az, ama daha lezzetlidir. Temmuz’dan Kasım’a kadar lezzetli olduğu aylardır." +
                    " Ama en lezzetli olduğu aylar Eylül ve Ekim’dir.",

            "Türkiye’de en çok avlanan deniz balığıdır. Ağırlıklı olarak Karadeniz’den ve Marmara’dan avlanır." +
                    " Karadeniz hamsisi Azak ve Karadeniz olmak üzere ikiye ayrılır. Azak hamsisinin burnu daha küttür." +
                    " Azak Denizi’nde üreyip kışlamak üzere güneye, bizim Orta ve Doğu Karadeniz bölgesine inerler." +
                    " Nisan sonunda da kuzeye göç ederler.Karadeniz hamsisi ise kuzeybatı Karadeniz’de ürer," +
                    " kışlamak üzere Kasım’dan Şubat’a kadar Trakya kıyılarına ve Marmara’ya göç eder." +
                    " Nisan ayında da yumurtlamak üzere Karadeniz’e çıkar.Ayrıca Marmara Hamsisi denilen," +
                    " yalnız Marmara’da çıkan, daha küçük ve göç etmeyen bir hamsi türü de vardır." +
                    " Ocak, Şubat, Kasım, Aralık en lezzetli olduğu aylardır.",

            "Karasularımızda 2 türü vardır: Karagöz istavrit ve sarıkuyruk istavrit." +
                    " Küçüğüne kraça adı verilir. Sarıkuyruk, karagöze göre daha büyüktür." +
                    " Sonbahar ve kış aylarında tüketilir ama Ekim ve Kasım en lezzetli olduğu aylardır.",

            "Kefal aslında 100’e yakın balık türünün ortak adıdır. Denizlerimizde 6 türü yaşıyor:" +
                    " Has kefal, altınbaş kefal, dudaklı kefal, mavri kefal, sivriburun kefal, rus kefali." +
                    " Küçüğüne ilerya denir. En lezzetlisi has kefal ve altınbaş kefaldir." +
                    " Nisan, Mayıs, kış ayları kefalin lezzetli olduğu dönemlerdir.",

            "Akdeniz, Ege, Marmara’da yaygındır. Deniz suyu sıcaklığı arttıkça yaşam alanları da genişler." +
                    " Kupesin gözleri kocamandır ve biraz dikkatli bakarsanız gözlerinin üstünde sanki" +
                    " göz kapağıymış gibi kapanıp açılan saydam zarı görebilirsiniz. Kupes balıklarının ortalama boylan 20 cm’dir." +
                    " 35- 40 cm’yi bulan iri kupeslere de rastlanır. Eti oldukça lezzetlidir. Haziran- Eylül arasında en lezzetli aylarıdır.",

            "Fazla gezici olmayan yerli balıklardan sayılır. Lezzetli ve ekonomik değeri yüksek olduğundan," +
                    " deniz çiftliklerinde de üretilmektedir. Şubat-Mayıs arasında en lezzetli zamanıdır." +
                    " Bayağı levrek ve benekli levrek olmak üzere iki tipi mevcuttur. " +
                    "Sırtlarındaki çok sayıda benek ile ayrılırlar. Benekli levrek Güney Ege ve Akdeniz’de," +
                    " bayağı levrek ise bütün denizlerimizde görülür." +
                    " Karadeniz’de kötek olarak da bilinen minekop da bu familyanın diğer bir türüdür.",

            "Büyüklüğüne göre lüferin çeşitleri şöyledir:\n" +
                    "\n" +
                    "10 cm’ye kadar -> yaprak / 11-13 cm arası -> çinekop / 14-16 cm arası -> kaba çinekop\n" +
                    "17-20 cm arası -> sarıkanat / 21-30 cm arası -> lüfer / 1-35 cm arası -> kaba lüfer\n" +
                    "35 cm’den büyük -> kofana\n" +
                    "Karadeniz ve Marmara’da bulunur. Ağustos, Eylül, Ekim en lezzetli aylarıdır." +
                    " Soğuk denizde yaşayanları daha lezzetli olur. Türkiye’de ise Karadeniz ve Marmara’da avlananları daha lezzetlidir.",

            "Daha çok Marmara ve Karadeniz balığıdır. Türkiye’de 2 türü vardır." +
                    " Türlerden birinde alt çenesinde küçük bıyık vardır." +
                    " Tavuk balığı olarak da bilinen mezgit bütün denizlerimizde bulunmakla beraber en çok Karadeniz’de bulunur." +
                    " Şubat, Mart, Nisan ayları arasında lezzetlidir.",

            "Tonbalığı olarak da bilinir. Eti konservecilikte kullanılır." +
                    " Genç orkinoslar sürüler halinde palamutlarla birlikte Karadeniz’den Akdeniz’e göçerler." +
                    " En bilinen türleri sarıkanat orkinos, güney mavi yüzgeçli orkinos, çizgili orkinos," +
                    " uzun kanat orkinos ve kocagöz orkinos gibi türleridir.\n" +
                    "\n" +
                    "Nisan’da Akdeniz’e giren orkinoslara, 1 Mayıs ile 15 Temmuz arasında âdeta bir avcı ordusu saldırıyor." +
                    " Yaklaşık 50 tonluk yüzer kafeslerle yakalanan orkinoslar, balık çiftliklerine getiriliyor." +
                    " Mayıs-Kasım arasında canlı balıklarla beslenen orkinoslar, bu süre içinde yaklaşık yüzde 50 oranında kilo alıyor." +
                    " Kanlı görüntülerle iç sızlatan hasattan sonra orkinos etleri çoğunlukla Japonya’ya ihraç ediliyor." +
                    " Mavi yüzgeçli orkinos, ekonomik ve ekolojik anlamda son derece değerli bir tür olmasına karşın," +
                    " aşırı avlanma nedeniyle hızla yok olma tehlikesi altında.",

            "Denizlerimizde giderek azalan balık türlerindendir. Bizans İmparatorluğu’nda şehrin sembollerindenmiş." +
                    " Ağustos’tan Şubat ortalarına kadar en lezzetli olduğu aylardır.\n" +
                    "\n" +
                    "Palamut boyuna göre şöyle isimlendirilir:\n" +
                    "\n" +
                    "20 cm’ye kadar -> palamut vanozu / 20-30 cm arası -> çingene palamutu / 31-40 cm arası -> palamut\n" +
                    "40-50 cm arası -> kestane palamutu / 51-60 cm arası -> torik / 61-65 arası -> sivri65-70 cm arası -> altıparmak\n" +
                    "70 cm’den büyük -> zindan delen\n" +
                    "\n" +
                    "Torik ve toriğin büyük boyları palamuttan daha çok yağlıdır," +
                    " bu nedenle tuzlama ve lakerdası tercih edilir. Palamut avı Ağustos ayında başlar." +
                    " Önce Karadeniz’de sürüler halinde vanoz ve çingene palamutu, Eylül’den itibaren de palamut gelmeye başlar.",

            "Akdeniz, Kuzey Ege, Marmara’da bol bulunur. Son yıllarda suların ısınması nedeniyle Karadeniz’de de görülmeye başlanmıştır." +
                    " Hamsinin yakın akrabası sardalya kurutularak," +
                    " tuzlanarak hatta balık yağı ve balık unu elde etmekte kullanılır.\n" +
                    "\n" +
                    "Sardalya adı konserve işleminden dolayı konserve ile özdeşleşmiştir." +
                    " Sardalyanın küçüğüne papalina denilir, ayıklamadan kızartılır." +
                    " Tirsi ise sardalyanın büyüğüne denilir. Kıl tarzında çok kılçığı vardır ve sardalya kadar lezzetli değildir." +
                    " Sürü halinde yazın orta, kışın derin sularda yaşayan gezici balıklardır." +
                    " Eskiden deniz üstünün ateş aydınlatmasıyla avcılığından ötürü ateş balığı diye de tanınır." +
                    " En lezzetli ayları Temmuz-Ekim arasıdır.",

            "1970’li yıllarda kışlak yeri olan Marmara-Karadeniz’i aşırı kirlenme nedeniyle terk ederek Kuzey Ege’ye yerleşti." +
                    " Eti çok lezzetlidir. Bol avlanılan uskumru, şimdilerde ekolojik nedenlerle seyrek rastlanan adeta tükenmiş bir balıktır." +
                    " Kolyoza çok benzeyen uskumru büyüklüğüne göre üç değişik ad ile adlandırılır.\n" +
                    "\n" +
                    "En küçüğüne kalinarya denir. 20-25 cm civarında ve yağlı olanları uskumru," +
                    " dönüş uskumrusu ise çiroz olarak adlandırılır. Yazın yakalananlara ise lipari denir." +
                    " En lezzetli olduğu dönemler yumurtlamaya başladığı Eylül’den Ocak ayı sonuna kadardır.",

            "Bir dip balığı olan kalkan, Karadeniz’in en tanınmış balıklarındandır. Boğazlar, Marmara, Ege ve Akdeniz’de seyrek rastlanır." +
                    " Gezici balık değildir. Bütün hayatı dipte yatmakla geçer.\n" +
                    "\n" +
                    "Her iki tarafı siyah olan kaya kalkanına nadiren rastlanır. Kaya kalkanı daha çok Sinop," +
                    " Samsun yörelerinde çıkar. İstanbul Boğazı’nın kuzeyinde," +
                    " Karadeniz’in batısında avlanan kalkan bir tarafı siyah, bir tarafı beyaz olan bayağı kalkandır." +
                    " En lezzetli zamanı Ocak sonundan Mart ortalarına kadardır.Karadeniz’in bu ünlü balığı bütün yassı," +
                    " oval vücudu, bir tarafı siyaha yakın diğer tarafı beyaz rengi ve beyaz tarafındaki düğmeleri ile tanınır.",

            "Açık kırmızı renkli, eti beyaz, pullu çok lezzetli kıymetli bir balıktır.\n" +
                    "\n" +
                    "Marmara, Akdeniz, Ege’de yaygındır. 4 türü vardır: Kırma mercan, lekeli mercan, çizgili mercan, fangri mercan." +
                    " Kırma ve lekeli mercanın en lezzetlisi ve ekonomik değeri yüksek olanıdır." +
                    " En lezzetli olduğu aylar Nisan’dan Haziran’a kadar geçen süredir.",

            "Sıcaklığa göre Marmara ve Karadeniz arasında dolaşıp Akdeniz’e göçerler. Açık deniz balığıdır." +
                    " 800 m derinliğe inebilir. Türkiye’yi çevreleyen denizlerde artık nadir görülen çok lezzetli bir balık türüdür." +
                    " Akdeniz ve Ege’de yıl boyunca, Karadeniz’de ise yalnız yaz ayları görülür." +
                    " Her mevsimde yenebilen kılıçın en lezzetli zamanı Eylül-Şubat arasıdır.\n" +
                    "\n",

            "Etinin lezzeti Eski Roma çağlarından beri namlıdır. Bol avlanılan, ekonomik değeri yüksek bir balıktır." +
                    " Dört mevsim yenebilecek bu balığın en lezzetli zamanı, aynen barbunyada olduğu gibi Temmuz-Ekim ayları arasıdır." +
                    " Çoğu zaman birbiri ile karıştırılan iki kuzendir barbunya ve tekir.\n" +
                    "\n" +
                    "Yaşam alanları, renkleri, biçimleri hemen hemen birbiri ile aynıdır." +
                    " Dikkatli olmayan gözler aradaki farkı kolay kolay anlamazlar." +
                    " Tekirin başı daha uzuncadır ve tabi sırt yüzgecinde bulunan hareler ise oldukça belirgindir." +
                    " Doğal kafa yapılarından dolayı barbunyaların ağızları göz hizasına kadar gelir," +
                    " fakat tekirlerde bu böyle değildir. Kafalarının şekline nazaran sırt yüzgeçlerinde hare" +
                    " olup olmayışı daha kolay ayırt edilebilir.",

            "Mercan ailesinden ve Fangri ile aynı türdendir. Ege’nin ünlü balığıdır. Kabukluyu kırıp yiyebilir." +
                    " Hermafrodit, hem erkek hem de dişi karakteri gösterir. Boyu 30 – 50, en çok 75 cm olur." +
                    " Eti beyaz ve lezzetlidir. Geçmişte aşırı avlanılması neslini çok azaltmıştır." +
                    " Ağustos-Eylül arasında eti lezzetlidir.\n" +
                    "\n",

            "Çok gelişmiş solungaçları ve gırtlak yapısı medeniyle uğultu," +
                    " inilti gibi değişik frekanslarda ses çıkarırlar." +
                    " Bu ses nedeniyle birçok balıkçı tarafından inleyen balık diye adlandırılır ve uğursuz sayılır." +
                    " Ege, Akdeniz ve Marmara’nın fazla göç etmeyen, yerli balığıdır." +
                    " Kısmen Karadeniz’de rastlanır. Ilık denizlerin sahil yakınlarında" +
                    " 5 – 300 m derinliklerin diplerinde çiftler halinde yaşar." +
                    " Küçüklerine derviş balığı da denir. Özellikle çorbası çok lezzetli olur.\n" +
                    "\n",

            "Sinarit balıkları 20 yaşından sonra yavaş büyürler. 20. yaştan sonraki 10 senede sadece 5 cm boy atarlar." +
                    " Nadiren 1 metre boya ve 10 kg ağırlığa ulaşırlar. Ama çok iri sinarit lezzetli olmaz.\n\n" +
                    "Sinarit sivri dişlere ve kuvvetli bir çeneye sahiptir. Ege Denizi ve Akdeniz’in bazı yerleri," +
                    " Ayvalık-Dikili arası, Marmara Adası civarı, Kapıdağı sahilleri, Tekirdağ, Saroz körfezi," +
                    " Çanakkale Boğaz’ında bulunurlar. Mercana benzer ama başı daha küçük ve uzundur. Ensesi basıktır." +
                    " Yine mercana oranla vücut yüksekliği daha az ve daha uzundur. Mercanın kırmızılığı yerine sinarit eflatun renkler taşır." +
                    " Temmuz’dan Eylül sonuna kadar en lezzetli zamanıdır."
    };

    private String[] fishLimit = {"13 cm", "20 cm", "9 cm", "13 cm", "20 cm", "yasal sınır yok.", "25 cm", "14 cm", "13 cm",
            "yasal sınır yok.", "25 cm", "11 cm", "20 cm", "40 cm", "yasal sınır yok.", "125 cm",
            "11 cm", "yasal sınır yok.", "yasak sınır yok.", "yasal sınır yok."};

    private int[] fish_images = {R.drawable.barbun,R.drawable.cipura,R.drawable.hamsi,R.drawable.istavrit,
            R.drawable.kefal,R.drawable.kupus,R.drawable.levrek,R.drawable.lufer,R.drawable.mezgit,
            R.drawable.orkinos,R.drawable.palamut,R.drawable.sardalya,R.drawable.uskumru,R.drawable.kalkan,
            R.drawable.mercan,R.drawable.kilic_baligi,R.drawable.tekir,R.drawable.tranca,R.drawable.kirlangic,R.drawable.sinarit};
    private int[] fish_maps = {R.drawable.barbun_map, R.drawable.cipura_map, R.drawable.hamsi_map, R.drawable.istavrit_map,
            R.drawable.kefal_map, R.drawable.kupes_map,R.drawable.levrek_map, R.drawable.lufer_map, R.drawable.mezgit_map, R.drawable.orkinos_map,
            R.drawable.palamut_map, R.drawable.sardalya_map, R.drawable.uskumru_map, R.drawable.kalkan_map,R.drawable.mercan_map,R.drawable.kilic_baligi_map,
            R.drawable.tekir_map,R.drawable.tranca_map,R.drawable.kirlangic_map,R.drawable.istavrit_map};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_detail);


        String clickedItemIndex=null;
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            /*
             * Checked to make sure the extra we are looking for is contained within
             * the Intent then we get the index number from FishEncyclopedia class.
             */
            clickedItemIndex = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);

        }
        createFishDB();

        index = Integer.parseInt(clickedItemIndex);


    }

    @Override
    protected void onStart() {
        super.onStart();

        /**
         * Created contents of screen and linked to their matches in the XML file.
         */
        TextView fish_title = (TextView) findViewById(R.id.fish_title);
        ImageView fish_img = (ImageView) findViewById(R.id.fish_picture);
        TextView fish_latin = (TextView) findViewById(R.id.fish_latin);
        TextView fish_description = (TextView) findViewById(R.id.description);
        ImageView fish_map = (ImageView) findViewById(R.id.fish_map);

        fish_title.setText(allFish.get(index).getName());
        fish_img.setImageResource(allFish.get(index).getImage());
        fish_latin.setText(allFish.get(index).getLatinName());
        fish_description.setText(allFish.get(index).getDescription());
        fish_map.setImageResource(allFish.get(index).getMap());
    }

    public void createFishDB() {
        for (int i = 0; i < totalFishCount; i++) {

            /**
             * Creates a database for the fishes
             */

            Fish newFish = new Fish();

            newFish.setName(fishNames[i]);
            newFish.setLatinName(fishLatin[i]);
            newFish.setDescription(descriptions[i]);
            newFish.setSize(fishLimit[i]);
            newFish.setImage(fish_images[i]);
            newFish.setMap(fish_maps[i]);

            allFish.add(newFish);
        }
    }


}
