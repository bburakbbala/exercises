using Proje.Library.Concrete;
using Proje.Library.Enum;
using System;
using System.Drawing;
using System.Windows.Forms;

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

namespace Proje.Desktop
{
    public partial class OyunForm : Form
    {
        public readonly Oyun _oyun;

        public OyunForm(int oncekiOyunlarSeciliOyunIndex)
        {
            InitializeComponent();
            _oyun = new Oyun(okcuPanel, savasAlaniPanel, oncekiOyunlarSeciliOyunIndex);
            _oyun.SureDegisti += Oyun_GecenSureDegisti;
        }

        public OyunForm(bool erkekOkcuMu, string oyuncuIsmi, bool okAtesliMi, Color zeminRengi)
        {
            InitializeComponent();
            _oyun = new Oyun(okcuPanel, savasAlaniPanel, oyuncuIsmi, erkekOkcuMu, okAtesliMi);
            _oyun.SureDegisti += Oyun_GecenSureDegisti;
            _oyun.OyunAyarlari(oyuncuIsmi, erkekOkcuMu, okAtesliMi, zeminRengi);
            savasAlaniPanel.BackColor = zeminRengi;
            okcuPanel.BackColor = zeminRengi;
            asamaLabel.Text = $"{oyuncuIsmi} oyunu başlatmak için Enter'a basın.";
        }

        private void OyunForm_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter && !_oyun.DevamEdiyorMu)
                _oyun.Baslat();
            if (e.KeyCode == Keys.Up)
                _oyun.OkcuyuHareketEttir(Yon.Yukari);
            if (e.KeyCode == Keys.Down)
                _oyun.OkcuyuHareketEttir(Yon.Asagi);
            if (e.KeyCode == Keys.Space)
                _oyun.OkAt();
            if (e.KeyCode == Keys.F5)
                _oyun.YenidenBaslat();
            if (e.KeyCode == Keys.S && e.Modifiers == Keys.Control && (Oyun.OyunBasladiMi || Oyun.OyunBittiMi)) _oyun.OyunuKaydet();
        }

        private void Oyun_GecenSureDegisti(object sender, EventArgs e)
        {
            puanLabel.Text = Oyun.Puan.ToString();
            asamaLabel.Text = Oyun.AsamaLabel.ToString();
            // oyunda herhangi bir anda kayıt yap
            if (Oyun.OyunBittiMi) asamaLabel.Text = "Oyun Bitti. Yeniden Başlatmak İçin F5'e basın, kaydetmek için CTRL+S'e basınız.";
            if (Oyun.OyunKaydedildiMi)
            {
                _oyun.Bitir();
                Close();
            }
        }
    }
}
