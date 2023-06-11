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
    internal class Ok : Cisim
    {
        public Ok(Size hareketAlaniBoyutlari, int yayUcu, bool okAtesliMi) : base(hareketAlaniBoyutlari)
        {
            string ok = @"Resources\Images\arrow.png";
            if (okAtesliMi) ok = @"Resources\Images\arrow-fiery.png";
            Image = Image.FromFile(ok);
            BaslangicKonumunuAyarla(yayUcu);
            HareketMesafesi = (int)(Width * 0.5);
        }
        private void BaslangicKonumunuAyarla(int yayUcu)
        {
            YayMiddle = yayUcu;
        }
    }
}
