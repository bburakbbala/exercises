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
using Proje.Library.Interface;
using System.Drawing;
using System.Windows.Forms;

namespace Proje.Library.Abstract
{
    internal abstract class Cisim : PictureBox, IHareketEden
    {
        public Size HareketAlaniBoyutlari { get; }

        public int HareketMesafesi { get; protected set; }

        public new int Right
        {
            get => base.Right;
            set => Left = value - Right;
        }

        public new int Bottom
        {
            get => base.Bottom;
            set => Top = value - Height;
        }

        public int Center
        {
            get => Left + Width / 2;
            set => Left = value - Width / 2;
        }

        public int Middle
        {
            get => Top + Height / 2;
            set => Top = value - Height / 2;
        }

        public int YayMiddle
        {
            get => Top + (int)(Height * 0.28);
            set => Top = value - (int)(Height * 0.28);
        }
        protected Cisim(Size hareketAlaniBoyutlari)
        {
            HareketAlaniBoyutlari = hareketAlaniBoyutlari;
            SizeMode = PictureBoxSizeMode.AutoSize;
        }

        public bool HareketEttir(Yon yon)
        {
            if (yon == Yon.Yukari) return YukariHareketettir();
            if (yon == Yon.Saga) return SagaHareketEttir();
            if (yon == Yon.Asagi) return AsagiHareketEttir();
            if (yon == Yon.Sola) return SolaHareketEttir();
            return false;
        }

        private bool SolaHareketEttir()
        {
            if (Left == 0) return true;
            int yeniLeft = Left - HareketMesafesi;
            bool tasacakMi = yeniLeft < 0;
            Left = tasacakMi ? 0 : yeniLeft;
            return Left == 0;
        }

        private bool SagaHareketEttir()
        {
            if (Left + HareketMesafesi == HareketAlaniBoyutlari.Width) return true;
            int yeniRight = Left + HareketMesafesi;
            bool tasacakMi = yeniRight > HareketAlaniBoyutlari.Width;
            Left = tasacakMi ? HareketAlaniBoyutlari.Width : yeniRight;
            return Left == HareketAlaniBoyutlari.Width;
        }

        private bool AsagiHareketEttir()
        {
            if (Bottom == HareketAlaniBoyutlari.Height) return true;
            int yeniBottom = Bottom + HareketMesafesi;
            bool tasacakMi = yeniBottom > HareketAlaniBoyutlari.Height;
            Bottom = tasacakMi ? HareketAlaniBoyutlari.Height : yeniBottom;
            return Bottom == HareketAlaniBoyutlari.Height;
        }

        private bool YukariHareketettir()
        {
            if (Top == 0) return true;
            int yeniTop = Top - HareketMesafesi;
            bool tasacakMi = yeniTop < 0;
            Top = tasacakMi ? 0 : yeniTop;
            return Top == 0;
        }
    }
}
