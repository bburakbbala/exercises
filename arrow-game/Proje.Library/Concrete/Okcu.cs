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
using System.Drawing;

namespace Proje.Library.Concrete
{
    internal class Okcu : Oyuncu
    {
        private bool ErkekOkcuMu { get; set; }
        public static string OkcuCinsiyet { get; private set; }

        public Okcu(int panelYuksekligi, Size hareketAlaniBoyutlari, string oyuncuIsmi, int oyuncuPuani, bool erkekOkcuMu) : base(hareketAlaniBoyutlari)
        {
            string okcu;
            ErkekOkcuMu = erkekOkcuMu;
            if (ErkekOkcuMu)
            {
                OkcuCinsiyet = "Erkek";
                okcu = @"Resources\Images\archer-male.png";
            }
            else
            {
                OkcuCinsiyet = "Kadın";
                okcu = @"Resources\Images\archer-female.png";
            }
            Image = Image.FromFile(okcu);
            Middle = panelYuksekligi / 2;
            HareketMesafesi = (int)(Height / 6);
            OyuncuIsmi = oyuncuIsmi;
            OyuncuPuani = oyuncuPuani;
        }
    }
}
