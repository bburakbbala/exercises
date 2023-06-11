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

using Proje.Library.Enum;
using System;

namespace Proje.Library.Interface
{
    interface IOyun
    {
        event EventHandler SureDegisti;
        bool DevamEdiyorMu { get; }
        void Baslat();
        void OkAt();
        void OkcuyuHareketEttir(Yon yon);
        void OklariHareketEttir(Yon yon);
    }
}
