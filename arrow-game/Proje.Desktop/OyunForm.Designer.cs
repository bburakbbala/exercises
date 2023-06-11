namespace Proje.Desktop
{
    partial class OyunForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(OyunForm));
            this.bilgiPanel = new System.Windows.Forms.Panel();
            this.asamaLabel = new System.Windows.Forms.Label();
            this.puanLabel = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.okcuPanel = new System.Windows.Forms.Panel();
            this.savasAlaniPanel = new System.Windows.Forms.Panel();
            this.bilgiPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // bilgiPanel
            // 
            this.bilgiPanel.BackColor = System.Drawing.Color.CadetBlue;
            this.bilgiPanel.Controls.Add(this.asamaLabel);
            this.bilgiPanel.Controls.Add(this.puanLabel);
            this.bilgiPanel.Controls.Add(this.label1);
            this.bilgiPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.bilgiPanel.Location = new System.Drawing.Point(0, 0);
            this.bilgiPanel.Name = "bilgiPanel";
            this.bilgiPanel.Size = new System.Drawing.Size(1385, 67);
            this.bilgiPanel.TabIndex = 0;
            // 
            // asamaLabel
            // 
            this.asamaLabel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.asamaLabel.Font = new System.Drawing.Font("Century Gothic", 17.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.asamaLabel.ForeColor = System.Drawing.Color.White;
            this.asamaLabel.Location = new System.Drawing.Point(311, 9);
            this.asamaLabel.Name = "asamaLabel";
            this.asamaLabel.Size = new System.Drawing.Size(1062, 48);
            this.asamaLabel.TabIndex = 4;
            this.asamaLabel.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // puanLabel
            // 
            this.puanLabel.Font = new System.Drawing.Font("Century Gothic", 23.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.puanLabel.ForeColor = System.Drawing.Color.White;
            this.puanLabel.Location = new System.Drawing.Point(100, 8);
            this.puanLabel.Name = "puanLabel";
            this.puanLabel.Size = new System.Drawing.Size(134, 56);
            this.puanLabel.TabIndex = 3;
            this.puanLabel.Text = "0";
            this.puanLabel.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label1
            // 
            this.label1.BackColor = System.Drawing.Color.CadetBlue;
            this.label1.Font = new System.Drawing.Font("Century Gothic", 23.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(6, 8);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(148, 56);
            this.label1.TabIndex = 2;
            this.label1.Text = "Puan: ";
            this.label1.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // okcuPanel
            // 
            this.okcuPanel.BackColor = System.Drawing.Color.PowderBlue;
            this.okcuPanel.Dock = System.Windows.Forms.DockStyle.Left;
            this.okcuPanel.Location = new System.Drawing.Point(0, 67);
            this.okcuPanel.Name = "okcuPanel";
            this.okcuPanel.Size = new System.Drawing.Size(170, 733);
            this.okcuPanel.TabIndex = 1;
            // 
            // savasAlaniPanel
            // 
            this.savasAlaniPanel.BackColor = System.Drawing.Color.PowderBlue;
            this.savasAlaniPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.savasAlaniPanel.Location = new System.Drawing.Point(170, 67);
            this.savasAlaniPanel.Name = "savasAlaniPanel";
            this.savasAlaniPanel.Size = new System.Drawing.Size(1215, 733);
            this.savasAlaniPanel.TabIndex = 2;
            // 
            // OyunForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1385, 800);
            this.Controls.Add(this.savasAlaniPanel);
            this.Controls.Add(this.okcuPanel);
            this.Controls.Add(this.bilgiPanel);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "OyunForm";
            this.Text = "Okçu Oyunu";
            this.WindowState = System.Windows.Forms.FormWindowState.Maximized;
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.OyunForm_KeyDown);
            this.bilgiPanel.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel bilgiPanel;
        private System.Windows.Forms.Label puanLabel;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel okcuPanel;
        private System.Windows.Forms.Panel savasAlaniPanel;
        private System.Windows.Forms.Label asamaLabel;
    }
}

