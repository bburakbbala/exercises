//
// Created by burak on 06/05/2021.
//

#include <string.h>
#include "DosyaOku.h"
#include "CharOperation.h"
#include "Portfoy.h"

cJSON* PortfoyuOku(const char* file_path)
{
    char* buffer = readFile(file_path);
    cJSON* json = cJSON_Parse(buffer);
    if (!json) error("JSON Parse Error!");
    cJSON* json_array = cJSON_GetArrayItem(json, 0);
    freeFileContent(buffer);
    cJSON_free(json);
    return json_array; // json'ı yoket
}

node_t*  PortfoyOlustur(cJSON* portfoy_content)
{
    if (!portfoy_content) return NULL;
    int array_size_portfoy = cJSON_GetArraySize(portfoy_content);
    node_t* root = NULL;
    for (int i = 0; i < array_size_portfoy; ++i) {
        char* hisse_adi = cJSON_GetObjectItem(cJSON_GetArrayItem(portfoy_content, i), "Sembol")->valuestring;
        remove_char(hisse_adi, '"');
        remove_char(hisse_adi, ' ');
        double maliyet = cJSON_GetObjectItem(cJSON_GetArrayItem(portfoy_content, i), "Maliyet")->valuedouble;
        int adet = cJSON_GetObjectItem(cJSON_GetArrayItem(portfoy_content, i), "Adet")->valueint;
    
        PortfoyHisse this = PortfoyHisseOlustur(hisse_adi, maliyet, adet);
        if (!root) {
            root = malloc(sizeof(node_t));
            root->portfoy_hisse = this;
            root->next = NULL;
        } else {
            PortfoyHisseEkle(root, this);
        }
    }
    return root;
}