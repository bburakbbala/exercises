/****************************************************************************
**				SAKARYA ÜNİVERSİTESİ
**				BİLGİSAYAR VE BİLİŞİM BİLİMLERİ FAKÜLTESİ
**				BİLGİSAYAR MÜHENDİSLİĞİ BÖLÜMÜ
**				NESNEYE DAYALI PROGRAMLAMA DERSİ
**				2020-2021 YAZ DÖNEMİ
**
**				ÖĞRENCİ ADI............: Burak Bala
**				ÖĞRENCİ NUMARASI.......: G181210058
**
****************************************************************************/

using Proje.Library.Abstract;
using Proje.Library.Enum;
using Proje.Library.Interface;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace Proje.Library.Concrete
{
    public class Oyun : IOyun
    {
        #region Fields

        private readonly Timer _kontrolTimer = new Timer { Interval = 50 };
        private readonly Timer _balonHareketTimer = new Timer { Interval = 70 };
        private readonly Timer _okHareketTimer = new Timer { Interval = 50 };
        private readonly Timer _yesilBalonOlusturmaTimer = new Timer { Interval = 2155 };
        private readonly Timer _sariBalonOlusturmaTimer = new Timer { Interval = 3033 };
        private readonly Timer _kirmiziBalonOlusturmaTimer = new Timer { Interval = 5017 };
        private readonly Timer _asamaBirTimer = new Timer { Interval = 100 };
        private readonly Timer _asamaIkiTimer = new Timer { Interval = 50 };
        private readonly Timer _asamaUcTimer = new Timer { Interval = 40 };
        private TimeSpan _hareketSure;
        private readonly Panel _okcuPanel;
        private readonly Panel _savasAlaniPanel;
        private Okcu _okcu;
        private readonly List<Ok> _oklar = new List<Ok>();
        private readonly List<Balon> _balonlar = new List<Balon>();

        private int _olusturulanOkAdedi = 0;
        private int _balonYesilAdet = 0;
        private int _balonSariAdet = 0;
        private int _balonKirmiziAdet = 0;
        private int _olusturulanBalonAdedi = 0;
        private bool _ikinciAsamaBittiMi = false;
        private bool _ucuncuAsamaBittiMi = false;
        private int _ucuncuAsamaZemineUlasanBalonAdedi = 0;
        private int _vurulanBalonAdedi = 0;
        private int _sahnedenKaldirilanOkAdedi = 0;
        private string _zeminRengi;
        public static readonly string _continuGame = "continue-game.txt";
        public static readonly string _savedGames = "saved-games.txt";
        public static readonly string _savedOptions = "saved-options.txt";

        #endregion

        #region Events

        public event EventHandler SureDegisti;

        #endregion

        #region Properties
        public TimeSpan HareketSure
        {
            get => _hareketSure;
            private set
            {
                _hareketSure = value;
                SureDegisti?.Invoke(this, EventArgs.Empty);
            }
        }

        public bool DevamEdiyorMu { get; private set; }
        public static string AsamaLabel { get; private set; }
        public static bool OyunBittiMi { get; private set; }
        public static bool OyunBasladiMi { get; private set; }
        private bool OkBittiMi { get; set; }
        public int UcuncuAsamaOkAdedi { get; private set; }
        public static bool OyunKaydedildiMi { get; private set; }
        public static int Puan { get; private set; }
        public static string OyuncuIsmi { get; private set; }
        public static string KacinciAsama { get; private set; }
        public static bool OkcuErkekMi { get; private set; }
        public static string OkcuCinsiyet { get; private set; }
        public static bool OkAtesliMi { get; private set; }
        public int KayitliAyarlarIndex { get; private set; }
        public string OkCesidi { get; private set; }
        public bool KayitliOyunMu { get; private set; }
        #endregion

        #region Methods

        public Oyun(Panel okcuPanel, Panel savasAlaniPanel, int oncekiOyunlarSeciliOyunIndex)
        {
            KayitliOyunMu = true;
            string[] lines = File.ReadAllLines(_continuGame);
            string[] game = lines[oncekiOyunlarSeciliOyunIndex].Split(',');
            OyuncuIsmi = game[0];
            OkcuCinsiyet = game[1];
            OkCesidi = game[2];
            Puan = int.Parse(game[3]);
            _zeminRengi = game[4];
            _okcuPanel = okcuPanel;
            _savasAlaniPanel = savasAlaniPanel;
            _kontrolTimer.Tick += KontrolTimer_Tick;
            _balonHareketTimer.Tick += BalonHareketTimer_Tick;
            _okHareketTimer.Tick += OkHareketTimer_Tick;
            _yesilBalonOlusturmaTimer.Tick += YesilBalonOlusturmaTimer_Tick;
            _sariBalonOlusturmaTimer.Tick += SariBalonOlusturmaTimer_Tick;
            _kirmiziBalonOlusturmaTimer.Tick += KirmiziBalonOlusturmaTimer_Tick;
            _asamaBirTimer.Tick += AsamaBirTimer_Tick;
            _asamaIkiTimer.Tick += AsamaIkiTimer_Tick;
            _asamaUcTimer.Tick += AsamaUcTimer_Tick;
            Baslat();
        }

        public Oyun(Panel okcuPanel, Panel savasAlaniPanel, string oyuncuIsmi, bool erkekOkcuMu, bool okAtesliMi)
        {
            _okcuPanel = okcuPanel;
            _savasAlaniPanel = savasAlaniPanel;
            _kontrolTimer.Tick += KontrolTimer_Tick;
            _balonHareketTimer.Tick += BalonHareketTimer_Tick;
            _okHareketTimer.Tick += OkHareketTimer_Tick;
            _yesilBalonOlusturmaTimer.Tick += YesilBalonOlusturmaTimer_Tick;
            _sariBalonOlusturmaTimer.Tick += SariBalonOlusturmaTimer_Tick;
            _kirmiziBalonOlusturmaTimer.Tick += KirmiziBalonOlusturmaTimer_Tick;
            _asamaBirTimer.Tick += AsamaBirTimer_Tick;
            _asamaIkiTimer.Tick += AsamaIkiTimer_Tick;
            _asamaUcTimer.Tick += AsamaUcTimer_Tick;
            OkcuErkekMi = erkekOkcuMu;
            OyuncuIsmi = oyuncuIsmi;
            OkAtesliMi = okAtesliMi;
            //MessageBox.Show("Herhangi bir anda oyunu kaydetmek için CTRL+S'e basınız.");
        }

        private void AsamaBirTimer_Tick(object sender, EventArgs e)
        {
            AsamaBir();
        }
        private void AsamaIkiTimer_Tick(object sender, EventArgs e)
        {
            Asamaİki();
        }
        private void AsamaUcTimer_Tick(object sender, EventArgs e)
        {
            AsamaUc();
        }
        private void KontrolTimer_Tick(object sender, EventArgs e)
        {
            HareketSure += TimeSpan.FromMilliseconds(_balonHareketTimer.Interval);
        }
        private void BalonHareketTimer_Tick(object sender, EventArgs e)
        {
            BalonlariHareketEttir(Yon.Asagi);
            VurulanBalonlariCikar();
            if (_ikinciAsamaBittiMi && (!_ucuncuAsamaBittiMi || !OyunBittiMi)) UcuncuAsamaZemineUlasanBalonlariSahnedenCikar();
        }
        private void OkHareketTimer_Tick(object sender, EventArgs e)
        {
            OklariHareketEttir(Yon.Saga);
        }

        private void YesilBalonOlusturmaTimer_Tick(object sender, EventArgs e)
        {
            YesilBalonOlusturma();
            _balonYesilAdet++;
            _olusturulanBalonAdedi++;
        }
        private void SariBalonOlusturmaTimer_Tick(object sender, EventArgs e)
        {
            SariBalonOlusturma();
            _balonSariAdet--;
            _olusturulanBalonAdedi++;
        }
        private void KirmiziBalonOlusturmaTimer_Tick(object sender, EventArgs e)
        {
            KirmiziBalonOlusturma();
            _balonKirmiziAdet--;
            _olusturulanBalonAdedi++;
        }
        public void Baslat()
        {
            if (OyunBasladiMi) return;
            DevamEdiyorMu = true;
            OyunBasladiMi = true;
            KacinciAsama = "Birinci Aşama";
            AsamaLabel = $"Oyuncu : {OyuncuIsmi} [{KacinciAsama}]";
            ZamanlayicilariBaslat();
            OkcuOlustur();
            if (!KayitliOyunMu)
                DegiskenleriSifirla();
        }
        private void ZamanlayicilariBaslat()
        {
            _kontrolTimer.Start();
            _balonHareketTimer.Start();
            _okHareketTimer.Start();
            _asamaBirTimer.Start();
            _yesilBalonOlusturmaTimer.Start();
        }
        public void YenidenBaslat()
        {
            if (!OyunBittiMi) return;
            _okcuPanel.Controls.Remove(_okcu);
            KayitliOyunMu = false;
            OyunBasladiMi = false;
            SahneyiTemizle();
            ZamanlayicilariDurdur();
            Baslat();
        }

        private void DegiskenleriSifirla()
        {
            _balonYesilAdet = 0;
            _balonSariAdet = 0;
            _balonKirmiziAdet = 0;
            _olusturulanBalonAdedi = 0;
            _ikinciAsamaBittiMi = false;
            _ucuncuAsamaBittiMi = false;
            UcuncuAsamaOkAdedi = 50;
            _ucuncuAsamaZemineUlasanBalonAdedi = 0;
            _vurulanBalonAdedi = 0;
            _olusturulanOkAdedi = 0;
            _sahnedenKaldirilanOkAdedi = 0;
            _balonHareketTimer.Interval = 70;
            OyunBasladiMi = false;
            Puan = 0;
            UcuncuAsamaOkAdedi = 50;
            OkBittiMi = false;
            OyunBittiMi = false;
            OyunKaydedildiMi = false;
        }

        private void SahneyiTemizle()
        {
            for (int i = _balonlar.Count - 1; i >= 0; i--)
            {
                Balon balon = _balonlar[i];
                _balonlar.Remove(balon);
                _savasAlaniPanel.Controls.Remove(balon);
            }
            // oklar temizleniyor
            if (_olusturulanOkAdedi != 0)
            {
                for (int i = _oklar.Count - 1; i >= 0; i--)
                {
                    Ok ok = _oklar[i];
                    _oklar.Remove(ok);
                    _savasAlaniPanel.Controls.Remove(ok);
                }
            }
        }
        private void BalonlariHareketEttir(Yon asagi)
        {
            foreach (var balon in _balonlar)
            {
                bool carptimi = balon.HareketEttir(asagi);
                bool kirmiziBalonMu = balon is BalonKirmizi;
                bool yesilBalonMu = balon is BalonYesil;
                bool sariBalonMu = balon is BalonSari;
                if (_ikinciAsamaBittiMi && (yesilBalonMu || sariBalonMu || kirmiziBalonMu)) continue; // ucuncu asamada balonlar zemine carparsa oyun durmaz
                if (!carptimi) continue; //balon carpmazsa oyun durmaz
                // oyunu bitirir balon zemine çarparsa
                Bitir();
                break;
            }
        }
        private void YesilBalonOlusturma()
        {
            BalonYesil balonYesil = new BalonYesil(_savasAlaniPanel.Size);
            _balonlar.Add(balonYesil);
            _savasAlaniPanel.Controls.Add(balonYesil);
        }
        private void SariBalonOlusturma()
        {
            BalonSari balonSari = new BalonSari(_savasAlaniPanel.Size);
            _balonlar.Add(balonSari);
            _savasAlaniPanel.Controls.Add(balonSari);
        }
        private void KirmiziBalonOlusturma()
        {
            BalonKirmizi balonKirmizi = new BalonKirmizi(_savasAlaniPanel.Size);
            _balonlar.Add(balonKirmizi);
            _savasAlaniPanel.Controls.Add(balonKirmizi);
        }
        private void AsamaBir()
        {
            if (_balonYesilAdet == 20)
            {
                _yesilBalonOlusturmaTimer.Stop();
                _balonSariAdet = (int)(_balonYesilAdet * 0.3);
                _asamaBirTimer.Stop();
                _asamaIkiTimer.Start();
            }
        }
        private void Asamaİki()
        {
            if (_vurulanBalonAdedi == 20)
            {
                _balonHareketTimer.Interval = 50;
                _yesilBalonOlusturmaTimer.Start();
                _sariBalonOlusturmaTimer.Start();
                KacinciAsama = "İkinci Aşama";
                AsamaLabel = $"Oyuncu : {OyuncuIsmi} [{KacinciAsama}]";
            }
            if (_balonSariAdet < 1)
            {
                _yesilBalonOlusturmaTimer.Stop();
                _sariBalonOlusturmaTimer.Stop();
                _balonSariAdet = (int)(_balonYesilAdet * 0.2);
                _balonKirmiziAdet = (int)(_balonYesilAdet * 0.2);
                _asamaIkiTimer.Stop();
                _asamaUcTimer.Start();
            }
        }
        private void AsamaUc()
        {
            if (_vurulanBalonAdedi == _olusturulanBalonAdedi)
            {
                _balonHareketTimer.Interval = 40;
                _yesilBalonOlusturmaTimer.Start();
                _sariBalonOlusturmaTimer.Start();
                _kirmiziBalonOlusturmaTimer.Start();
                _ikinciAsamaBittiMi = true;
            }
            if (_ikinciAsamaBittiMi) AsamaLabel = $"Oyuncu : {OyuncuIsmi} [{KacinciAsama = "Üçüncü Aşama"}] [Ok adedi : {UcuncuAsamaOkAdedi}]";
            if (_balonKirmiziAdet < 1 && !OyunBittiMi)
            {
                _yesilBalonOlusturmaTimer.Stop();
                _sariBalonOlusturmaTimer.Stop();
                _kirmiziBalonOlusturmaTimer.Stop();
                if (_vurulanBalonAdedi + _ucuncuAsamaZemineUlasanBalonAdedi == _olusturulanBalonAdedi)
                {
                    _ucuncuAsamaBittiMi = true;
                    Bitir();
                }
            }
        }
        private void VurulanBalonlariCikar()
        {
            for (int i = _balonlar.Count - 1; i >= 0; i--)
            {
                Balon balon = _balonlar[i];
                bool vurulduMu = balon.VurulduMu(_oklar);
                if (!vurulduMu) continue;
                if (balon is BalonYesil) Puan += 5;
                if (balon is BalonSari) Puan += 15;
                if (balon is BalonKirmizi) Puan = 0;
                _vurulanBalonAdedi++;
                _balonlar.Remove(balon);
                _savasAlaniPanel.Controls.Remove(balon);
            }
        }
        private void UcuncuAsamaZemineUlasanBalonlariSahnedenCikar()
        {
            for (int i = _balonlar.Count - 1; i >= 0; i--)
            {
                Balon balon = _balonlar[i];
                if (balon.Bottom == _savasAlaniPanel.Height)
                {
                    _balonlar.Remove(balon);
                    _savasAlaniPanel.Controls.Remove(balon);
                    _ucuncuAsamaZemineUlasanBalonAdedi++;
                }
            }
        }
        private void OkcuOlustur()
        {
            if (!DevamEdiyorMu) return;
            if (KayitliOyunMu)
            {
                OkcuErkekMi = false;
                if (OkcuCinsiyet == "Erkek Okçu") OkcuErkekMi = true;
            }
            _okcu = new Okcu(_okcuPanel.Height, _okcuPanel.Size, OyuncuIsmi, Puan, OkcuErkekMi);
            _okcuPanel.Controls.Add(_okcu);
        }
        public void OkcuyuHareketEttir(Yon yon)
        {
            if (!DevamEdiyorMu) return;
            _okcu.HareketEttir(yon);
        }
        public void OkAt()
        {
            if (!DevamEdiyorMu) return;
            if (!OyunBittiMi && !OkBittiMi)
            {
                if (KayitliOyunMu)
                {
                    OkAtesliMi = false;
                    if (OkCesidi == "Ateşli Ok") OkAtesliMi = true;
                }
                Ok ok = new Ok(_savasAlaniPanel.Size, _okcu.YayMiddle, OkAtesliMi);
                _oklar.Add(ok);
                _savasAlaniPanel.Controls.Add(ok);
                _olusturulanOkAdedi++;
                if (_ikinciAsamaBittiMi) UcuncuAsamaOkAdedi--;
            }
            if (_ikinciAsamaBittiMi)  // üçüncü aşama başladığında ok adedi 50 ile kısıtlanır
            {
                if (UcuncuAsamaOkAdedi < 1)
                {
                    OkBittiMi = true;
                    // ok bittikten sonra hareket eden ok kalmayınca oyunu bitirir
                    if (_olusturulanOkAdedi == _sahnedenKaldirilanOkAdedi) Bitir();
                }
            }
        }
        public void OklariHareketEttir(Yon yon)
        {
            for (int i = _oklar.Count - 1; i >= 0; i--)
            {
                Ok ok = _oklar[i];
                bool carptiMi = ok.HareketEttir(yon);
                if (carptiMi)
                {
                    _oklar.Remove(ok);
                    _savasAlaniPanel.Controls.Remove(ok);
                    _sahnedenKaldirilanOkAdedi++;
                }
            }
        }
        public void Bitir()
        {
            if (!DevamEdiyorMu) return;
            DevamEdiyorMu = false;
            OyunBittiMi = true;
            KayitliOyunMu = false;
            ZamanlayicilariDurdur();
        }
        private void ZamanlayicilariDurdur()
        {
            _balonHareketTimer.Stop();
            _okHareketTimer.Stop();
            _yesilBalonOlusturmaTimer.Stop();
            _sariBalonOlusturmaTimer.Stop();
            _kirmiziBalonOlusturmaTimer.Stop();
            _asamaBirTimer.Stop();
            _asamaIkiTimer.Stop();
            _asamaUcTimer.Stop();
        }
        public void OyunuKaydet()
        {
            Bitir();
            MessageBox.Show("Oyun ve Ayarlar Kaydedildi.");
            FileStream fileStream = new FileStream(_savedGames, FileMode.Append, FileAccess.Write);
            StreamWriter streamWriter = new StreamWriter(fileStream);
            streamWriter.WriteLine($"Oyuncu İsmi: {OyuncuIsmi}\t\tPuan: {Puan}\t\tSeviye: {KacinciAsama}");

            FileStream fileStream2 = new FileStream(_savedOptions, FileMode.Append, FileAccess.Write);
            StreamWriter streamWriter2 = new StreamWriter(fileStream2);
            OkCesidi = OkAtesliMi ? "Ateşli Ok" : "Normal Ok";
            streamWriter2.WriteLine($"{OkcuCinsiyet}\t{OkCesidi}\t{_zeminRengi}");

            FileStream fileStream3 = new FileStream(_continuGame, FileMode.Append, FileAccess.Write);
            StreamWriter streamWriter3 = new StreamWriter(fileStream3);
            streamWriter3.WriteLine($"{OyuncuIsmi},{OkcuCinsiyet},{OkCesidi},{Puan},{_zeminRengi}");

            streamWriter.Close();
            fileStream.Close();
            streamWriter2.Close();
            fileStream2.Close();
            streamWriter3.Close();
            fileStream3.Close();
            OyunKaydedildiMi = true;
        }
        public void OyunAyarlari(string oyuncuIsmi, bool erkekOkcuMu, bool okAtesliMi, Color zeminRengi)
        {
            OyuncuIsmi = oyuncuIsmi;
            OkAtesliMi = okAtesliMi;
            _zeminRengi = zeminRengi.ToString();
            OkcuCinsiyet = erkekOkcuMu ? "Erkek Okçu" : "Kadın Okçu";
        }
        #endregion

    }
}
