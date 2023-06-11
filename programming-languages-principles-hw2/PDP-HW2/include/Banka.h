//
// Created by burak on 01/05/2021.
//

#if !defined(BANKA_H)
#define BANKA_H

#include "Musteri.h"

struct BANKA {
    const char* banka_adi;
    int musteri_sayisi;
    size_t musteriler_length;
    Musteri* musteriler;
    
    void (*bankaMusteriEkle)(struct BANKA*, Musteri);
    void (*emirleriIsle)(const struct BANKA*, Musteri);
    void (*bankaYazdir)(const struct BANKA*);
    void (*bankaYoket)(struct BANKA*);
};
typedef struct BANKA* Banka;
typedef enum { true, false } boolean;

Banka BankaOlustur(const char*);
void BankaMusteriEkle(Banka, Musteri);
boolean BankaMusterisiMi(const struct BANKA*, Musteri);
void EmirleriIsle(const struct BANKA*, Musteri);
void BankaYazdir(const struct BANKA*);
void BankaYoket(Banka);

#endif //BANKA_H