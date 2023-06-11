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
    internal class BalonYesil : Balon
    {
        public BalonYesil(Size hareketAlaniBoyutlari) : base(hareketAlaniBoyutlari)
        {
            string balon = @"Resources\Images\balloon-green.png";
            Image = Image.FromFile(balon);
        }
    }
}
