//
// Created by burak on 07/05/2021.
//

#if !defined(EMIRISLE_H)
#define EMIRISLE_H

#include "cJSON.h"
#include "Emir.h"
#include "PortfoyHisse.h"
#include "PortfoyLinkedList.h"

cJSON* EmirleriOku(const char*);
void EmirIsle(Emir, cJSON*, node_t*, double*);
Emir EmirOlustur(cJSON*, int);

#endif //EMIRISLE_H