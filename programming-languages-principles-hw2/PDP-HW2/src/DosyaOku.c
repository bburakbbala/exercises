//
// Created by burak on 05/05/2021.
//
#include <stdio.h>
#include <stdlib.h>
#include "DosyaOku.h"

char* readFile(const char* file_name) {
    char* buffer;
    long length;
    FILE* f = fopen(file_name, "r");
    
    if (f) {
        fseek(f, 0, SEEK_END);
        length = ftell(f);
        fseek(f, 0, SEEK_SET);
        
        // 1 GiB; best not to load a whole large file in one string
        if (length > 1073741824) {
            fclose(f);
            error("File is too large!");
        }
        buffer = malloc(length);
        if (buffer) {
            fread(buffer, 1, length, f);
        } else {
            error("Allocation Error!");
        }
        fclose(f);
        return buffer;
    }
    error("File Couldn't Open!");
    return NULL;
}

void freeFileContent(char* content) {
    if(!content) return;
    free(content);
}