//
// Created by burak on 01/05/2021.
//

#include <stdio.h>
#include "PortfoyHisse.h"
#include "Error.h"

PortfoyHisse PortfoyHisseOlustur(char* sembol, double maliyet, size_t adet)
{
    PortfoyHisse this = malloc(sizeof(struct PORTFOYHISSE));
    if (!this) error("Allocation Error!");
    this->sembol = sembol;
    this->maliyet = maliyet;
    this->adet = adet;
    this->portfoyHisseYazdir = PortfoyHisseYazdir;
    this->portfoyHisseYoket = PortFoyHisseYoket;
    return this;
}

void PortfoyHisseYazdir(PortfoyHisse this)
{
    if (!this) return;
    printf("Hisse: %s\n", this->sembol);
    printf("Adet: %zu\n", this->adet);
    printf("Maliyet: %.2lf TL\n", this->maliyet);
    printf("-----------------------------\n");
}

void PortFoyHisseYoket(PortfoyHisse this)
{
    if (!this) return;
    free(this);
}