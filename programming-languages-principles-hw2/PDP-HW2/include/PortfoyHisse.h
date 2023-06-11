//
// Created by burak on 01/05/2021.
//

#if !defined(PORTFOYHISSE_H)
#define PORTFOYHISSE_H

#include <stdlib.h>

struct PORTFOYHISSE {
    char* sembol;
    double maliyet;
    size_t adet;
    
    void (*portfoyHisseYazdir)(struct PORTFOYHISSE*);
    void (*portfoyHisseYoket)(struct PORTFOYHISSE*);
};
typedef struct PORTFOYHISSE* PortfoyHisse;

PortfoyHisse PortfoyHisseOlustur(char*, double, size_t);
void PortfoyHisseYazdir(PortfoyHisse);
void PortFoyHisseYoket(PortfoyHisse);

#endif //PORTFOYHISSE_H