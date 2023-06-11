//
// Created by burak on 07/05/2021.
//

#if !defined(PORTFOYLINKEDLIST_H)
#define PORTFOYLINKEDLIST_H

#include "PortfoyHisse.h"

typedef struct Node {
    PortfoyHisse portfoy_hisse;
    struct Node* next;
} node_t;

void PortfoyHisseEkle(node_t*, PortfoyHisse);
node_t* PortfoyHisseBul(node_t*, const char*);
void PortfoyPrint(node_t*);
void PortfoyYoket(node_t*);

#endif //PORTFOYLINKEDLIST_H