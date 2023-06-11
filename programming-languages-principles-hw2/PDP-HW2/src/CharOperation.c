//
// Created by burak on 15/05/2021.
//

#include <string.h>
#include "CharOperation.h"

void remove_char(char* source, char remove) {
    size_t j, n = strlen(source);
    for (size_t i = j = 0; i < n; i++) {
        if (source[i] != remove) {
            source[j++] = source[i];
        }
    }
    source[j] = '\0';
}