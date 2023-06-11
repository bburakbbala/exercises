//
// Created by burak on 01/05/2021.
//

#include "Musteri.h"
#include "PortfoyLinkedList.h"
#include "Error.h"

Musteri MusteriOlustur(const char* musteri_adi, const char* musteri_soyadi)
{
    Musteri this = malloc(sizeof(struct MUSTERI));
    if (!this) error("Allocation Error!");
    this->musteri_adi = musteri_adi;
    this->musteri_soyadi = musteri_soyadi;
    this->musteriYazdir = MusteriYazdir;
    this->musteriYoket = MusteriYoket;
    return this;
}

void MusteriYazdir(const struct MUSTERI* this)
{
    if (!this) return;
    printf("Musteri: %s %s\n", this->musteri_adi, this->musteri_soyadi);
}

void MusteriYoket(Musteri this)
{
    if (!this) return;
    free(this);
}