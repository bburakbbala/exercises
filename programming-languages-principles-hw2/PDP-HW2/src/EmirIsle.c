//
// Created by burak on 07/05/2021.
//

#include <stdio.h>
#include <string.h>
#include <math.h>
#include "EmirIsle.h"
#include "DosyaOku.h"
#include "CharOperation.h"
#include "Portfoy.h"
#include "HisseBul.h"

cJSON* EmirleriOku(const char* file_path)
{
    char* buffer = readFile(file_path);
    cJSON* json = cJSON_Parse(buffer);
    if (!json) error("JSON Parse Error!");
    cJSON* json_array = cJSON_GetArrayItem(json, 0);
    freeFileContent(buffer);
    cJSON_free(json);
    return json_array; // destroy cJSON
}

Emir EmirOlustur(cJSON* emirler, int index) {
    if (!emirler) return NULL;
    
    char* hisse_adi = cJSON_GetObjectItem(cJSON_GetArrayItem(emirler, index), "Sembol")->valuestring;
    remove_char(hisse_adi, '"');
    remove_char(hisse_adi, ' ');
    char* islem = cJSON_GetObjectItem(cJSON_GetArrayItem(emirler, index), "Islem")->valuestring;
    remove_char(islem, '"');
    remove_char(islem, ' ');
    unsigned long adet = cJSON_GetObjectItem(cJSON_GetArrayItem(emirler, index), "Adet")->valueint;
    
    Emir this = EmirNesneOlustur(hisse_adi, islem, adet);
    return this;
}

void EmirIsle(Emir emir, cJSON* hisseler, node_t* portfoy_list, double* toplam_kar_zarar)
{
    if (!emir) return;
    
    Hisse hisse = HisseFiyatiBul(emir->sembol, hisseler);
    if (!hisse) return; // bulunmayan hisse islenmedi
    
    node_t* portfoy_islenen_hisse = PortfoyHisseBul(portfoy_list, emir->sembol);
    
    if (!strcmp(emir->islem, "SATIS") && portfoy_islenen_hisse && portfoy_islenen_hisse->portfoy_hisse->adet != 0) { // portfoyde olmayan hisse satılmaz
        double guncel_fiyat, eski_fiyat;
        if (emir->adet < portfoy_islenen_hisse->portfoy_hisse->adet) {
            guncel_fiyat = hisse->fiyat * emir->adet;
            eski_fiyat = portfoy_islenen_hisse->portfoy_hisse->maliyet * emir->adet;
            portfoy_islenen_hisse->portfoy_hisse->adet -= emir->adet;
        } else if (emir->adet == portfoy_islenen_hisse->portfoy_hisse->adet) { // esit  durumunda portfoyun hepsi satıldı
            
            guncel_fiyat = hisse->fiyat * portfoy_islenen_hisse->portfoy_hisse->adet;
            eski_fiyat = portfoy_islenen_hisse->portfoy_hisse->maliyet * portfoy_islenen_hisse->portfoy_hisse->adet;
            portfoy_islenen_hisse->portfoy_hisse->adet = 0;
        }  else { // fazla olması durumunda satilmadi
            return;
        }
        double sonuc = guncel_fiyat - eski_fiyat;
        if (sonuc > 0) { // kar
            printf("%s : %.2lf TL Kar\n", emir->sembol, sonuc);
        } else if (sonuc < 0) { // zarar
            printf("%s : %.2lf TL Zarar\n", emir->sembol, fabs(sonuc));
        }
        *toplam_kar_zarar += sonuc;
    } else if (!strcmp(emir->islem, "ALIS")) {
        if (portfoy_islenen_hisse == NULL) { // portfoy yok olustur
            PortfoyHisseEkle(portfoy_list, PortfoyHisseOlustur(emir->sembol, hisse->fiyat, emir->adet));
        } else { // varolan portfoy, portfoyu guncelle
            double yeni_maliyet =
                (portfoy_islenen_hisse->portfoy_hisse->adet * portfoy_islenen_hisse->portfoy_hisse->maliyet +
                 emir->adet * hisse->fiyat);
            portfoy_islenen_hisse->portfoy_hisse->adet += emir->adet;
            yeni_maliyet /= portfoy_islenen_hisse->portfoy_hisse->adet;
            portfoy_islenen_hisse->portfoy_hisse->maliyet = yeni_maliyet;
        }
    }
    hisse->hisseYoket(hisse);
}