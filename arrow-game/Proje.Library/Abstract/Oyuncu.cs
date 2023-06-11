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

using System.Drawing;
using System.Windows.Forms;

namespace Proje.Library.Abstract
{
    internal abstract class Oyuncu : Cisim
    {
        protected string OyuncuIsmi { get; set; }
        protected int OyuncuPuani { get; set; }

        protected Oyuncu(Size hareketAlaniBoyutlari) : base(hareketAlaniBoyutlari)
        {
            SizeMode = PictureBoxSizeMode.Zoom;
            Size = new Size(170, 270);
        }
    }
}
