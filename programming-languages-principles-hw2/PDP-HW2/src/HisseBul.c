//
// Created by burak on 06/05/2021.
//

#include <string.h>
#include "HisseBul.h"
#include "DosyaOku.h"
#include "CharOperation.h"
#include "cJSON.h"

cJSON* HisseleriOku(const char* file_path)
{
    char* buffer = readFile(file_path);
    cJSON* json = cJSON_Parse(buffer);
    if (!json) error("JSON Parse Error!");
    cJSON* json_array = cJSON_GetArrayItem(json, 0);
    freeFileContent(buffer);
    cJSON_free(json);
    return json_array; // destroy cJSON
}

Hisse HisseFiyatiBul(const char* hisse_sembolu, cJSON* hisseler)
{
    if (!hisseler) error("Null argument!");
    int array_size = cJSON_GetArraySize(hisseler);
    for (int i = 0; i < array_size; ++i) {
        char* hisse_adi = cJSON_GetObjectItem(cJSON_GetArrayItem(hisseler, i), "Sembol")->valuestring;
        if (!hisse_adi) error("Couldn't Find Given Stock in hisseler.json!");
        remove_char(hisse_adi, '\"');
        remove_char(hisse_adi, ' '); // removes whitespaces
        if (!strcmp(hisse_sembolu, hisse_adi)) {
            double fiyat = cJSON_GetObjectItem(cJSON_GetArrayItem(hisseler, i), "Fiyat")->valuedouble;
            Hisse this = HisseOlustur(hisse_sembolu, fiyat);
            return this;
        }
    }
    return NULL;
}