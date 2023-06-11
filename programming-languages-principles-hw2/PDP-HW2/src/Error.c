//
// Created by burak on 05/05/2021.
//

#include <stdio.h>
#include <stdlib.h>
#include "Error.h"

void error(const char* error_message)
{
    fprintf(stderr, "%s\n", error_message);
    exit(EXIT_FAILURE);
}