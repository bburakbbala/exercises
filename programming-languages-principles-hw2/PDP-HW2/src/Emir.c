//
// Created by burak on 01/05/2021.
//

#include "Emir.h"
#include "Error.h"

Emir EmirNesneOlustur(char* sembol, char* islem_adi, size_t adet)
{
    Emir this = malloc(sizeof(struct EMIR));
    if (!this) error("Allocation Error!");
    this->sembol = sembol;
    this->islem = islem_adi;
    this->adet = adet;
    this->emirYoket = EmirNesneYoket;
    return this;
}

void EmirNesneYoket(Emir this)
{
    if (!this) return;
    free(this);
}