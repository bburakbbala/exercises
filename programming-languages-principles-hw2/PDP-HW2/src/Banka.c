//
// Created by burak on 01/05/2021.
//

#include "cJSON.h"
#include "Error.h"
#include "Banka.h"
#include "EmirIsle.h"
#include "HisseBul.h"
#include "Portfoy.h"
#include "PortfoyLinkedList.h"

Banka BankaOlustur(const char* banka_adi)
{
    Banka this = malloc(sizeof(struct BANKA));
    if (!this) error("Allocation Error!");
    this->banka_adi = banka_adi;
    this->musteriler = NULL;
    this->musteri_sayisi = 0;
    this->musteriler_length = 10;
    this->bankaMusteriEkle = BankaMusteriEkle;
    this->emirleriIsle = EmirleriIsle;
    this->bankaYazdir = BankaYazdir;
    this->bankaYoket = BankaYoket;
    return this;
}

void BankaMusteriEkle(Banka this, Musteri musteri)
{
    if (!this || !musteri) return;
    
    if (!this->musteriler) {
        this->musteriler = malloc(sizeof(struct MUSTERI) * this->musteriler_length);
        if (!this->musteriler) error("Allocation Error!");
        this->musteriler[0] = musteri;
        this->musteri_sayisi++;
    } else if (this->musteriler_length != this->musteri_sayisi) {
        this->musteriler[this->musteri_sayisi] = musteri;
        this->musteri_sayisi++;
    } else { // array is full
        this->musteriler_length = this->musteriler_length * 2;
        this->musteriler = realloc(this->musteriler, this->musteriler_length * sizeof(struct MUSTERI));
        if (!this->musteriler) error("Reallocation Error!");
        this->musteriler[this->musteri_sayisi] = musteri;
        this->musteri_sayisi++;
    }
}

void EmirleriIsle(const struct BANKA* banka, Musteri this)
{
    if (BankaMusterisiMi(banka, this) == false) return;
    cJSON* hisseler = HisseleriOku("./doc/hisseler.json");
    
    cJSON* portfoy = PortfoyuOku("./doc/portfoy.json");
    node_t* head = PortfoyOlustur(portfoy);
    if (!head) error("Portfoy Olusturulamadı!");
    
    cJSON* emirler = EmirleriOku("./doc/emirler.json");
    int emir_adedi = cJSON_GetArraySize(emirler);
    Emir* emirler_dizi = malloc(sizeof(Emir) * emir_adedi);
    if (!emirler_dizi) error("Allocation Error!");
    for (int i = 0; i < emir_adedi; ++i) {
        emirler_dizi[i] = EmirOlustur(emirler, i);
    }
    
    printf("Satislar Kar/Zarar:\n");
    double toplam_kar_zarar = 0;
    for (int i = 0; i < emir_adedi; ++i) {
        EmirIsle(emirler_dizi[i], hisseler, head, &toplam_kar_zarar);
    }
    printf("Toplam Kar/Zarar: ");
    if (toplam_kar_zarar > 0) {
        printf("+%.2f TL\n", toplam_kar_zarar);
    } else if (toplam_kar_zarar < 0) {
        printf("%.2f TL\n", toplam_kar_zarar); // negative number
    }
    printf("\nGuncel Portfoy:\n");
    PortfoyPrint(head);
    PortfoyYoket(head);
    // destroy emirler
    for (int i = 0; i < emir_adedi; ++i) {
        emirler_dizi[i]->emirYoket(emirler_dizi[i]);
    }
    free(emirler_dizi);
    cJSON_Delete(emirler);
    cJSON_Delete(hisseler);
    cJSON_Delete(portfoy);
}

boolean BankaMusterisiMi(const struct BANKA* this, Musteri musteri)
{
    boolean bankaMusterisiMi = false;
    for (int i = 0; i < this->musteri_sayisi; ++i) {
        if (this->musteriler[i] == musteri) bankaMusterisiMi = true;
    }
    if (bankaMusterisiMi == false) printf("\nBanka Musterisi Degil!\n");
    return bankaMusterisiMi;
}

void BankaYazdir(const struct BANKA* this)
{
    if (!this) return;
    printf("Banka: %s\n", this->banka_adi);
}

void BankaYoket(Banka this)
{
    if (!this) return;
    free(this->musteriler);
    free(this);
}
