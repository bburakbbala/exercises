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
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace Proje.Desktop
{
    public partial class AyarForm : Form
    {
        private OyunForm _oyunForm;
        private int _oncekiOyunlarSeciliOyunIndex;

        public AyarForm()
        {
            InitializeComponent();
            OncekiOyunlariListeyeEkle();
            Activated += AyarForm_Activated;
            yeniOyuncuTexBox.KeyDown += YeniOyuncuTexBox_KeyDown;
            zeminRengiComboBox.Items.Add(Color.PowderBlue);
            zeminRengiComboBox.Items.Add(Color.FloralWhite);
            zeminRengiComboBox.Items.Add(Color.LightGoldenrodYellow);
            zeminRengiComboBox.SelectedIndex = zeminRengiComboBox.Items.IndexOf(Color.PowderBlue);
        }
        // enter a basınca ses çıkarıyor çözümler işe yaramadı
        private void YeniOyuncuTexBox_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.Enter)
            {
                oyunuBaslatButton.PerformClick();
                yeniOyuncuTexBox.Text = "";
            }
        }

        private void AyarForm_Activated(object sender, EventArgs e)
        {
            OncekiOyunlariListeyeEkle();
            if (oncekiOyunlarListBox.Items.Count == 0 && oncekiOyunlarListBox.SelectedIndex == -1) secliOyunaDevamEtButton.Enabled = false;
            if (oncekiOyunlarListBox.Items.Count != 0) secliOyunaDevamEtButton.Enabled = true;
        }

        private void OyunuBaslatButton_Click(object sender, EventArgs e)
        {
            OyunaBaslat();
        }

        private void OyunaBaslat()
        {
            if (yeniOyuncuTexBox.Text == "")
            {
                MessageBox.Show("İsminizi Giriniz.");
            }
            else
            {
                _oyunForm = new OyunForm(erkekOkcuRadioButton.Checked, yeniOyuncuTexBox.Text, okAtesliMiRadioButton.Checked, (Color)zeminRengiComboBox.SelectedItem);
                yeniOyuncuTexBox.Text = "";
                _oyunForm.ShowDialog();
                Close();
            }
        }

        private void OyunlariSilButton_Click(object sender, EventArgs e)
        {
            File.Delete(Oyun._savedGames);
            File.Delete(Oyun._savedOptions);
            File.Delete(Oyun._continuGame);
            oncekiOyunlarListBox.Items.Clear();
        }

        public void OncekiOyunlariListeyeEkle()
        {
            oncekiOyunlarListBox.Items.Clear();
            if (File.Exists(Oyun._savedGames))
            {
                string[] lines = File.ReadAllLines(Oyun._savedGames);
                foreach (string line in lines)
                    oncekiOyunlarListBox.Items.Add(line);
            }
        }

        private void SecliOyunaDevamEtButton_Click(object sender, EventArgs e)
        {
            _oncekiOyunlarSeciliOyunIndex = oncekiOyunlarListBox.SelectedIndex;
            _oyunForm = new OyunForm(_oncekiOyunlarSeciliOyunIndex);
            _oyunForm.ShowDialog();
            Close();
        }
    }
}
