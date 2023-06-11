//
// Created by burak on 01/05/2021.
//

#if !defined(MUSTERI_H)
#define MUSTERI_H

#include <stdio.h>
#include <stdlib.h>

struct MUSTERI {
    const char* musteri_adi;
    const char* musteri_soyadi;
    
    void (*musteriYazdir)(const struct MUSTERI*);
    void (*musteriYoket)(struct MUSTERI*);
};
typedef struct MUSTERI* Musteri;

Musteri MusteriOlustur(const char*, const char*);
void MusteriYazdir(const struct MUSTERI*);
void MusteriYoket(Musteri);

#endif //MUSTERI_H