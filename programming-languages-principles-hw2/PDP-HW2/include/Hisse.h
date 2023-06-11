//
// Created by burak on 01/05/2021.
//

#if !defined(HISSE_H)
#define HISSE_H

struct HISSE {
    const char* sembol;
    double fiyat;
    
    void (*hisseYoket)(struct HISSE*);
};
typedef struct HISSE* Hisse;

Hisse HisseOlustur(const char*, double);
void HisseYoket(Hisse);

#endif //HISSE_H