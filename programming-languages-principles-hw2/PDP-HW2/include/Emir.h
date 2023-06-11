//
// Created by burak on 01/05/2021.
//

#if !defined(EMIR_H)
#define EMIR_H

#include <stdlib.h>

struct EMIR {
    char* sembol;
    char* islem;
    size_t adet;
    
    void (*emirYoket)(struct EMIR*);
};
typedef struct EMIR* Emir;

Emir EmirNesneOlustur(char*, char*, size_t);
void EmirNesneYoket(Emir this);

#endif //EMIR_H