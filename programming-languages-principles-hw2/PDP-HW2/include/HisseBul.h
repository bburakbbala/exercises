//
// Created by burak on 06/05/2021.
//

#if !defined(HISSEBUL_H)
#define HISSEBUL_H

#include "Hisse.h"
#include "cJSON.h"

cJSON* HisseleriOku(const char*);
Hisse HisseFiyatiBul(const char*, cJSON*);

#endif //HISSEBUL_H
