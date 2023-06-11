//
// Created by burak on 07/05/2021.
//

#include <string.h>
#include "PortfoyLinkedList.h"
#include "PortfoyHisse.h"

void PortfoyHisseEkle(node_t* head, PortfoyHisse portfoy_hisse)
{
    node_t* current = head;
    while (current->next != NULL) {
        current = current->next;
    }
    if (head->portfoy_hisse == portfoy_hisse) return; // don't add same object
    /* now we can add a new variable */
    current->next = malloc(sizeof(node_t));
    current->next->portfoy_hisse = portfoy_hisse;
    current->next->next = NULL;
}

node_t* PortfoyHisseBul(node_t* head, const char* hisse_sembol)
{
    if (!head) return NULL;
    node_t* current = head;
    while (current) {
        if (!strcmp(current->portfoy_hisse->sembol, hisse_sembol)) {
            return current;
        }
        current = current->next;
    }
    return NULL;
}

void PortfoyPrint(node_t* head)
{
    if (!head) return;
    node_t* current = head;
    while (current) {
        if (current->portfoy_hisse->adet == 0) {
            current = current->next;
            continue;
        }
        current->portfoy_hisse->portfoyHisseYazdir(current->portfoy_hisse);
        current = current->next;
    }
}

void PortfoyYoket(node_t* head)
{
    if (!head) return;
    node_t* current = head;
    node_t* temp;
    while (current) {
        current->portfoy_hisse->portfoyHisseYoket(current->portfoy_hisse);
        temp = current;
        current = current->next;
        free(temp);
    }
    free(current);
}