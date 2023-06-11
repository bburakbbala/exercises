//
// Created by burak on 01/05/2021.
//

#include <stdlib.h>
#include "DosyaOku.h"
#include "Hisse.h"

Hisse HisseOlustur(const char* sembol, double fiyat)
{
    Hisse this = malloc(sizeof(struct HISSE));
    if (!this) error("Allocation Error!");
    this->fiyat = fiyat;
    this->sembol = sembol;
    this->hisseYoket = HisseYoket;
    return this;
}

void HisseYoket(Hisse this)
{
    if (!this) return;
    free(this);
}