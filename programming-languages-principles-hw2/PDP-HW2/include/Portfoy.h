//
// Created by burak on 06/05/2021.
//

#if !defined(PORTFOYOKU_H)
#define PORTFOYOKU_H

#include "cJSON.h"
#include "PortfoyHisse.h"
#include "PortfoyLinkedList.h"

cJSON* PortfoyuOku(const char*);
node_t* PortfoyOlustur(cJSON*);

#endif //PORTFOYOKU_H