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

using Proje.Library.Concrete;
using System;
using System.Collections.Generic;
using System.Drawing;

namespace Proje.Library.Abstract
{
    internal abstract class Balon : Cisim
    {
        private static readonly Random _random = new Random();

        protected Balon(Size hareketAlaniBoyutlari) : base(hareketAlaniBoyutlari)
        {
            Left = _random.Next(20, hareketAlaniBoyutlari.Width - Width + 1);
            HareketMesafesi = (int)(Height * 0.16);
        }
        public bool VurulduMu(List<Ok> oklar)
        {
            foreach (var ok in oklar)
            {
                bool vurulduMu = ok.Top < Bottom && ok.Bottom > Top && ok.Right > Left && ok.Left < Right;
                if (vurulduMu) return true;
            }
            return false;
        }
    }
}
